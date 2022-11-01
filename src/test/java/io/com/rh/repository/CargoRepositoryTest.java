package io.com.rh.repository;

import com.github.javafaker.Faker;
import io.com.rh.entity.Cargo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Locale;
import java.util.Optional;

@SpringBootTest
class CargoRepositoryTest {

    @Autowired
    private CargoRepository cargoRepository;

    private Faker faker = new Faker(new Locale("pt-br"));

    @Test
    void deveriaSalvarEbuscarPorID() {

        Cargo cargo = new Cargo();
        cargo.setDescricao(faker.job().title());

        cargoRepository.save(cargo);

        Optional<Cargo> cargoBD = cargoRepository.findById(cargo.getId());
        Assertions.assertTrue(cargoBD.isPresent());
        cargoBD.ifPresent(c -> Assertions.assertEquals(cargo.getDescricao(), c.getDescricao()));

    }

    @Test
    void deveriaAtualizarUmCargo() {

        Cargo cargo = new Cargo();
        cargo.setDescricao(faker.job().title());

        cargoRepository.save(cargo);

        Optional<Cargo> cargoBD = cargoRepository.findById(cargo.getId());
        Assertions.assertTrue(cargoBD.isPresent());
        cargoBD.ifPresent(c -> Assertions.assertEquals(cargo.getDescricao(), c.getDescricao()));

        cargoBD.ifPresent((c) -> {
            c.setDescricao(faker.job().title());
            cargoRepository.save(c);

            cargoRepository.findById(c.getId()).ifPresent((c2) -> {
                Assertions.assertEquals(c.getDescricao(), c2.getDescricao());
            });

        });
    }

    @Test
    void deveriaExcluircargo() {

        Cargo cargo = new Cargo();
        cargo.setDescricao(faker.job().title());

        cargoRepository.save(cargo);

        Optional<Cargo> cargoBD = cargoRepository.findById(cargo.getId());
        Assertions.assertTrue(cargoBD.isPresent());
        cargoBD.ifPresent(c -> Assertions.assertEquals(cargo.getDescricao(), c.getDescricao()));

        cargoRepository.deleteById(cargo.getId());

        cargoBD = cargoRepository.findById(cargo.getId());
        Assertions.assertFalse(cargoBD.isPresent());
    }

}
