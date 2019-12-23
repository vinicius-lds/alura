package br.com.alura.alurator;

import br.com.alura.annotation.XmlTagName;
import br.com.alura.exception.InvalidClassNameException;
import br.com.alura.exception.InvalidConstructorException;
import br.com.alura.exception.InvalidFieldNameException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClassManipulator<T> {

    public static <T> ClassManipulator<T> of(Class<T> clazz) {
        return new ClassManipulator<T>(clazz);
    }

    public static <T> ClassManipulator<T> of(String fullyQualifiedName) {
        try {
            return ClassManipulator.of((Class<T>) Class.forName(fullyQualifiedName));
        } catch (ClassNotFoundException e) {
            throw new InvalidClassNameException("Class '" + fullyQualifiedName + "' not valid");
        }
    }

    private final Class<T> clazz;

    private ClassManipulator(Class<T> clazz) {
        this.clazz = clazz;
    }

    public ConstructorManipulator<T> getDefaultConstructor() throws InvalidConstructorException {
        try {
            var defaultConstructor = clazz.getDeclaredConstructor();
            defaultConstructor.setAccessible(true);
            return ConstructorManipulator.of(defaultConstructor);
        } catch (NoSuchMethodException e) {
            throw new InvalidConstructorException("A valid default constructor was not found on the class " + this.clazz.getName() + ".", e);
        }
    }

    public FieldManipulator<T> getField(String name) {
        return (FieldManipulator<T>) FieldManipulator.of(getRawField(name));
    }

    public MethodManipulator<T> getMethod(String methodName, Map<String, Object> args) {
        return (MethodManipulator<T>) MethodManipulator.of(getRawMethod(methodName, args));
    }

    public FieldManipulator<T> getFieldWithObject(String name, T object) {
        return FieldManipulator.of(getRawField(name), object);
    }

    public MethodManipulator<T> getMethodWithObject(String methodName, Map<String, Object> args, T object) {
        return MethodManipulator.of(getRawMethod(methodName, args), object);
    }

    private Field getRawField(String name) {
        try {
            return clazz.getDeclaredField(name);
        } catch (NoSuchFieldException e) {
            throw new InvalidFieldNameException("Field not found: " + name);
        }
    }

    private Method getRawMethod(String methodName, Map<String, Object> args) {
        Method m = Stream.of(clazz.getDeclaredMethods())
                .filter(method ->
                        method.getName().equals(methodName)
                                && method.getParameterCount() == args.values().size()
                                && Stream.of(method.getParameters()).allMatch(parameter -> args.containsKey(parameter.getName()) && args.get(parameter.getName()).getClass() == parameter.getType())
                )
                .findFirst()
                .orElseThrow(() -> new InvalidFieldNameException("Method not found: " + methodName + " " + args.toString()));
        m.setAccessible(true);
        return m;
    }

    public List<FieldManipulator<T>> getFields() {
        return getRawFields()
                .stream()
                .map(field -> (FieldManipulator<T>) FieldManipulator.of(field))
                .collect(Collectors.toList());
    }

    public List<FieldManipulator<T>> getFieldsWithObject(T object) {
        return getRawFields()
                .stream()
                .map(field -> FieldManipulator.of(field, object))
                .collect(Collectors.toList());
    }

    private List<Field> getRawFields() {
        return Arrays.asList(clazz.getDeclaredFields());
    }

    public String getName() {
        return this.clazz.getSimpleName();
    }

    public String getXmlTagName() {
        XmlTagName annotation = this.clazz.getAnnotation(XmlTagName.class);
        if (annotation == null) {
            return getName();
        } else {
            return annotation.value();
        }
    }
}
