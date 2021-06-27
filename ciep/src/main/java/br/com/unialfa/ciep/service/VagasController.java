package br.com.unialfa.ciep.service;

import br.com.unialfa.ciep.DAO.VagasDAO;
import br.com.unialfa.ciep.domain.Empresa;
import br.com.unialfa.ciep.domain.Usuario;
import br.com.unialfa.ciep.domain.Vagas;
import br.com.unialfa.ciep.repository.UsuarioRepository;
import br.com.unialfa.ciep.repository.EmpresaRepository;
import br.com.unialfa.ciep.repository.VagasRepository;
import br.com.unialfa.ciep.business.VagasBusiness;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/vagasEmprego")
public class VagasController {

    final VagasRepository vagasRepository;
    final VagasBusiness vagasBusiness;
    final UsuarioRepository usuarioRepository;
    private final EmpresaRepository empresaRepository;


    public VagasController(VagasRepository vagasRepository, VagasBusiness vagasBusiness, UsuarioRepository usuarioRepository, EmpresaRepository empresaRepository) {
        this.vagasRepository = vagasRepository;
        this.vagasBusiness = vagasBusiness;
        this.usuarioRepository = usuarioRepository;
        this.empresaRepository = empresaRepository;

    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listarVagas() {
        try {
            return new ResponseEntity<>(vagasRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/listar/{email}")
    public ResponseEntity<?> listarVagasEmpresa(@PathVariable(name = "email") String email) {
        try {
            return new ResponseEntity<>(vagasBusiness.listarVagasEmpresa(email), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> bucarVagaPorId(@PathVariable(name = "id") long id) {
        try {
            return new ResponseEntity<>(vagasRepository.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/add/{email}")
    public ResponseEntity<?> cadastrarVagasEmprego(@RequestBody VagasDAO vagasEmpregoDTO, @PathVariable(name = "email") String email) {
        return vagasBusiness.salvarVagasEmprego(vagasEmpregoDTO, email);
    }

    @PutMapping(path = "/edit/{email}")
    public ResponseEntity<?> editarVagasEmprego(@RequestBody Vagas vagas, @PathVariable(name = "email") String email) {
        try {
            Usuario usuario = usuarioRepository.findByEmail(email);
            Empresa empresa = empresaRepository.findByUsuarioId(usuario.getId());
            vagas.setEmpresa(empresa);
            return new ResponseEntity<>(vagasRepository.save(vagas), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deletarVagaEmprego(@PathVariable(name = "id") long id){
        return vagasBusiness.deletarVagaEmprego(id);
    }
}
