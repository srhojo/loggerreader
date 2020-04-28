package io.srhojo.java.loggerreader.controllers;

import io.srhojo.java.loggerreader.api.LogReaderApi;
import io.srhojo.java.loggerreader.api.domain.ContainerList;
import io.srhojo.java.loggerreader.api.domain.LogTypeEnum;
import io.srhojo.java.loggerreader.api.domain.Project;
import io.srhojo.java.loggerreader.services.LogReaderService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogReaderController implements LogReaderApi {

    private final LogReaderService logReaderService;

    public LogReaderController(final LogReaderService logReaderService) {
        this.logReaderService = logReaderService;
    }

    @Override
    public void initTest() {
        logReaderService.init();
    }

    @Override
    public ContainerList getAllProjects() {
        return null;
    }

    @Override
    public Project getProjectById(final Long id) {
        return null;
    }

    @Override
    public ContainerList getLogLinesByProjectAndLogType(final Long projectId, final LogTypeEnum logType) {
        return null;
    }
}
