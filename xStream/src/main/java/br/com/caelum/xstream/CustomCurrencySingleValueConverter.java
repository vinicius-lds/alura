package br.com.caelum.xstream;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.SingleValueConverter;

public class CustomCurrencySingleValueConverter implements SingleValueConverter {

    @Override
    public String toString(Object value) {
        var locale = new Locale("pt", "br");
        var numberFormat = NumberFormat.getCurrencyInstance(locale);
        return numberFormat.format(value);
    }

    @Override
    public Object fromString(String value) {
        var locale = new Locale("pt", "br");
        var numberFormat = NumberFormat.getCurrencyInstance(locale);
        try {
            return numberFormat.parse(value);
        } catch (ParseException e) {
            throw new ConversionException(e);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean canConvert(Class aClass) {
        return aClass.isAssignableFrom(Double.class);
    }
}
