package br.com.caelum.xstream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;

public class ProductTest {

    @Test
    public void shouldMapToXml() {
        var expectedXml = """
                <produto código=\"1587\">
                  <nome>geladeira</nome>
                  <preço>1000.0</preço>
                  <descrição>geladeira com duas portas</descrição>
                </produto>""";
        var product = Product.builder() //
                .code(1587) //
                .name("geladeira") //
                .price(1000.0) //
                .description("geladeira com duas portas") //
                .build();

        var xStream = new XStream();

        // Definindo o alias do nome da classe
        xStream.alias("produto", Product.class);

        // Definindo que o campo code será usado como atributo e com o alias código
        xStream.useAttributeFor(Product.class, "code");
        xStream.aliasField("código", Product.class, "code");

        // Definido os alias's para o demais campos
        xStream.aliasField("nome", Product.class, "name");
        xStream.aliasField("preço", Product.class, "price");
        xStream.aliasField("descrição", Product.class, "description");

        var generatedXml = xStream.toXML(product);

        assertThat(generatedXml, is(equalTo(expectedXml)));
    }

}