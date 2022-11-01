package io.com.rh.repository;

import io.com.rh.entity.Funcionario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository  extends CrudRepository<Funcionario, Long> {
}
