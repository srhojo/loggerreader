package io.srhojo.java.loggerreader.daos.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "LOG_LINES")
public class LogLineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectEntity project;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "log_type", nullable = false)
    private LogTypeEntityEnum logType;

    @Column(name = "thread_number")
    private Integer threadNumber;

    @Column(name = "thread_name")
    private String threadName;

    @Column(name = "package_name", length = 1000)
    private String packageName;

    @Column(name = "description", length = 5000)
    private String description;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LogTypeEntityEnum getLogType() {
        return logType;
    }

    public void setLogType(LogTypeEntityEnum logType) {
        this.logType = logType;
    }

    public Integer getThreadNumber() {
        return threadNumber;
    }

    public void setThreadNumber(Integer threadNumber) {
        this.threadNumber = threadNumber;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LogLineEntity)) return false;
        LogLineEntity that = (LogLineEntity) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "LogLine{" +
                "id=" + id +
                ", date=" + date +
                ", logType=" + logType +
                ", threadNumber=" + threadNumber +
                ", threadName='" + threadName + '\'' +
                ", packageName='" + packageName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
