package br.com.techhub.techstock.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.ListCrudRepository;

import br.com.techhub.techstock.model.BaseModel;

public class BaseService<M extends BaseModel, T extends ListCrudRepository<M, Long>> {

    @Autowired
    public T repository;

    public M save(M entity) {
        return repository.save(entity);
    }

    public Optional<M> findById(Long id) {
        return repository.findById(id);
    }

    public void delete(M entity) {
        try {
            repository.delete(entity);
        } catch (Exception e) {
            if (!(e instanceof DataIntegrityViolationException)) {
                throw e;
            }

            String constraintName = ((ConstraintViolationException) e
                .getCause()).getConstraintName();


            if (constraintName.equals("fk_equipamento_usuario")) {
                throw new ServiceException(
                    "Erro ao tentar excluir o usuário. O usuário é tratativa de algum equipamento."
                );
            }
        }
    }

    public void saveAll(Iterable<M> entities) {
        repository.saveAll(entities);
    }

    public List<M> findAll() {
        return repository.findAll();
    }

}
