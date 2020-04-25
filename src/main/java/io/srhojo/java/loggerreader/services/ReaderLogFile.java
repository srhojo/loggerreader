package io.srhojo.java.loggerreader.services;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Service
public class ReaderLogFile {


    public void reader(final File file) {


        final Path path = Paths.get(file.getPath());

        try (Stream<String> lines = Files.lines(path)) {

            //lines.filter(this::validLogLine).forEach(l -> System.out.println(extractInfoLine(l)));

            lines.filter(this::validLogLine).forEach(l -> System.out.println(extractInfoLineRegex(l)));


        } catch (final IOException ioException) {
            ioException.printStackTrace();
        }

    }


    private String localDateRegex = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}.\\d{3}";
    private String logTypeRegex = "INFO|WARN|DEBUG|ERROR";
    private String threadRegex = "";
    private String packageRegex = "";

    private String extractInfoLineRegex(String line) {

        List<String> splitLineThreadDescription = Arrays.asList(line.split(" : "));

        String threadInfo = splitLineThreadDescription.get(0).trim();
        String descriptionInfo = splitLineThreadDescription.size() > 1 ? splitLineThreadDescription.get(1).trim() : null;


        Pattern pattern = Pattern.compile(localDateRegex);
        Matcher matcher = pattern.matcher(threadInfo);


        String date = matcher.find()? matcher.group(0) : "N/A";
        String logType = "N/A";
        String threadNumber = "N/A";
        String theadName = "N/A";
        String packageName = "N/A";
        String description = descriptionInfo;
        String loggerFormat = "Date: %s; LogType: %s; ThreadNumber: %s; ThreadName: %s; Package: %s; Description: %s";

        return String.format(loggerFormat,date,logType,threadNumber,theadName,packageName,description);
    }


    private String extractInfoLine(String line) { // Extraer con regex mejor que con splits...

        List<String> splitLineThreadDescription = Arrays.asList(line.split(" : "));

        String threadInfo = splitLineThreadDescription.get(0).trim();
        String descriptionInfo = splitLineThreadDescription.size() > 1 ? splitLineThreadDescription.get(1).trim() : null;


        String[] threadInfoSplit = threadInfo.split("---");
        String logInfo = threadInfoSplit[0].trim();
        String threadInfo2 = threadInfoSplit[1].trim();

        String[] logInfoSplit = logInfo.split(" ");
        String[] threadInfo2Split = threadInfo2.split(" ");


        String date = logInfoSplit[0].trim();
        String logType = logInfoSplit[1].trim();
        String threadNumber = logInfoSplit[2].trim();
        String theadName = threadInfo2Split[0].trim();
        String packageName = threadInfo2Split[1].trim();
        String description = descriptionInfo;
        String loggerFormat = "Date: %s; LogType: %s; ThreadNumber: %s; ThreadName: %s; Package: %s; Description: %s";

        return String.format(loggerFormat,date,logType,threadNumber,theadName,packageName,description);
    }


    private boolean validLogLine(final String line) {
        return line.contains("WARN") | line.contains("INFO") | line.contains("ERROR") | line.contains("DEBUG");
    }

}
