package io.com.rh.repository;

import io.com.rh.entity.Cargo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class CargoRepositoryTest {

    @Autowired
    private CargoRepository cargoRepository;

    @Test
    void deveriaSalvarEbuscarPorID() {

        Cargo cargo = new Cargo();
        cargo.setDescricao("Desenvolvedor");

        cargoRepository.save(cargo);

        Optional<Cargo> cargoBD = cargoRepository.findById(cargo.getId());
        Assertions.assertTrue(cargoBD.isPresent());
        cargoBD.ifPresent(c -> Assertions.assertEquals(cargo.getDescricao(), c.getDescricao()));

    }

    @Test
    void deveriaAtualizarUmCargo() {

        Cargo cargo = new Cargo();
        cargo.setDescricao("Desenvolvedor");

        cargoRepository.save(cargo);

        Optional<Cargo> cargoBD = cargoRepository.findById(cargo.getId());
        Assertions.assertTrue(cargoBD.isPresent());
        cargoBD.ifPresent(c -> Assertions.assertEquals(cargo.getDescricao(), c.getDescricao()));

        cargoBD.ifPresent((c) -> {
            c.setDescricao("Desenvolvedor Java");
            cargoRepository.save(c);

            cargoRepository.findById(c.getId()).ifPresent((c2) -> {
                Assertions.assertEquals("Desenvolvedor Java", c2.getDescricao());
            });

        });
    }

    @Test
    void deveriaExcluircargo() {

        Cargo cargo = new Cargo();
        cargo.setDescricao("Desenvolvedor");

        cargoRepository.save(cargo);

        Optional<Cargo> cargoBD = cargoRepository.findById(cargo.getId());
        Assertions.assertTrue(cargoBD.isPresent());
        cargoBD.ifPresent(c -> Assertions.assertEquals(cargo.getDescricao(), c.getDescricao()));

        cargoRepository.deleteById(cargo.getId());

        cargoBD = cargoRepository.findById(cargo.getId());
        Assertions.assertFalse(cargoBD.isPresent());
    }

}
