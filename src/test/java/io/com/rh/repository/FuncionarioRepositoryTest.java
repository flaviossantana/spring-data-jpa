package io.com.rh.repository;

import com.github.javafaker.Faker;
import io.com.rh.entity.Cargo;
import io.com.rh.entity.Funcionario;
import io.com.rh.entity.Unidade;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FuncionarioRepositoryTest {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private UnidadeRepository unidadeRepository;

    private final Faker faker = new Faker(new Locale("pt-br"));

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
        funcionario.setSalario(new BigDecimal(faker.number().randomNumber(5, true)));
        funcionario.setCargo(cargo);
        funcionario.addUnidade(unidade);
        funcionarioRepository.save(funcionario);

        assertNotNull(funcionario.getId());
    }

    @Test
    void deveriaBuscarFuncionarioPorNone() {

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
        funcionario.setSalario(new BigDecimal(faker.number().randomNumber(5, true)));
        funcionario.setCargo(cargo);
        funcionario.addUnidade(unidade);
        funcionarioRepository.save(funcionario);


        List<Funcionario> buscaPorNome = funcionarioRepository.findByNome(funcionario.getNome());
        assertNotNull(buscaPorNome);
    }

    @Test
    void deveriaDarExcecaoDeLazyInitializationExceptionAoDarGetEmCargo() {

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
        funcionario.setSalario(new BigDecimal(faker.number().randomNumber(5, true)));
        funcionario.setCargo(cargo);
        funcionario.addUnidade(unidade);
        funcionarioRepository.save(funcionario);


        List<Funcionario> buscaPorNome = funcionarioRepository.findByNome(funcionario.getNome());
        assertNotNull(buscaPorNome);

        Optional<Funcionario> first = buscaPorNome.stream().findFirst();

        if(first.isPresent()){
            Cargo cargoLIE = first.get().getCargo();
            try {
                fail(cargoLIE.getDescricao());
            }catch (LazyInitializationException e){
                assertTrue(true);
            }

        }

    }


    @Test
    void deveriaBuscarFuncionarioPorNoneComFetchEmCliente() {

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
        funcionario.setSalario(new BigDecimal(faker.number().randomNumber(5, true)));
        funcionario.setCargo(cargo);
        funcionario.addUnidade(unidade);
        funcionarioRepository.save(funcionario);


        List<Funcionario> buscaPorNome = funcionarioRepository.findByNomeFetchCliente(funcionario.getNome());
        assertNotNull(buscaPorNome);
        assertNotNull(buscaPorNome.get(0).getCargo());
        assertEquals(buscaPorNome.get(0).getCargo().getDescricao(), cargo.getDescricao());
    }

    @Test
    void findByNomeAndSalarioGreaterThanAndDataContratacao() {

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
        funcionario.setSalario(new BigDecimal(faker.number().randomNumber(5, true)));
        funcionario.setCargo(cargo);
        funcionario.addUnidade(unidade);
        funcionarioRepository.save(funcionario);

        Funcionario funcionarioForaFiltro = new Funcionario();
        funcionarioForaFiltro.setCpf(faker.idNumber().valid());
        funcionarioForaFiltro.setNome(faker.name().fullName());
        funcionarioForaFiltro.setDataContratacao(LocalDateTime.now());
        funcionarioForaFiltro.setSalario(new BigDecimal("999.00"));
        funcionarioForaFiltro.setCargo(cargo);
        funcionarioForaFiltro.addUnidade(unidade);
        funcionarioRepository.save(funcionarioForaFiltro);

        List<Funcionario> funcionarios = funcionarioRepository.findBySalarioGreaterThanAndDataContratacaoLessThan(new BigDecimal(100), LocalDateTime.now());
        assertNotNull(funcionarios);

    }

    @Test
    void deveriaBuscarTodasContratacoesMenorQueADataInformada(){
        List<Funcionario> funcionarios = funcionarioRepository.buscarTodosComDataContratacaoMaiorQue(LocalDateTime.now());
        assertNotNull(funcionarios);
    }

}
