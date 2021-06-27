package br.com.unialfa.ciep.business;

import br.com.unialfa.ciep.DAO.VagasDAO;
import br.com.unialfa.ciep.domain.Empresa;
import br.com.unialfa.ciep.domain.Usuario;
import br.com.unialfa.ciep.domain.Vagas;
import br.com.unialfa.ciep.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import br.com.unialfa.ciep.repository.EmpresaRepository;
import br.com.unialfa.ciep.repository.VagasRepository;

@Service
public class VagasBusiness {

    private final VagasRepository vagasRepository;
    private final EmpresaRepository empresaRepository;
    private final UsuarioRepository usuarioRepository;

    public VagasBusiness(VagasRepository vagasRepository, EmpresaRepository empresaRepository, UsuarioRepository usuarioRepository) {
        this.vagasRepository = vagasRepository;
        this.empresaRepository = empresaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public ResponseEntity<?> salvarVagasEmprego(VagasDAO vagasDAO, String email) {
        try {
            Vagas vagas = new Vagas();
            vagas.setNomeEmpresa(vagasDAO.getNomeEmpresa());
            vagas.setDescricao(vagasDAO.getDescricao());
            vagas.setSalario(vagasDAO.getSalario());
            vagas.setEscolaridade(vagasDAO.getEscolaridade());
            Usuario usuario = usuarioRepository.findByEmail(email);
            Empresa empresa = empresaRepository.findByUsuarioId(usuario.getId());
            vagas.setEmpresa(empresa);
            return new ResponseEntity<>(vagasRepository.save(vagas),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    public Iterable<Vagas> listarVagasEmpresa(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        Empresa empresa = empresaRepository.findByUsuarioId(usuario.getId());
        if(empresa == null){
            return null;
        }
        return empresa.getVagas();
    }

    public ResponseEntity<?> deletarVagaEmprego(long id) {
        try {
            vagasRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }
}
