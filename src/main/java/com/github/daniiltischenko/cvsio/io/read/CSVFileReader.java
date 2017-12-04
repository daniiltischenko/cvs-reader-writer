package com.github.daniiltischenko.cvsio.io.read;

import com.github.daniiltischenko.cvsio.generator.PojoGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * CVS files implementation of {@link FileReader}.
 *
 * @author Daniil Tyshchenko
 * @version 0.0.1
 * @since 0.0.1
 */
public class CSVFileReader extends FileReader {
    private static final String CSV_DELIMITER = ";";
    private PojoGenerator pojoGenerator = new PojoGenerator();

    @Override
    public <T> List<T> read(String path, Class<T> clazz) {
        List<T> entries = new ArrayList<>();

        try(Scanner scanner = readFile(path)) {
            String[] fieldsNames;
            if (scanner.hasNextLine()) {
                String firstLine = scanner.nextLine();
                fieldsNames = firstLine.split(CSV_DELIMITER);
            } else {
                throw new RuntimeException("No field names were provided");
            }

            while (scanner.hasNextLine()) {
                String[] values = scanner.nextLine().split(CSV_DELIMITER);

                T csvEntry = pojoGenerator.generateEntryReflectively(fieldsNames, values, clazz);

                entries.add(csvEntry);
            }
        }
        return entries;
    }
}
