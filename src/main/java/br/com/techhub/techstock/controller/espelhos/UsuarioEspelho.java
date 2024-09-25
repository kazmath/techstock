package br.com.techhub.techstock.controller.espelhos;

import java.util.List;

import br.com.techhub.techstock.model.ProfileType;
import br.com.techhub.techstock.model.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioEspelho implements IEspelho {

    private Long              id;
    private String            codigo;
    private String            nome;
    private String            email;
    private String            senha;
    private List<ProfileType> profileTypes;
    private Long              setorId;

    public UsuarioEspelho(Usuario usuario) {
        this.id = usuario.getId();
        this.codigo = usuario.getCodigo();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
        this.profileTypes = usuario.getProfileTypes();
        this.setorId = usuario.getSetor().getId();
    }

}
