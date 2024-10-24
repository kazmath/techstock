package br.com.techhub.techstock.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonValue;

import br.com.techhub.techstock.controller.requests.TicketRequest;
import br.com.techhub.techstock.model.enums.TicketStatus;
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
@Table(name = "ticket")
@EqualsAndHashCode(of = {
    "id"
}, callSuper = true)
public class Ticket extends BaseModel {

    @Id
    @JsonValue
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date dt_reserva;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date dt_devolucao;

    @Column(nullable = true, length = 255)
    private String observacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TicketStatus status = TicketStatus.AGUARDANDO;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_equipamento", nullable = false)
    private Equipamento equipamento;

    /**
     * @param id
     */
    public Ticket(Long id) {
        super();
        this.id = id;
    }

    /**
     * @param request
     */
    public Ticket(TicketRequest request) {
        super();
        this.id = request.getId();
        this.dt_reserva = request.getDt_reserva();
        this.dt_devolucao = request.getDt_devolucao();
        this.observacao = request.getObservacao();
        this.status = request.getStatus();
        this.usuario = request.getUsuario();
        this.equipamento = request.getEquipamento();
    }

}
