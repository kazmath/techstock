package br.com.techhub.techstock.controller.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SetorRequest implements IRequest {

    private Long id;
    // TODO: Add as outras colunas

}
