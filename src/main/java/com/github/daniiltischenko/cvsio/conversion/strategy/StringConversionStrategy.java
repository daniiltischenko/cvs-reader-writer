package com.github.daniiltischenko.cvsio.conversion.strategy;

import com.github.daniiltischenko.cvsio.conversion.ConversionStrategy;

/**
 * {@code ConversionStrategy} implementation for {@link String} data type.
 *
 * @author Daniil Tyshchenko
 * @version 0.0.1
 * @since 0.0.1
 */
public class StringConversionStrategy implements ConversionStrategy<String> {

    @Override
    public String convertFromString(String value) {
        return value;
    }

    @Override
    public String convertToString(String value) {
        return value;
    }
}
