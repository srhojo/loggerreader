package io.srhojo.java.loggerreader.services;

import io.srhojo.java.loggerreader.daos.LogLine;
import io.srhojo.java.loggerreader.daos.LogTypeEnum;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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


    public List<LogLine> reader(final File file) {


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

    private LogLine extractInfoLineRegex(String line) {
        final List<String> splitLineThreadDescription = Arrays.asList(line.split(" : "));

        final String threadInfo = splitLineThreadDescription.get(0).trim();
        final String descriptionInfo = splitLineThreadDescription.size() > 1 ? splitLineThreadDescription.get(1).trim() : null;

        return mapToLogLine(threadInfo,descriptionInfo);
    }

    private LogLine mapToLogLine(String threadInfo, String descriptionInfo) {
        final LogLine logLine = new LogLine();

        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        logLine.setDate(LocalDateTime.parse(findMatcher(threadInfo,localDateRegex),dtf));
        logLine.setLogType(LogTypeEnum.valueOf(findMatcher(threadInfo,logTypeRegex)));
        logLine.setThreadNumber(Integer.valueOf(findMatcher(threadInfo,threadNumberRegex)));
        logLine.setThreadName(findMatcher(threadInfo,threadRegex));
        logLine.setPackageName(findMatcher(threadInfo,packageRegex));
        logLine.setDescription(descriptionInfo);

        return logLine;
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
