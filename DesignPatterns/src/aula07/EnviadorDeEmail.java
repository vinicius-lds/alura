package aula07;

public class EnviadorDeEmail implements AcaoAposGerarNota {

    public void executa(NotaFiscal notaFiscal) {
        System.out.println("enviando por e-mail");
    }
}
