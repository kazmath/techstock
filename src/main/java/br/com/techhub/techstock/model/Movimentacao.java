package br.com.techhub.techstock.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonValue;

import br.com.techhub.techstock.controller.requests.MovimentacaoRequest;
import br.com.techhub.techstock.model.enums.MovimentacaoTipo;
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
@Table(name = "movimentacao")
@EqualsAndHashCode(of = {
    "id"
}, callSuper = true)
public class Movimentacao extends BaseModel {

    @Id
    @JsonValue
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private MovimentacaoTipo tipo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_movimentacao", nullable = false)
    private Date data;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ticket", nullable = false)
    private Ticket ticket;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario_adm", nullable = true)
    private Usuario usuarioAdm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    /**
     * @param id
     */
    public Movimentacao(Long id) {
        super();
        this.id = id;
    }

    /**
     * @param request
     */
    public Movimentacao(MovimentacaoRequest request) {
        super();
        this.id = request.getId();
        this.tipo = request.getTipo();
        this.data = request.getData();
        this.ticket = request.getTicket();
        this.usuario = request.getUsuario();
    }

}
