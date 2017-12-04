package com.github.daniiltischenko.cvsio.conversion.strategy;

import com.github.daniiltischenko.cvsio.conversion.ConversionStrategy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * {@code ConversionStrategy} implementation for {@link Date} data type.
 *
 * @author Daniil Tyshchenko
 * @version 0.0.1
 * @since 0.0.1
 */
public class DateConversionStrategy implements ConversionStrategy<Date> {
    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);

    @Override
    public Date convertFromString(String value) {
        try {
            return formatter.parse(value);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Date value can not be parsed from String", e);
        }
    }

    @Override
    public String convertToString(Date value) {
        return formatter.format(value);
    }
}
