package aula04;

import aula05.Visitor;

public interface Expressao {

    int avalia();
    void aceita(Visitor visitor);

}
