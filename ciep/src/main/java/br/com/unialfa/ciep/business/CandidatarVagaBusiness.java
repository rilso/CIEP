package br.com.unialfa.ciep.business;

import br.com.unialfa.ciep.DAO.CandidatarVagaDAO;
import br.com.unialfa.ciep.domain.CandidatarVaga;
import br.com.unialfa.ciep.domain.PessoaFisica;
import br.com.unialfa.ciep.domain.Vagas;
import br.com.unialfa.ciep.repository.CandidatarVagaRepository;
import br.com.unialfa.ciep.repository.PessoaFisicaRepository;
import br.com.unialfa.ciep.repository.VagasRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CandidatarVagaBusiness {
    private final CandidatarVagaRepository candidatarVagaRepository;
    private final VagasRepository vagasRepository;
    private final PessoaFisicaRepository pessoaFisicaRepository;

    public CandidatarVagaBusiness(CandidatarVagaRepository candidatarVagaRepository, VagasRepository vagasRepository, PessoaFisicaRepository pessoaFisicaRepository) {
        this.candidatarVagaRepository = candidatarVagaRepository;
        this.vagasRepository = vagasRepository;
        this.pessoaFisicaRepository = pessoaFisicaRepository;
    }
    public ResponseEntity<?> candidatarVaga(CandidatarVagaDAO candidatarVagaDAO) {

        try {
            CandidatarVaga candidatarVaga = new CandidatarVaga();
            candidatarVaga.setCandidatar(candidatarVagaDAO.isCandidatar());
            Optional<PessoaFisica> pessoaFisica = pessoaFisicaRepository.findById(candidatarVagaDAO.getPessoaFisica_id());
            candidatarVaga.setPessoaFisica(pessoaFisica.get());
            Optional<Vagas> vagasEmprego = vagasRepository.findById(candidatarVagaDAO.getVagasEmprego_id());
            candidatarVaga.setVagas(vagasEmprego.get());
            return new ResponseEntity<>(candidatarVagaRepository.save(candidatarVaga), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }

    }
}
