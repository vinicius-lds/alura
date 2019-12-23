package br.com.alura.alurator;

import br.com.alura.exception.MethodInvocationException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

public class MethodManipulator<T> {

    public static MethodManipulator of(Method method) {
        return new MethodManipulator(method);
    }

    public static <T> MethodManipulator<T> of(Method method, T object) {
        return new MethodManipulator(method, object);
    }

    private final Method method;
    private Map<String, Object> params;
    private T object;
    private BiConsumer<Method, Throwable> invocationTargetExceptionHandler;

    private MethodManipulator(Method method) {
        this.method = method;
    }

    private MethodManipulator(Method method, T object) {
        this.method = method;
        this.object = object;
    }

    public MethodManipulator<T> withObject(T object) {
        this.object = object;
        return this;
    }

    public MethodManipulator<T> withParams(Map<String, Object> params) {
        this.params = params;
        return this;
    }

    public MethodManipulator<T> withCatchClause(BiConsumer<Method, Throwable> handler) {
        this.invocationTargetExceptionHandler = handler;
        return this;
    }

    public ObjectManipulator invoke() {
        try {
            var args = Stream.of(method.getParameters()).map(parameter -> params.get(parameter.getName())).toArray();
            method.setAccessible(true);
            return ObjectManipulator.of(method.invoke(object, args));
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
