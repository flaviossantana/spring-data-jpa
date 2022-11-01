package io.com.rh.repository;

import com.github.javafaker.Faker;
import io.com.rh.entity.Cargo;
import io.com.rh.entity.Funcionario;
import io.com.rh.entity.Unidade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FuncionarioRepositoryTest {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private UnidadeRepository unidadeRepository;

    private Faker faker = new Faker(new Locale("pt-br"));

    @Test
    void deveriaSalvarFuncionario() {

        Cargo cargo = new Cargo();
        cargo.setDescricao(faker.job().title());
        cargoRepository.save(cargo);

        Unidade unidade = new Unidade();
        unidade.setDescricao(faker.address().streetAddress());
        unidade.setEndereco(faker.address().fullAddress());
        unidadeRepository.save(unidade);

        Funcionario funcionario = new Funcionario();
        funcionario.setCpf(faker.idNumber().valid());
        funcionario.setNome(faker.name().fullName());
        funcionario.setDataContratacao(LocalDateTime.now());
        funcionario.setSalario(new BigDecimal(faker.number().randomDouble(2, 1000, 5000)));
        funcionario.setCargo(cargo);
        funcionario.addUnidade(unidade);
        funcionarioRepository.save(funcionario);

    }

}
