package br.com.techhub.techstock.controller.espelhos;

import br.com.techhub.techstock.model.Categoria;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaEspelho implements IEspelho {

    private Long id;
    // TODO: Add as outras colunas

    public CategoriaEspelho(Categoria categoria) {
        throw new UnsupportedOperationException();
    }

}
