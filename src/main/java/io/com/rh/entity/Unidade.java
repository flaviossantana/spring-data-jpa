package io.com.rh.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "unidades")
public class Unidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "desc_descricao", nullable = false, length = 100)
    private String descricao;

    @Column(name = "desc_endereco", nullable = false, length = 200)
    private String endereco;

    @ManyToMany(mappedBy = "unidades")
    private List<Funcionario> funcionarios = new ArrayList<>();


}
