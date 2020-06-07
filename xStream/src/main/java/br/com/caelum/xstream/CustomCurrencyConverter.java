package br.com.caelum.xstream;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class CustomCurrencyConverter implements Converter {

    @Override
    @SuppressWarnings("unchecked")
    public boolean canConvert(Class aClass) {
        return aClass.isAssignableFrom(Double.class);
    }

    @Override
    public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {
        var locale = new Locale("pt", "br");
        var numberFormat = NumberFormat.getCurrencyInstance(locale);
        var stringValue = numberFormat.format(value);
        writer.setValue(stringValue);
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        var value = reader.getValue();
        var locale = new Locale("pt", "br");
        var numberFormat = NumberFormat.getCurrencyInstance(locale);
        try {
            return numberFormat.parse(value);
        } catch (ParseException e) {
            throw new ConversionException(e);
        }
    }

}
