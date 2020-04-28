package io.srhojo.java.loggerreader.api;

import io.srhojo.java.loggerreader.api.domain.ContainerList;
import io.srhojo.java.loggerreader.api.domain.LogTypeEnum;
import io.srhojo.java.loggerreader.api.domain.Project;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

public interface LogReaderApi {

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/api/v1/init")
    void initTest();

    @GetMapping("/api/v1/projects")
    ContainerList getAllProjects();

    @GetMapping("/api/v1/projects/{id}")
    Project getProjectById(@PathVariable("id") Long id);

    @GetMapping("/api/v1/projects/{id}/logs")
    ContainerList getLogLinesByProjectAndLogType(@PathVariable("id") Long projectId, @RequestParam("type") LogTypeEnum logType);

}
