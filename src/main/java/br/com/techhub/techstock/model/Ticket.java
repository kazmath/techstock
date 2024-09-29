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
    @Column(nullable = false, length = 50)
    private TicketStatus status = TicketStatus.PROCESSANDO;

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
        this.id = getId();
        this.dt_reserva = getDt_reserva();
        this.dt_devolucao = getDt_devolucao();
        this.observacao = getObservacao();
        this.status = getStatus();
        this.usuario = getUsuario();
        this.equipamento = getEquipamento();
    }

}
