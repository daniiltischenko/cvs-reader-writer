package com.github.daniiltischenko.cvsio.conversion;

/**
 * Encapsulates logic for converting data to and from {@code String}.
 *
 * @author Daniil Tyshchenko
 * @version 0.0.1
 * @since 0.0.1
 */
public interface ConversionStrategy<T> {

    T convertFromString(String value);

    String convertToString(T value);
}
