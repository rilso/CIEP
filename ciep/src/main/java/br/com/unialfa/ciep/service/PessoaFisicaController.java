package br.com.unialfa.ciep.service;
import br.com.unialfa.ciep.DAO.PessoaFisicaDAO;
import br.com.unialfa.ciep.domain.PessoaFisica;
import br.com.unialfa.ciep.domain.Usuario;
import br.com.unialfa.ciep.repository.UsuarioRepository;
import br.com.unialfa.ciep.repository.PessoaFisicaRepository;
import br.com.unialfa.ciep.business.PessoaFisicaBusiness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/pessoaFisica")
public class PessoaFisicaController {

    final PessoaFisicaBusiness pessoaFisicaBusiness;
    final PessoaFisicaRepository pessoaFisicaRepository;
    final UsuarioRepository usuarioRepository;


    public PessoaFisicaController(PessoaFisicaBusiness pessoaFisicaBusiness, PessoaFisicaRepository pessoaFisicaRepository, UsuarioRepository usuarioRepository) {
        this.pessoaFisicaBusiness = pessoaFisicaBusiness;
        this.pessoaFisicaRepository = pessoaFisicaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping(value = "/listar/{email}")
    public ResponseEntity<?> listarPessoa(@PathVariable(name = "email") String email) {
        try {
            return new ResponseEntity<>(pessoaFisicaBusiness.listarPessoa(email), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/add")
    public ResponseEntity<?> cadastrarPessoaFisica(@RequestBody PessoaFisicaDAO pessoaFisicaDAO) {
        PessoaFisicaDAO teste = pessoaFisicaDAO;
        try {
            return new ResponseEntity<>(pessoaFisicaBusiness.salvarPessoaFisica(pessoaFisicaDAO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping(path = "/edit/{email}")
    public ResponseEntity<?> editarPessoaFisica(@RequestBody PessoaFisica pessoaFisica, @PathVariable(name = "email") String email) {
        try {
            Usuario usuario = usuarioRepository.findByEmail(email);
            pessoaFisica.setUsuario(usuario);
            return new ResponseEntity<>(pessoaFisicaRepository.save(pessoaFisica), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> bucarTipoPorId(@PathVariable(name = "id") long id) {
        try {
            return new ResponseEntity<>(pessoaFisicaRepository.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> deletarEmpresa(@PathVariable(name = "id") long id){
        try {
            pessoaFisicaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }
}
