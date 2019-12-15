package br.com.alura.alurator;

import br.com.alura.exceptions.MethodInvocationException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodManipulator {

    public static MethodManipulator of(Method method) {
        return new MethodManipulator(method);
    }

    private Method method;
    private Object object;

    private MethodManipulator(Method method) {
        this.method = method;
    }


    public MethodManipulator setObject(Object object) {
        this.object = object;
        return this;
    }

    public Object invoke(Object... args) {
        try {
            method.setAccessible(true);
            return method.invoke(object, args);
        } catch (IllegalAccessException e) {
            throw new MethodInvocationException("An exception occoured while invoking the method " + method.getName() + ".", e);
        } catch (InvocationTargetException e) {
            throw new MethodInvocationException("An exception occoured while invoking the method " + method.getName() + ".", e.getTargetException());
        }
    }
}
