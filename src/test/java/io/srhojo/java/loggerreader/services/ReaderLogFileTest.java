package io.srhojo.java.loggerreader.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;

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
            readerLogFile.reader(file);

            //Then
            // Nothing for now

    }
}