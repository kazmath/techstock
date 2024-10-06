package br.com.techhub.techstock.controller.requests;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.techhub.techstock.model.Categoria;
import br.com.techhub.techstock.model.Usuario;
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

    @NotNull
    private String tombamento;

    @NotBlank
    private String nome;

    @NotBlank
    private String fabricante;

    @JsonFormat(pattern = "yyyy")
    private Date ano_fabricacao;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dt_saida;

    private String            descricao;
    private String            modelo;
    private EquipamentoStatus status;

    private Usuario UsuarioComEquipamento;

    @NotNull
    @JsonAlias("categoriaId")
    private Categoria categoria;

}
