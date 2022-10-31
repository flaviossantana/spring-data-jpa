package io.com.rh.entidade;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cargos")
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "desc_cargo", nullable = false, length = 60)
    private String descricao;

}
