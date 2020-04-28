package io.srhojo.java.loggerreader.services;

import io.srhojo.java.loggerreader.daos.entities.LogLineEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ReaderLogFileTest {

    @InjectMocks
    private ReaderLogFile readerLogFile;

    @Test
    void reader() {
            //Given
            final File file = new File(this.getClass().getClassLoader().getResource("examples/minTest.log").getFile());

            //When
            final List<LogLineEntity> lines = readerLogFile.reader(file);

            lines.forEach(LogLineEntity::toString);

            //Then
            assertThat(lines).isNotNull();
    }


}