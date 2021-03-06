package br.com.alura.alurator;

import br.com.alura.exception.ConstructorInvocationException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ConstructorManipulator<T> {

    public static <T> ConstructorManipulator<T> of(Constructor<T> constructor) {
        return new ConstructorManipulator<T>(constructor);
    }

    private Constructor<T> constructor;

    private ConstructorManipulator(Constructor<T> constructor) {
        this.constructor = constructor;
    }

    public ObjectManipulator<T> invoke() {
        try {
            return ObjectManipulator.of(constructor.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            throw new ConstructorInvocationException("An excpetion occoured while invoking the constructor " + constructor.getName() + ".", e);
        } catch (InvocationTargetException e) {
            throw new ConstructorInvocationException("An excpetion occoured while invoking the constructor " + constructor.getName() + ".", e.getTargetException());
        }
    }
}
