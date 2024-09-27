package br.com.techhub.techstock.model;


import java.util.List;

import br.com.techhub.techstock.model.enums.UsuarioPerfil;
import com.fasterxml.jackson.annotation.JsonValue;

import br.com.techhub.techstock.controller.requests.UsuarioRequest;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "usuario")
@EqualsAndHashCode(of = {
    "id"
}, callSuper = true)
public class Usuario extends BaseModel {

    @Id
    @JsonValue
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(length = 15, nullable = false)
    private String codigo;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(name = "senha_hash", nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    private UsuarioPerfil usuarioPerfil;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "setor_id", nullable = false)
    private Setor setor;

    /**
     * @param id
     */
    public Usuario(Long id) {
        super();
        this.id = id;
    }

    /**
     * @param request
     */
    public Usuario(UsuarioRequest request) {
        super();
        this.id = request.getId();
        this.codigo = request.getCodigo();
        this.nome = request.getNome();
        this.email = request.getEmail();
        this.senha = request.getSenha();
        this.usuarioPerfil = request.getUsuarioPerfil();
        this.setor = request.getSetor();
    }

}
