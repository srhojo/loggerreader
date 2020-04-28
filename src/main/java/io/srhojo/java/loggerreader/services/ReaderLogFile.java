package io.srhojo.java.loggerreader.services;

import io.srhojo.java.loggerreader.daos.entities.LogLineEntity;
import io.srhojo.java.loggerreader.daos.entities.LogTypeEntityEnum;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ReaderLogFile {


    public List<LogLineEntity> reader(final File file) {

        final Path path = Paths.get(file.getPath());

        try (Stream<String> lines = Files.lines(path)) {

            return lines
                    .filter(this::validLogLine)
                    .map(this::extractInfoLineRegex)
                    .collect(Collectors.toList());


        } catch (final IOException ioException) {
            ioException.printStackTrace();
            throw new RuntimeException(ioException.getMessage());
        }

    }


    private String localDateRegex = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}";
    private String logTypeRegex = "INFO|WARN|DEBUG|ERROR";
    private String threadNumberRegex = " \\d{1} ";
    private String threadRegex = "\\[.*\\]";
    private String packageRegex = "([A-Za-z]{1}[A-Za-z\\d_]*\\.)+[A-Za-z][A-Za-z\\d_]*$";

    private LogLineEntity extractInfoLineRegex(String line) {
        final List<String> splitLineThreadDescription = Arrays.asList(line.split(" : "));

        final String threadInfo = splitLineThreadDescription.get(0).trim();
        final String descriptionInfo = splitLineThreadDescription.size() > 1 ? splitLineThreadDescription.get(1).trim() : null;

        return mapToLogLine(threadInfo,descriptionInfo);
    }

    private LogLineEntity mapToLogLine(String threadInfo, String descriptionInfo) {
        final LogLineEntity logLineEntity = new LogLineEntity();

        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        logLineEntity.setDate(LocalDateTime.parse(findMatcher(threadInfo,localDateRegex),dtf));
        logLineEntity.setLogType(LogTypeEntityEnum.valueOf(findMatcher(threadInfo,logTypeRegex)));
        logLineEntity.setThreadNumber(Integer.valueOf(findMatcher(threadInfo,threadNumberRegex)));
        logLineEntity.setThreadName(findMatcher(threadInfo,threadRegex));
        logLineEntity.setPackageName(findMatcher(threadInfo,packageRegex));
        logLineEntity.setDescription(descriptionInfo);

        return logLineEntity;
    }


    private String findMatcher(final String stringData, final String stringPattern) {
        final Pattern pattern = Pattern.compile(stringPattern);
        final Matcher matcher = pattern.matcher(stringData);
        return matcher.find() ? matcher.group(0).trim() : "N/A";
    }


    private boolean validLogLine(final String line) {
        return line.contains("WARN") || line.contains("INFO") || line.contains("ERROR") || line.contains("DEBUG");
    }

}
