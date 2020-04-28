package io.srhojo.java.loggerreader.daos;

import io.srhojo.java.loggerreader.daos.entities.LogLineEntity;
import io.srhojo.java.loggerreader.daos.entities.LogTypeEntityEnum;
import io.srhojo.java.loggerreader.daos.entities.ProjectEntity;

import java.util.List;

public interface LoggerDao {

    ProjectEntity saveProjectLogs(ProjectEntity project);

    ProjectEntity getProject(Long id);

    List<LogLineEntity> getProjectLogsByType(long idProject, LogTypeEntityEnum logType);

    List<ProjectEntity> getAllProjects();

}
