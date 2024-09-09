package br.com.techhub.techstock.controller.espelhos;

import br.com.techhub.techstock.model.Categoria;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaEspelho implements IEspelho {

    private Long   id;
    private String nome;
    private String descricao;

    public CategoriaEspelho(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
        this.descricao = categoria.getDescricao();
    }

}
