package br.com.techhub.techstock.repository;

import java.util.List;

import br.com.techhub.techstock.controller.filters.IFilter;
import br.com.techhub.techstock.model.BaseModel;

public interface RFilter<E extends BaseModel, F extends IFilter> {

    List<E> filterBy(F filter);

}
