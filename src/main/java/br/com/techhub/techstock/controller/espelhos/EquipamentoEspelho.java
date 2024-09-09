package br.com.techhub.techstock.controller.espelhos;

import java.util.Date;

import br.com.techhub.techstock.model.Equipamento;
import br.com.techhub.techstock.model.enums.EquipamentoStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquipamentoEspelho implements IEspelho {

    private Long id;
    private String            nome;
    private String            descricao;
    private String            fabricante;
    private String            modelo;
    private Date              anoFabricacao;
    private EquipamentoStatus status;
    private Date              dtEntrada;
    private Date              dtSaida;
    private Long              categoriaId;

    public EquipamentoEspelho(Equipamento equipamento) {
        this.id = equipamento.getId();
        this.nome = equipamento.getNome();
        this.descricao = equipamento.getDescricao();
        this.fabricante = equipamento.getFabricante();
        this.modelo = equipamento.getModelo();
        this.anoFabricacao = equipamento.getAno_fabricacao();
        this.status = equipamento.getStatus();
        this.dtEntrada = equipamento.getDt_entrada();
        this.dtSaida = equipamento.getDt_saida();
        this.categoriaId = equipamento.getCategoria().getId();
    }

}
