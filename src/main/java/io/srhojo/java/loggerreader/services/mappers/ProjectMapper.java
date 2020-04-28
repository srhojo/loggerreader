package io.srhojo.java.loggerreader.services.mappers;

import io.srhojo.java.loggerreader.api.domain.Project;
import io.srhojo.java.loggerreader.daos.entities.ProjectEntity;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper implements OuterMapper<Project, ProjectEntity> {

    private final LogLineMapper logLineMapper;

    public ProjectMapper(final LogLineMapper logLineMapper) {
        this.logLineMapper = logLineMapper;
    }


    @Override
    public Project toOuter(final ProjectEntity inner) {
        if(inner==null) {
            return  null;
        }
        final Project outer = new Project();
        outer.setId(inner.getId());
        outer.setName(inner.getName());
        outer.setCreateAt(inner.getCreatedAt());
        outer.setLogLines(logLineMapper.toOuter(inner.getLoglines()));
        return outer;
    }
}
