package br.com.unialfa.ciep.repository;

import br.com.unialfa.ciep.domain.Empresa;
import org.springframework.data.repository.CrudRepository;

public interface EmpresaRepository  extends CrudRepository<Empresa, Long> {
    Empresa findByUsuarioId(long id);
}
