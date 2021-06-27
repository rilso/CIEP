package br.com.unialfa.ciep.business;
import br.com.unialfa.ciep.DAO.PessoaFisicaDAO;
import br.com.unialfa.ciep.domain.PessoaFisica;
import br.com.unialfa.ciep.domain.Usuario;
import br.com.unialfa.ciep.repository.PessoaFisicaRepository;
import org.springframework.stereotype.Service;
import br.com.unialfa.ciep.repository.UsuarioRepository;

@Service
public class PessoaFisicaBusiness {

    private final PessoaFisicaRepository pessoaFisicaRepository;
    private final UsuarioRepository usuarioRepository;

    public PessoaFisicaBusiness(PessoaFisicaRepository pessoaFisicaRepository,
                                UsuarioRepository usuarioRepository) {
        this.pessoaFisicaRepository = pessoaFisicaRepository;
        this.usuarioRepository = usuarioRepository;
    }


    public PessoaFisica salvarPessoaFisica(PessoaFisicaDAO pessoaFisicaDAO) {
        Usuario usuario = usuarioRepository.findByEmail(pessoaFisicaDAO.getUsuario().getEmail());
        PessoaFisica pessoaFisica = new PessoaFisica();
        pessoaFisica.setNome(pessoaFisicaDAO.getNome());
        pessoaFisica.setIdade(pessoaFisicaDAO.getIdade());
        pessoaFisica.setSexo(pessoaFisicaDAO.getSexo());
        pessoaFisica.setCpf(pessoaFisicaDAO.getCpf());
        pessoaFisica.setDescricao(pessoaFisicaDAO.getDescricao());
        pessoaFisica.setUsuario(usuario);
        return pessoaFisicaRepository.save(pessoaFisica);
    }

    public PessoaFisica listarPessoa(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        PessoaFisica pessoaFisica = pessoaFisicaRepository.findByUsuarioId(usuario.getId());
        return pessoaFisica;
    }


}
