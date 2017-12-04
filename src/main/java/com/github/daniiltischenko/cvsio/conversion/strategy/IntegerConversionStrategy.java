package com.github.daniiltischenko.cvsio.conversion.strategy;

import com.github.daniiltischenko.cvsio.conversion.ConversionStrategy;

/**
 * {@code ConversionStrategy} implementation for {@link Integer} data type.
 *
 * @author Daniil Tyshchenko
 * @version 0.0.1
 * @since 0.0.1
 */
public class IntegerConversionStrategy implements ConversionStrategy<Integer> {

    @Override
    public Integer convertFromString(String value) {
        return Integer.valueOf(value);
    }

    @Override
    public String convertToString(Integer value) {
        return Integer.toString(value);
    }
}
