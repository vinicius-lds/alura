package aula08;

public class Servico {

    private static Servico instance;

    public static Servico getInstance() {
        if (instance == null) {
            instance = new Servico();
        }
        return instance;
    }

}
