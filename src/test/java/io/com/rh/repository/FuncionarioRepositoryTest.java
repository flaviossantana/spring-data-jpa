package io.com.rh.repository;

import io.com.rh.entity.Cargo;
import io.com.rh.entity.Funcionario;
import io.com.rh.entity.Unidade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FuncionarioRepositoryTest {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private UnidadeRepository unidadeRepository;

    @Test
    void deveriaSalvarFuncionario() {

        Cargo cargo = new Cargo();
        cargo.setDescricao("Desenvolvedor");
        cargoRepository.save(cargo);

        Unidade unidade = new Unidade();
        unidade.setDescricao("Unidade 1");
        unidade.setEndereco("Rua 1");
        unidadeRepository.save(unidade);

        Funcionario funcionario = new Funcionario();
        funcionario.setCpf("99547785120");
        funcionario.setNome("Fulano de Tals");
        funcionario.setDataContratacao(LocalDateTime.now());
        funcionario.setSalario(new BigDecimal("1200.00"));
        funcionario.setCargo(cargo);
        funcionario.setUnidade(unidade);
        funcionarioRepository.save(funcionario);

    }

}
