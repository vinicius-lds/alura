package br.com.alura.alurator;

import java.util.Map;

public class ObjectManipulator<T> {

    public static final <T> ObjectManipulator<T> of(T object) {
        return new ObjectManipulator<>(object);
    }

    private final T object;
    private final ClassManipulator<T> classManipulator;

    private ObjectManipulator(T object) {
        this.object = object;
        this.classManipulator = ClassManipulator.of((Class<T>) this.object.getClass());
    }

    public MethodManipulator<T> getMethod(String name, Map<String, Object> params) {
        return this.classManipulator.getMethodWithObject(name, params, object);
    }

    public FieldManipulator<T> getField(String name) {
        return this.classManipulator.getFieldWithObject(name, object);
    }

    public String toXml() {
        var sb = new StringBuilder();
        var xmlTagName = this.classManipulator.getXmlTagName();
        sb.append(String.format("<%s>", xmlTagName));
        classManipulator
                .getFieldsWithObject(object)
                .forEach(fieldManipulator -> sb.append(fieldManipulator.toXml()));
        sb.append(String.format("</%s>", xmlTagName));
        return sb.toString();
    }

}
