package io.srhojo.java.loggerreader.services;

import io.srhojo.java.loggerreader.api.domain.LogLine;
import io.srhojo.java.loggerreader.api.domain.Project;

import java.util.List;

public interface LogReaderService {

    // Función para incializar las demos
    void init();

    List<Project> getProjects();

    Project getProjectLogs(Long idProject);

    List<LogLine> getProjectLogsByType(Long idProject, String logType);


}
