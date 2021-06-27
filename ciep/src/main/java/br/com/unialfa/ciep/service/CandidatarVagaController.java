package br.com.unialfa.ciep.service;
import br.com.unialfa.ciep.DAO.CandidatarVagaDAO;
import br.com.unialfa.ciep.business.CandidatarVagaBusiness;
import br.com.unialfa.ciep.domain.CandidatarVaga;
import br.com.unialfa.ciep.repository.CandidatarVagaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/candidatarVaga")

public class CandidatarVagaController {

    private final CandidatarVagaRepository candidatarVagaRepository;
    private final CandidatarVagaBusiness candidatarVagaBusiness;

    public CandidatarVagaController(CandidatarVagaRepository candidatarVagaRepository, CandidatarVagaBusiness candidatarVagaBusiness) {
        this.candidatarVagaRepository = candidatarVagaRepository;
        this.candidatarVagaBusiness = candidatarVagaBusiness;
    }

    @PostMapping(path = "/add")
    public ResponseEntity<?> candidatarVaga(@RequestBody CandidatarVagaDAO candidatarVagaDTO) {
        return candidatarVagaBusiness.candidatarVaga(candidatarVagaDTO);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listarEndereco(){
        try {
            return new ResponseEntity<>(candidatarVagaRepository.findAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }
}
