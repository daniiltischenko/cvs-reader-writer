package com.github.daniiltischenko.cvsio.generator;

import com.github.daniiltischenko.cvsio.conversion.Converter;

import java.lang.reflect.Field;

/**
 * Generates POJO object based on array of fields,
 * array of values and entity class reflectively.
 *
 * @author Daniil Tyshchenko
 * @version 0.0.1
 * @since 0.0.1
 */
public class PojoGenerator {
    private Converter converter = new Converter();

    public <T> T generateEntryReflectively(String[] fields, String[] values, Class<T> clazz) {
        validate(fields, values);

        T newInstance = createInstance(clazz);

        for (int i = 0; i < fields.length; i++) {
            String fieldName = fields[i];
            Field field = getDeclaredField(clazz, fieldName);
            String value = values[i];

            setFieldValue(newInstance, field, value);
        }

        return newInstance;
    }

    private void validate(String[] fields, String[] values) {
        if (values.length != fields.length) {
            throw new RuntimeException("Incorrect input. Number of entries in the row does not match the number of fields");
        }
    }

    private <T> T createInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Could not create class instance", e);
        }
    }

    private <T> Field getDeclaredField(Class<T> clazz, String fieldName) {
        try {
            return clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            throw new IllegalArgumentException("No such field", e);
        }
    }

    private <T> void setFieldValue(T newInstance, Field field, String value) {
        try {
            field.setAccessible(Boolean.TRUE);
            Object convertedFieldValue = transformStringValueToFieldType(field.getType(), value);
            field.set(newInstance, convertedFieldValue);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Could not access the field", e);
        }
    }

    private <T> T transformStringValueToFieldType(Class<T> type, String value) {
        return converter.getValueFromString(type, value);
    }
}
