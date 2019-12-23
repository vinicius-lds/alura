package br.com.alura.alurator;

import br.com.alura.annotation.XmlTagName;

import java.lang.reflect.Field;
import java.util.Collection;

public class FieldManipulator<T> {

    public static FieldManipulator of(Field field) {
        return new FieldManipulator(field);
    }

    public static <T> FieldManipulator<T> of(Field field, T object) {
        return new FieldManipulator(field, object);
    }

    private final Field field;
    private T object;

    private FieldManipulator(Field field) {
        this.field = field;
    }

    private FieldManipulator(Field field, T object) {
        this.field = field;
        this.object = object;
    }

    public FieldManipulator<T> withObject(T object) {
        this.object = object;
        return this;
    }

    public String getName() {
        return this.field.getName();
    }

    public Object getValue() {
        try {
            this.field.setAccessible(true);
            return this.field.get(object);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public String toXml() {
        var xmlTagName = getXmlTagName();
        var fieldValue = getValue();
        if (fieldValue instanceof Collection) {
            var sb = new StringBuilder();
            sb.append(String.format("<%s>", xmlTagName));
            var collection = (Collection<?>) fieldValue;
            collection.forEach(item -> sb.append(ObjectManipulator.of(item).toXml()));
            sb.append(String.format("</%s>", xmlTagName));
            return sb.toString();
        } else {
            return String.format("<%s>%s</%s>", xmlTagName, fieldValue, xmlTagName);
        }
    }

    public String getXmlTagName() {
        XmlTagName annotation = this.field.getAnnotation(XmlTagName.class);
        if (annotation == null) {
            return getName();
        } else {
            return annotation.value();
        }
    }
}
