package br.com.unialfa.ciep.service;
import br.com.unialfa.ciep.DAO.EmpresaDAO;
import br.com.unialfa.ciep.domain.Empresa;
import br.com.unialfa.ciep.domain.Usuario;
import br.com.unialfa.ciep.repository.UsuarioRepository;
import br.com.unialfa.ciep.repository.EmpresaRepository;
import br.com.unialfa.ciep.business.EmpresaBusiness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/empresa")
public class EmpresaController {

    final EmpresaBusiness empresaBusiness;
    final EmpresaRepository empresaRepository;
    final UsuarioRepository usuarioRepository;


    public EmpresaController(EmpresaBusiness empresaBusiness, EmpresaRepository empresaRepository, UsuarioRepository usuarioRepository) {
        this.empresaBusiness = empresaBusiness;
        this.empresaRepository = empresaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping(value = "/listar/{email}")
    public ResponseEntity<?> listarEmpresa(@PathVariable(name = "email") String email) {
        try {
            return new ResponseEntity<>(empresaBusiness.listarEmpresa(email), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/add")
    public ResponseEntity<?> cadastrarEmpresa(@RequestBody EmpresaDAO empresaDAO){
        try {
            return new ResponseEntity<>(empresaBusiness.salvarEmpresa(empresaDAO), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/edit/{email}")
    public ResponseEntity<?> editarEmpresa(@RequestBody Empresa empresa, @PathVariable(name = "email") String email) {
        try {
            Usuario usuario = usuarioRepository.findByEmail(email);
            empresa.setUsuario(usuario);
            return new ResponseEntity<>(empresaRepository.save(empresa), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> bucarTipoPorId(@PathVariable(name = "id") long id){
        try {
            return new ResponseEntity<>(empresaRepository.findById(id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> deletarEmpresa(@PathVariable(name = "id") long id){
        try {
            empresaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

}

