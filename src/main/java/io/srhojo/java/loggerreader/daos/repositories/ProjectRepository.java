package io.srhojo.java.loggerreader.daos.repositories;

import io.srhojo.java.loggerreader.daos.entities.ProjectEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProjectRepository extends CrudRepository<ProjectEntity, Long> {

    Optional<ProjectEntity> findByName(String name);

}
