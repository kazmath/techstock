package br.com.techhub.techstock.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.ListCrudRepository;

import br.com.techhub.techstock.model.BaseModel;

public class BaseService<M extends BaseModel, T extends ListCrudRepository<M, Long>> {

    @Autowired
    private T repository;

    public M save(M entity) {
        return repository.save(entity);
    }

    public Optional<M> findById(Long id) {
        return repository.findById(id);
    }

    public void delete(M entity) {
        repository.delete(entity);
    }

    public void saveAll(Iterable<M> entities) {
        repository.saveAll(entities);
    }

    public List<M> findAll() {
        return repository.findAll();
    }

}
