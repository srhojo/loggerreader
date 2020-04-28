package io.srhojo.java.loggerreader.services.mappers;

import io.srhojo.java.loggerreader.api.domain.LogLine;
import io.srhojo.java.loggerreader.api.domain.LogTypeEnum;
import io.srhojo.java.loggerreader.daos.entities.LogLineEntity;
import org.springframework.stereotype.Component;

@Component
public class LogLineMapper implements OuterMapper<LogLine, LogLineEntity> {

    @Override
    public LogLine toOuter(final LogLineEntity inner) {
        if(inner==null) {
            return null;
        }
        final LogLine outer = new LogLine();
        outer.setId(inner.getId());
        outer.setDate(inner.getDate());
        outer.setLogType(LogTypeEnum.valueOf(inner.getLogType().name()));
        outer.setThreadNumber(inner.getThreadNumber());
        outer.setThreadName(inner.getThreadName());
        outer.setPackageName(inner.getPackageName());
        outer.setDescription(inner.getDescription());
        return outer;
    }
}
