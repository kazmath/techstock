package br.com.techhub.techstock.model;

import java.util.Date;

import br.com.techhub.techstock.model.enums.EquipamentoStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "equipamento")
@EqualsAndHashCode(of = {
    "id"
}, callSuper = true)
public class Equipamento extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 300, nullable = true)
    private String descricao;

    @Column(length = 100, nullable = true)
    private String fabricante;

    @Column(length = 100, nullable = true)
    private String modelo;

    @Temporal(TemporalType.DATE)
    @Column(nullable = true)
    private Date ano_fabricacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private EquipamentoStatus status = EquipamentoStatus.DISPONIVEL;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date dt_entrada;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true)
    private Date dt_saida;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;

}
