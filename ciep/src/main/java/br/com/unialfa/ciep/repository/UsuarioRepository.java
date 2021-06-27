package br.com.unialfa.ciep.repository;

import br.com.unialfa.ciep.domain.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario,Long> {
    Usuario findByEmail(String email);
}
