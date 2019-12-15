package br.com.alura.alurator;

import br.com.alura.exceptions.InvalidConstructorException;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.stream.Stream;

public class ClassManipulator<T> {

    public static <T> ClassManipulator<T> of(Class<T> clazz) {
        return new ClassManipulator<T>(clazz);
    }

    private Class<T> clazz;

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

    public Method getMethod(String methodName, Map<String, Object> args) {
        Method m = Stream.of(clazz.getDeclaredMethods())
                .filter(method ->
                        method.getName().equals(methodName)
                                && method.getParameterCount() == args.values().size()
                                && Stream.of(method.getParameters()).allMatch(parameter -> args.containsKey(parameter.getName()) && args.get(parameter.getName()).getClass() == parameter.getType())
                )
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Method not found: " + methodName + " " + args.toString()));
        m.setAccessible(true);
        return m;
    }
}
