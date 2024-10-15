package br.com.techhub.techstock.model;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonValue;

import br.com.techhub.techstock.controller.requests.UsuarioRequest;
import br.com.techhub.techstock.model.enums.UsuarioTipo;
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

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UsuarioTipo usuarioTipo = UsuarioTipo.USER;

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
        this.usuarioTipo = request.getUsuarioTipo();
        this.setor = request.getSetor();
    }

    /**
    * @param senha the senha to set
    */
    public void setSenha(String senha) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(senha);

        this.senha = encryptedPassword;
    }


}
