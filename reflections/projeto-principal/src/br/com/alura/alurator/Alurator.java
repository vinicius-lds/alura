package br.com.alura.alurator;

public class Alurator {

    private String basePackage;

    public Alurator(String basePackage) {
        this.basePackage = basePackage;
    }

    public Object execute(String url) {
        var request = new Request(url);
        var fullyQualifiedName = ReflectionUtils.buildFullyQualifiedName(basePackage, request.getControllerClassName());

        return ClassManipulator.of(fullyQualifiedName)
                .getDefaultConstructor()
                .invoke()
                .getMethod(request.getMethodName(), request.getParams())
                .withCatchClause((method, ex) -> System.out.println(method + "; " + ex))
                .invoke()
                .toXml();
    }


}
