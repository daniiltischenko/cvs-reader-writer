package com.github.daniiltischenko.cvsio.generator;

import com.github.daniiltischenko.cvsio.conversion.Converter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Generates CVS file representation in memory for the {@code List}
 * of provided entities reflectively.
 *
 * Returns {@code List} of {@code String}.
 *
 * @author Daniil Tyshchenko
 * @version 0.0.1
 * @since 0.0.1
 */
public class CSVGenerator {
    private static final String CSV_DELIMITER = ";";
    private Converter converter = new Converter();

    public <T> List<String> generateCSV(List<T> objects) {
        String[] fields = getFields(objects);
        List<String> csv = new ArrayList<>();
        csv.add(buildLineWithDelimiter(Arrays.asList(fields)));

        for (T object : objects) {
            List<String> values = new ArrayList<>();
            for (String field : fields) {

                try {
                    Field declaredField = object.getClass().getDeclaredField(field);
                    declaredField.setAccessible(Boolean.TRUE);

                    Object value = declaredField.get(object);
                    String stringFromValue = converter.getStringFromValue(value);

                    values.add(stringFromValue);
                } catch (IllegalAccessException | NoSuchFieldException e) {
                    throw new RuntimeException("Couldn't read field's value", e);
                }
            }

            csv.add(buildLineWithDelimiter(values));
        }
        return csv;
    }

    /**
     * Retrieves array of field names for {@param objects}.
     *
     * @param objects
     * @param <T>
     * @return
     */
    private <T> String[] getFields(List<T> objects) {
        Iterator<T> iterator = objects.iterator();
        List<String> fields = new ArrayList<>();

        if (iterator.hasNext()) {
            T next = iterator.next();
            Field[] declaredFields = next.getClass().getDeclaredFields();

            for (Field declaredField : declaredFields) {
                fields.add(declaredField.getName());
            }

        }
        return fields.toArray(new String[0]);
    }

    private String buildLineWithDelimiter(List<String> values) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < values.size(); i++) {
            builder.append(values.get(i));

            if (i < values.size() - 1) {
                builder.append(CSV_DELIMITER);
            }
        }

        return builder.toString();
    }
}
