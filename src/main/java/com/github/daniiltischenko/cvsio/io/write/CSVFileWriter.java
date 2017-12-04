package com.github.daniiltischenko.cvsio.io.write;

import com.github.daniiltischenko.cvsio.generator.CSVGenerator;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * CVS files implementation of {@link FileWriter}.
 *
 * @author Daniil Tyshchenko
 * @version 0.0.1
 * @since 0.0.1
 */
public class CSVFileWriter extends FileWriter {
    private CSVGenerator generator = new CSVGenerator();

    @Override
    public <T> void write(String path, List<T> entries) {
        Path file = Paths.get(path);
        List<String> text = generator.generateCSV(entries);
        try {
            Files.write(file, text, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
