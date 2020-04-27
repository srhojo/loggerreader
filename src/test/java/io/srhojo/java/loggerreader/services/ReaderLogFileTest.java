package io.srhojo.java.loggerreader.services;

import io.srhojo.java.loggerreader.daos.LogLine;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ReaderLogFileTest {

    @InjectMocks
    private ReaderLogFile readerLogFile;

    @Test
    void reader() {
            //Given
            final File file = new File(this.getClass().getClassLoader().getResource("examples/minTest.log").getFile());

            //When
            final List<LogLine> lines = readerLogFile.reader(file);

            lines.forEach(LogLine::toString);

            //Then
            assertThat(lines).isNotNull();
    }


}