package io.srhojo.java.loggerreader.daos;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "LOG_LINES")
public class LogLine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "log_type", nullable = false)
    private LogTypeEnum logType;

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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LogTypeEnum getLogType() {
        return logType;
    }

    public void setLogType(LogTypeEnum logType) {
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
