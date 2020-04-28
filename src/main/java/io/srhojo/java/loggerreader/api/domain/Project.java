package io.srhojo.java.loggerreader.api.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Project {
    private Long id;

    private String name;

    private LocalDateTime createAt;

    private List<LogLine> logLines;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public List<LogLine> getLogLines() {
        return logLines;
    }

    public void setLogLines(List<LogLine> logLines) {
        this.logLines = logLines;
    }
}
