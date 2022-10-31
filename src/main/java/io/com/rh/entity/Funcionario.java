package io.com.rh.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "funcionarios")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "desc_nome", nullable = false, length = 60)
    private String nome;

    @Column(name = "numr_cpf", nullable = false, length = 11)
    private String cpf;

    @Column(name = "numr_salario", nullable = false, length = 16)
    private BigDecimal salario;

    @Column(name = "dt_contratacao", nullable = false)
    private LocalDateTime dataContratacao;

    @ManyToOne(fetch = FetchType.LAZY)
    private Unidade unidade;

    @OneToOne(fetch = FetchType.LAZY)
    private Cargo cargo;


}
