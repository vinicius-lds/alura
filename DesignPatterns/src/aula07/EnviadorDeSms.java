package aula07;

public class EnviadorDeSms implements AcaoAposGerarNota {
    public void executa(NotaFiscal notaFiscal) {
        System.out.println("enviando por sms");
    }
}
