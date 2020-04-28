package io.srhojo.java.loggerreader.daos.impl;

import io.srhojo.java.loggerreader.daos.LoggerDao;
import io.srhojo.java.loggerreader.daos.entities.LogLineEntity;
import io.srhojo.java.loggerreader.daos.entities.LogTypeEntityEnum;
import io.srhojo.java.loggerreader.daos.entities.ProjectEntity;
import io.srhojo.java.loggerreader.daos.repositories.LogLineRepository;
import io.srhojo.java.loggerreader.daos.repositories.ProjectRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LoggerDaoImpl implements LoggerDao {

    private final ProjectRepository projectRepository;
    private final LogLineRepository logLineRepository;

    public LoggerDaoImpl(final ProjectRepository projectRepository, final LogLineRepository logLineRepository) {
        this.projectRepository = projectRepository;
        this.logLineRepository = logLineRepository;
    }

    @Override
    public ProjectEntity saveProjectLogs(final ProjectEntity project) {

        projectRepository.findByName(project.getName())
                .ifPresent(previous -> {
                    project.setId(previous.getId());
                    project.setCreatedAt(previous.getCreatedAt());
                    //Eliminar los logs previos del proyecto
                    deleteLogLines(previous.getLoglines());
                });

        return projectRepository.save(project);
    }

    @Override
    public ProjectEntity getProject(final Long id) {
        return projectRepository.findById(id).orElseThrow(() -> new RuntimeException("Project not found"));
    }

    @Override
    public List<LogLineEntity> getProjectLogsByType(long idProject, LogTypeEntityEnum logType) {
        final ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(idProject);
        return logLineRepository.findByProjectAndLogType(projectEntity,logType);
    }

    @Override
    public List<ProjectEntity> getAllProjects() {
        return (List<ProjectEntity>) projectRepository.findAll();
    }


    private void deleteLogLines(List<LogLineEntity> logLines) {
        logLineRepository.deleteAll(logLines);
    }


}
