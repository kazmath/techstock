package br.com.techhub.techstock.controller.espelhos;

import br.com.techhub.techstock.model.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioEspelho implements IEspelho {

    private Long id;
    // TODO: Add as outras colunas

    public UsuarioEspelho(Usuario usuario) {
        throw new UnsupportedOperationException();
    }

}
