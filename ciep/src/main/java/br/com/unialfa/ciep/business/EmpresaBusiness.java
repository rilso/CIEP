package br.com.unialfa.ciep.business;
import br.com.unialfa.ciep.DAO.EmpresaDAO;
import br.com.unialfa.ciep.domain.Usuario;
import br.com.unialfa.ciep.domain.Empresa;
import br.com.unialfa.ciep.repository.EmpresaRepository;
import br.com.unialfa.ciep.repository.VagasRepository;
import org.springframework.stereotype.Service;
import br.com.unialfa.ciep.repository.UsuarioRepository;
import br.com.unialfa.ciep.repository.EnderecoRepository;

@Service
public class EmpresaBusiness {


    private final EnderecoRepository enderecoRepository;
    private final EmpresaRepository empresaRepository;
    private final UsuarioRepository usuarioRepository;
    private final VagasRepository vagasRepository;


    public EmpresaBusiness(EnderecoRepository enderecoRepository, EmpresaRepository empresaRepository, UsuarioRepository usuarioRepository, VagasRepository vagasRepository) {
        this.enderecoRepository = enderecoRepository;
        this.empresaRepository = empresaRepository;
        this.usuarioRepository = usuarioRepository;
        this.vagasRepository = vagasRepository;
    }

    public Empresa salvarEmpresa(EmpresaDAO empresaDAO) {
        Usuario usuario = usuarioRepository.findByEmail(empresaDAO.getUsuario().getEmail());
        Empresa empresa = new Empresa();
        empresa.setNomeFant(empresaDAO.getNomeFant());
        empresa.setRazaoSocial(empresaDAO.getRazaoSocial());
        empresa.setCnpj(empresaDAO.getCnpj());
        empresa.setUsuario(usuario);
        return empresaRepository.save(empresa);

    }

    public Empresa listarEmpresa(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        Empresa empresa = empresaRepository.findByUsuarioId(usuario.getId());
        return empresa;
    }
}





