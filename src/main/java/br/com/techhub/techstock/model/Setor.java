package br.com.techhub.techstock.model;

import com.fasterxml.jackson.annotation.JsonValue;

import br.com.techhub.techstock.controller.requests.SetorRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "setor")
@EqualsAndHashCode(of = {
    "id"
}, callSuper = true)
public class Setor extends BaseModel {

    @Id
    @JsonValue
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(length = 100, nullable = false)
    private String nome;

    /**
     * @param id
     */
    public Setor(Long id) {
        super();
        this.id = id;
    }

    /**
     * @param request
     */
    public Setor(SetorRequest request) {
        super();
        this.id = request.getId();
        this.nome = request.getNome();
    }

}
