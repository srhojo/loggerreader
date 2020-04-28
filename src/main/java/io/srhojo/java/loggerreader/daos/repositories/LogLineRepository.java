package io.srhojo.java.loggerreader.daos.repositories;

import io.srhojo.java.loggerreader.daos.entities.LogLineEntity;
import io.srhojo.java.loggerreader.daos.entities.LogTypeEntityEnum;
import io.srhojo.java.loggerreader.daos.entities.ProjectEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LogLineRepository extends CrudRepository<LogLineEntity, Long> {

    List<LogLineEntity> findByProjectAndLogType(ProjectEntity project, LogTypeEntityEnum logType);
}
