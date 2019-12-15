package br.com.alura.alurator;

public class ReflectionUtils {

    public static String buildFullyQualifiedName(String basePackage, String className) {
        if (basePackage == null) {
            basePackage = "";
        } else if (!basePackage.endsWith(".")) {
            basePackage += ".";
        }
        return basePackage + className;
    }

}
