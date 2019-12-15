package br.com.alura.alurator;

import br.com.alura.exceptions.MethodInvocationException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

public class MethodManipulator {

    public static MethodManipulator of(Method method) {
        return new MethodManipulator(method);
    }

    private Method method;
    private Map<String, Object> params;
    private Object object;
    private BiConsumer<Method, Throwable> invocationTargetExceptionHandler;

    private MethodManipulator(Method method) {
        this.method = method;
    }

    public MethodManipulator withParams(Map<String, Object> params) {
        this.params = params;
        return this;
    }

    public MethodManipulator withObject(Object object) {
        this.object = object;
        return this;
    }

    public MethodManipulator withCatchClause(BiConsumer<Method, Throwable> handler) {
        this.invocationTargetExceptionHandler = handler;
        return this;
    }

    public Object invoke() {
        try {
            var args = Stream.of(method.getParameters()).map(parameter -> params.get(parameter.getName())).toArray();
            method.setAccessible(true);
            return method.invoke(object, args);
        } catch (IllegalAccessException e) {
            throw new MethodInvocationException("An exception occoured while invoking the method " + method.getName() + ".", e);
        } catch (InvocationTargetException e) {
            if (invocationTargetExceptionHandler == null) {
                throw new MethodInvocationException("An exception occoured while invoking the method " + method.getName() + ".", e.getTargetException());
            } else {
                invocationTargetExceptionHandler.accept(method, e.getTargetException());
                return null;
            }
        }
    }

}
