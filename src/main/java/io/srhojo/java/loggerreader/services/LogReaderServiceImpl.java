package io.srhojo.java.loggerreader.services;

import io.srhojo.java.loggerreader.api.domain.LogLine;
import io.srhojo.java.loggerreader.api.domain.Project;
import io.srhojo.java.loggerreader.daos.LoggerDao;
import io.srhojo.java.loggerreader.daos.entities.LogTypeEntityEnum;
import io.srhojo.java.loggerreader.daos.entities.ProjectEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class LogReaderServiceImpl implements LogReaderService {


    private final ReaderLogFile readerLogFile;
    private final LoggerDao loggerDao;

    public LogReaderServiceImpl(final ReaderLogFile readerLogFile, final LoggerDao loggerDao) {
        this.readerLogFile = readerLogFile;
        this.loggerDao = loggerDao;
    }



    @Override
    public void init() {
        final File file = new File(this.getClass().getClassLoader().getResource("examples/minTest.log").getFile());

        final ProjectEntity project = new ProjectEntity();
        project.setName("minTest");
        project.setLoglines(readerLogFile.reader(file));

        loggerDao.saveProjectLogs(project);
    }

    @Override
    public List<Project> getProjects() {
        loggerDao.getAllProjects();
        return null;
    }

    @Override
    public Project getProjectLogs(Long idProject) {
        loggerDao.getProject(idProject);
        return null;

    }

    @Override
    public List<LogLine> getProjectLogsByType(Long idProject, String logType) {
        loggerDao.getProjectLogsByType(idProject, LogTypeEntityEnum.valueOf(logType));
        return null;
    }
}
