package br.com.techhub.techstock.controller.requests;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.techhub.techstock.model.Categoria;
import br.com.techhub.techstock.model.enums.EquipamentoStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EquipamentoRequest implements IRequest {

    @JsonIgnore
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    @NotBlank
    private String fabricante;

    @NotBlank
    private String modelo;

    @NotNull
    @JsonFormat(pattern = "yyyy")
    private Date ano_fabricacao;

    @NotBlank
    private EquipamentoStatus status;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dt_entrada;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dt_saida;

    @NotNull
    @JsonAlias("categoriaId")
    private Categoria categoria;

}
