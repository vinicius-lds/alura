package aula07;

public class TesteAcao {

    public static void main(String[] args) {
        NotaFiscalBuilder builder = new NotaFiscalBuilder(
                new EnviadorDeEmail(),
                new NotaFiscalDao(),
                new EnviadorDeSms(),
                new Impressora(),
                new Multiplicador(2)
        );

        NotaFiscal notaFiscal = builder.paraEmpresa("Caelum")
                .comCnpj("123.456.789/0001-10")
                .com(new ItemDaNota("item 1", 100.0))
                .com(new ItemDaNota("item 2", 200.0))
                .com(new ItemDaNota("item 3", 300.0))
                .comObservacoes("entregar notaFiscal pessoalmente")
                .naDataAtual()
                .constroi();
    }

}
