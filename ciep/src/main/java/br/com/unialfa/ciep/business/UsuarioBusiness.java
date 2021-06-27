package br.com.unialfa.ciep.business;

import br.com.unialfa.ciep.domain.Usuario;
import br.com.unialfa.ciep.domain.Endereco;
import br.com.unialfa.ciep.repository.UsuarioRepository;
import br.com.unialfa.ciep.repository.EnderecoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UsuarioBusiness {

    private final UsuarioRepository usuarioRepository;
    private final EnderecoRepository enderecoRepository;

    public UsuarioBusiness(UsuarioRepository usuarioRepository,
                           EnderecoRepository enderecoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.enderecoRepository = enderecoRepository;
    }

    public ResponseEntity<?> salvar(Usuario usuario) {
        try {
            Endereco endereco = new Endereco();
            endereco.setRua(usuario.getEndereco().getRua());
            endereco.setBairro(usuario.getEndereco().getBairro());
            endereco.setEstado(usuario.getEndereco().getEstado());
            endereco.setComplemento(usuario.getEndereco().getComplemento());
            endereco = enderecoRepository.save(endereco);
            usuario.setEndereco(endereco);
            return new ResponseEntity<>(usuarioRepository.save(usuario), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }
}

