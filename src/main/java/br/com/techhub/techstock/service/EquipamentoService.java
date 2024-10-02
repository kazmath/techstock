package br.com.techhub.techstock.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.techhub.techstock.controller.filters.EquipamentoFiltro;
import br.com.techhub.techstock.model.Equipamento;
import br.com.techhub.techstock.repository.EquipamentoRepository;

@Service
public class EquipamentoService extends BaseService<Equipamento, EquipamentoRepository> {

    public List<Equipamento> filterBy(EquipamentoFiltro filtro) {
        return repository.filterBy(filtro);
    }
}
