package br.com.alura.alurator;

import br.com.alura.exceptions.InvalidConstructorException;

public class Alurator {

    private String basePackage;

    public Alurator(String basePackage) {
        this.basePackage = basePackage;
    }

    public Object execute(String url) throws ClassNotFoundException, InvalidConstructorException, NoSuchMethodException {
        var request = new Request(url);
        var clazz = Class.forName(ReflectionUtils.buildFullyQualifiedName(basePackage, request.getControllerClassName()));
        var classManipulator = ClassManipulator.of(clazz);
        var object = classManipulator.getDefaultConstructor().invoke();
        var returnFromMethod = MethodManipulator.of(classManipulator.getMethod(request.getMethodName(), request.getParams()))
                .withParams(request.getParams())
                .withObject(object)
                .withCatchClause((method, ex) -> System.out.println(method + "; " + ex))
                .invoke();
        System.out.println(returnFromMethod);
        return null;
    }


}
