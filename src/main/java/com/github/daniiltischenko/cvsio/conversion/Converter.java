package com.github.daniiltischenko.cvsio.conversion;

import com.github.daniiltischenko.cvsio.conversion.strategy.DateConversionStrategy;
import com.github.daniiltischenko.cvsio.conversion.strategy.IntegerConversionStrategy;
import com.github.daniiltischenko.cvsio.conversion.strategy.StringConversionStrategy;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Converts data from and to {@code String} by use of different {@link ConversionStrategy}.
 * May be extended with new strategies for new data types.
 *
 * @author Daniil Tyshchenko
 * @version 0.0.1
 * @since 0.0.1
 */
public class Converter {

    private static final Map<Class, ConversionStrategy > CLASS_TO_STRATEGY_MAP = new HashMap<Class, ConversionStrategy>(){
        {
            put(Date.class, new DateConversionStrategy());
            put(String.class, new StringConversionStrategy());
            put(Integer.class, new IntegerConversionStrategy());
        }
    };

    public <T>T getValueFromString(Class<T> clazz, String value) {
        ConversionStrategy<T> strategy = defineConversionStrategy(clazz);
        return strategy.convertFromString(value);
    }

    public <T> String getStringFromValue(T value) {
        ConversionStrategy<T> strategy = defineConversionStrategy(value.getClass());
        return strategy.convertToString(value);
    }

    private <T> ConversionStrategy <T> defineConversionStrategy(Class clazz) {
        return CLASS_TO_STRATEGY_MAP.get(clazz);
    }
}
