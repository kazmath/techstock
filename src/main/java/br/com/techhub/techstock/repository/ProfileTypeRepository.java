package br.com.techhub.techstock.repository;

import br.com.techhub.techstock.model.ProfileType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileTypeRepository extends JpaRepository<ProfileType, Long> {
}
