package br.com.alura.alurator;

import br.com.alura.exceptions.InvalidConstructorException;

import java.lang.reflect.Method;

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

    public Method getMethod(String methodName, Class<?>... args) throws NoSuchMethodException {
        var method = this.clazz.getDeclaredMethod(methodName, args);
        method.setAccessible(true);
        return method;
    }
}
