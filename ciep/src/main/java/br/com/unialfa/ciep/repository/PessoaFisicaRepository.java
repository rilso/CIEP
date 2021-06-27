package br.com.unialfa.ciep.repository;

import br.com.unialfa.ciep.domain.PessoaFisica;
import org.springframework.data.repository.CrudRepository;

public interface PessoaFisicaRepository extends CrudRepository<PessoaFisica, Long> {
    PessoaFisica findByUsuarioId(long id);
}
