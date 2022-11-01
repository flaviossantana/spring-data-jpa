package io.com.rh.entity;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    @JoinColumn(name = "cargo_id", nullable = false)
    private Cargo cargo;

    @Fetch(FetchMode.SELECT)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "funcionarios_unidades",
            joinColumns = @JoinColumn(name = "funcionario_id"), inverseJoinColumns = @JoinColumn(name = "unidade_id"))
    private List<Unidade> unidades = new ArrayList<>();

    public void addUnidade(Unidade unidade) {
        this.unidades.add(unidade);
    }

}
