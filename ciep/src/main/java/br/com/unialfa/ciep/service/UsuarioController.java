package br.com.unialfa.ciep.service;

import br.com.unialfa.ciep.business.UsuarioBusiness;
import br.com.unialfa.ciep.domain.Usuario;
import br.com.unialfa.ciep.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/usuario")

public class UsuarioController {

    final UsuarioBusiness usuarioBusiness;
    final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioBusiness usuarioBusiness, UsuarioRepository usuarioRepository) {
        this.usuarioBusiness = usuarioBusiness;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listarCadastroLogin() {
        try {
            return new ResponseEntity<>(usuarioRepository.findAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/add")
    public ResponseEntity<?> cadastrarCadastroLogin(@RequestBody Usuario usuario) {
        return usuarioBusiness.salvar(usuario);
    }

    @PutMapping(path = "/edit")
    public ResponseEntity<?> editarCadastroLogin(@RequestBody Usuario usuario) {
        try {
            return new ResponseEntity<>(usuarioRepository.save(usuario), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> bucarTipoPorId(@RequestBody Usuario usuario){
        try {
            Usuario usuarioDao = usuarioRepository.findByEmail(usuario.getEmail());
            if(usuarioDao.getSenha().equals(usuario.getSenha())){
                return new ResponseEntity<>(usuario, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }
}
