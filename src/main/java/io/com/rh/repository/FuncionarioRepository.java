package io.com.rh.repository;

import io.com.rh.entity.Funcionario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FuncionarioRepository  extends CrudRepository<Funcionario, Long> {

    List<Funcionario> findByNome(String nome);
    @Query("SELECT f FROM Funcionario f JOIN FETCH f.cargo c WHERE f.nome = :nome")
    List<Funcionario> findByNomeFetchCliente(String nome);
    List<Funcionario> findBySalarioGreaterThanAndDataContratacaoLessThan(BigDecimal salario, LocalDateTime dataContratacao);

}
