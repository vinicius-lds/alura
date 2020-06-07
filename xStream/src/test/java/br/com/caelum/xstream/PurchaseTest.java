package br.com.caelum.xstream;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;

public class PurchaseTest {

    @Test
    public void shouldMapRelationsToXml() {
        var expectedXml = """
                <compra>
                  <id>15</id>
                  <produtos>
                    <produto código=\"1587\">
                      <nome>geladeira</nome>
                      <preço>1000.0</preço>
                      <descrição>geladeira com duas portas</descrição>
                    </produto>
                    <produto código=\"1588\">
                      <nome>ferro de passar</nome>
                      <preço>100.0</preço>
                      <descrição>ferro com vaporizador</descrição>
                    </produto>
                  </produtos>
                </compra>""";

        var product1 = Product.builder() //
                .code(1587) //
                .name("geladeira") //
                .price(1000.0) //
                .description("geladeira com duas portas") //
                .build();
        var product2 = Product.builder() //
                .code(1588) //
                .name("ferro de passar") //
                .price(100.0) //
                .description("ferro com vaporizador") //
                .build();

        var purchase = Purchase.builder() //
                .id(15) //
                .products(new ArrayList<>(asList(product1, product2))) //
                .build();

        var xStream = new XStream();

        // Definindo o alias do nome da classe
        xStream.alias("produto", Product.class);
        xStream.alias("compra", Purchase.class);

        // Definindo que o campo code será usado como atributo e com o alias código
        xStream.useAttributeFor(Product.class, "code");
        xStream.aliasField("código", Product.class, "code");

        // Definido os alias's para o demais campos
        xStream.aliasField("nome", Product.class, "name");
        xStream.aliasField("preço", Product.class, "price");
        xStream.aliasField("descrição", Product.class, "description");
        xStream.aliasField("produtos", Purchase.class, "products");

        var generatedXml = xStream.toXML(purchase);
        assertThat(generatedXml, is(equalTo(expectedXml)));
    }

    @Test
    public void shouldMapImplicitlyProductsCollectionToXml() {
        var expectedXml = """
                <compra>
                  <id>15</id>
                  <produto código=\"1587\">
                    <nome>geladeira</nome>
                    <preço>1000.0</preço>
                    <descrição>geladeira com duas portas</descrição>
                  </produto>
                  <produto código=\"1588\">
                    <nome>ferro de passar</nome>
                    <preço>100.0</preço>
                    <descrição>ferro com vaporizador</descrição>
                  </produto>
                </compra>""";

        var product1 = Product.builder() //
                .code(1587) //
                .name("geladeira") //
                .price(1000.0) //
                .description("geladeira com duas portas") //
                .build();
        var product2 = Product.builder() //
                .code(1588) //
                .name("ferro de passar") //
                .price(100.0) //
                .description("ferro com vaporizador") //
                .build();

        var purchase = Purchase.builder() //
                .id(15) //
                .products(new ArrayList<>(asList(product1, product2))) //
                .build();

        var xStream = new XStream();

        // Definindo o alias do nome da classe
        xStream.alias("produto", Product.class);
        xStream.alias("compra", Purchase.class);

        // Definindo que o campo code será usado como atributo e com o alias código
        xStream.useAttributeFor(Product.class, "code");
        xStream.aliasField("código", Product.class, "code");

        // Definido os alias's para o demais campos
        xStream.aliasField("nome", Product.class, "name");
        xStream.aliasField("preço", Product.class, "price");
        xStream.aliasField("descrição", Product.class, "description");
        xStream.aliasField("produtos", Purchase.class, "products");

        // Define que a coleção products deve ser serializada implicitamente
        xStream.addImplicitCollection(Purchase.class, "products");

        var generatedXml = xStream.toXML(purchase);
        assertThat(generatedXml, is(equalTo(expectedXml)));
    }

    @Test
    public void shouldMapRelationsToXmlUsingXPathRelativeReferences() {
        var expectedXml = """
                <compra>
                  <id>15</id>
                  <produtos>
                    <produto código=\"1587\">
                      <nome>geladeira</nome>
                      <preço>1000.0</preço>
                      <descrição>geladeira com duas portas</descrição>
                    </produto>
                    <produto reference=\"../produto\"/>
                  </produtos>
                </compra>""";

        var product1 = Product.builder() //
                .code(1587) //
                .name("geladeira") //
                .price(1000.0) //
                .description("geladeira com duas portas") //
                .build();

        var purchase = Purchase.builder() //
                .id(15) //
                .products(new ArrayList<>(asList(product1, product1))) //
                .build();

        var xStream = new XStream();

        // Seta o modo de referencias para objetos iguais (obj == obj)
        xStream.setMode(XStream.XPATH_RELATIVE_REFERENCES); // Esse é o modo default

        // Definindo o alias do nome da classe
        xStream.alias("produto", Product.class);
        xStream.alias("compra", Purchase.class);

        // Definindo que o campo code será usado como atributo e com o alias código
        xStream.useAttributeFor(Product.class, "code");
        xStream.aliasField("código", Product.class, "code");

        // Definido os alias's para o demais campos
        xStream.aliasField("nome", Product.class, "name");
        xStream.aliasField("preço", Product.class, "price");
        xStream.aliasField("descrição", Product.class, "description");
        xStream.aliasField("produtos", Purchase.class, "products");

        var generatedXml = xStream.toXML(purchase);
        assertThat(generatedXml, is(equalTo(expectedXml)));
    }

    @Test
    public void shouldMapRelationsToXmlUsingXPathAbsoluteReferences() {
        var expectedXml = """
                <compra>
                  <id>15</id>
                  <produtos>
                    <produto código=\"1587\">
                      <nome>geladeira</nome>
                      <preço>1000.0</preço>
                      <descrição>geladeira com duas portas</descrição>
                    </produto>
                    <produto reference=\"/compra/produtos/produto\"/>
                  </produtos>
                </compra>""";

        var product1 = Product.builder() //
                .code(1587) //
                .name("geladeira") //
                .price(1000.0) //
                .description("geladeira com duas portas") //
                .build();

        var purchase = Purchase.builder() //
                .id(15) //
                .products(new ArrayList<>(asList(product1, product1))) //
                .build();

        var xStream = new XStream();

        // Seta o modo de referencias para objetos iguais (obj == obj)
        xStream.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);

        // Definindo o alias do nome da classe
        xStream.alias("produto", Product.class);
        xStream.alias("compra", Purchase.class);

        // Definindo que o campo code será usado como atributo e com o alias código
        xStream.useAttributeFor(Product.class, "code");
        xStream.aliasField("código", Product.class, "code");

        // Definido os alias's para o demais campos
        xStream.aliasField("nome", Product.class, "name");
        xStream.aliasField("preço", Product.class, "price");
        xStream.aliasField("descrição", Product.class, "description");
        xStream.aliasField("produtos", Purchase.class, "products");

        var generatedXml = xStream.toXML(purchase);
        assertThat(generatedXml, is(equalTo(expectedXml)));
    }

    @Test
    public void shouldMapRelationsToXmlUsingIdReferences() {
        var expectedXml = """
                <compra id=\"1\">
                  <id>15</id>
                  <produtos id=\"2\">
                    <produto id=\"3\" código=\"1587\">
                      <nome>geladeira</nome>
                      <preço>1000.0</preço>
                      <descrição>geladeira com duas portas</descrição>
                    </produto>
                    <produto reference=\"3\"/>
                  </produtos>
                </compra>""";

        var product1 = Product.builder() //
                .code(1587) //
                .name("geladeira") //
                .price(1000.0) //
                .description("geladeira com duas portas") //
                .build();

        var purchase = Purchase.builder() //
                .id(15) //
                .products(new ArrayList<>(asList(product1, product1))) //
                .build();

        var xStream = new XStream();

        // Seta o modo de referencias para objetos iguais (obj == obj)
        xStream.setMode(XStream.ID_REFERENCES);

        // Definindo o alias do nome da classe
        xStream.alias("produto", Product.class);
        xStream.alias("compra", Purchase.class);

        // Definindo que o campo code será usado como atributo e com o alias código
        xStream.useAttributeFor(Product.class, "code");
        xStream.aliasField("código", Product.class, "code");

        // Definido os alias's para o demais campos
        xStream.aliasField("nome", Product.class, "name");
        xStream.aliasField("preço", Product.class, "price");
        xStream.aliasField("descrição", Product.class, "description");
        xStream.aliasField("produtos", Purchase.class, "products");

        var generatedXml = xStream.toXML(purchase);
        assertThat(generatedXml, is(equalTo(expectedXml)));
    }

    @Test
    public void shouldMapRelationsToXmlUsingNoReferences() {
        var expectedXml = """
                <compra>
                  <id>15</id>
                  <produtos>
                    <produto código=\"1587\">
                      <nome>geladeira</nome>
                      <preço>1000.0</preço>
                      <descrição>geladeira com duas portas</descrição>
                    </produto>
                    <produto código=\"1587\">
                      <nome>geladeira</nome>
                      <preço>1000.0</preço>
                      <descrição>geladeira com duas portas</descrição>
                    </produto>
                  </produtos>
                </compra>""";

        var product1 = Product.builder() //
                .code(1587) //
                .name("geladeira") //
                .price(1000.0) //
                .description("geladeira com duas portas") //
                .build();

        var purchase = Purchase.builder() //
                .id(15) //
                .products(new ArrayList<>(asList(product1, product1))) //
                .build();

        var xStream = new XStream();

        // Seta o modo de referencias para objetos iguais (obj == obj)
        xStream.setMode(XStream.NO_REFERENCES);

        // Definindo o alias do nome da classe
        xStream.alias("produto", Product.class);
        xStream.alias("compra", Purchase.class);

        // Definindo que o campo code será usado como atributo e com o alias código
        xStream.useAttributeFor(Product.class, "code");
        xStream.aliasField("código", Product.class, "code");

        // Definido os alias's para o demais campos
        xStream.aliasField("nome", Product.class, "name");
        xStream.aliasField("preço", Product.class, "price");
        xStream.aliasField("descrição", Product.class, "description");
        xStream.aliasField("produtos", Purchase.class, "products");

        var generatedXml = xStream.toXML(purchase);
        assertThat(generatedXml, is(equalTo(expectedXml)));
    }

    @Test
    public void shouldMapXmlToObject() {
        var xml = """
                <compra>
                  <id>15</id>
                  <produtos>
                    <produto código=\"1587\">
                      <nome>geladeira</nome>
                      <preço>1000.0</preço>
                      <descrição>geladeira com duas portas</descrição>
                    </produto>
                    <produto código=\"1588\">
                      <nome>ferro de passar</nome>
                      <preço>100.0</preço>
                      <descrição>ferro com vaporizador</descrição>
                    </produto>
                  </produtos>
                </compra>""";

        var product1 = Product.builder() //
                .code(1587) //
                .name("geladeira") //
                .price(1000.0) //
                .description("geladeira com duas portas") //
                .build();
        var product2 = Product.builder() //
                .code(1588) //
                .name("ferro de passar") //
                .price(100.0) //
                .description("ferro com vaporizador") //
                .build();

        var expectedObject = Purchase.builder() //
                .id(15) //
                .products(new ArrayList<>(asList(product1, product2))) //
                .build();

        var xStream = new XStream();

        // Definindo o alias do nome da classe
        xStream.alias("produto", Product.class);
        xStream.alias("compra", Purchase.class);

        // Definindo que o campo code será usado como atributo e com o alias código
        xStream.useAttributeFor(Product.class, "code");
        xStream.aliasField("código", Product.class, "code");

        // Definido os alias's para o demais campos
        xStream.aliasField("nome", Product.class, "name");
        xStream.aliasField("preço", Product.class, "price");
        xStream.aliasField("descrição", Product.class, "description");
        xStream.aliasField("produtos", Purchase.class, "products");

        var generatedObject = xStream.fromXML(xml);
        assertThat(generatedObject, is(equalTo(expectedObject)));
    }

    @Test
    public void shouldMapBookAndMusicToXml() {
        var expectedXml = """
                <compra>
                  <id>15</id>
                  <produtos>
                    <livro codigo="1589">
                      <nome>O Pássaro Raro</nome>
                      <preco>100.0</preco>
                      <descrição>dez histórias sobre a existência</descrição>
                    </livro>
                    <musica codigo="1590">
                      <nome>Meu Passeio</nome>
                      <preco>100.0</preco>
                      <descrição>música livre</descrição>
                    </musica>
                  </produtos>
                </compra>""";

        var product1 = Book.builder() //
                .code(1589) //
                .name("O Pássaro Raro") //
                .price(100.0) //
                .description("dez histórias sobre a existência") //
                .build();
        var product2 = Music.builder() //
                .code(1590) //
                .name("Meu Passeio") //
                .price(100) //
                .description("música livre") //
                .build();

        var purchase = Purchase.builder() //
                .id(15) //
                .products(new ArrayList<>(asList(product1, product2))) //
                .build();

        var xStream = new XStream();

        // Definindo o alias do nome da classe
        xStream.alias("musica", Music.class);
        xStream.alias("livro", Book.class);
        xStream.alias("compra", Purchase.class);

        // Definindo que o campo code será usado como atributo e com o alias código
        xStream.useAttributeFor(Product.class, "code");
        xStream.aliasField("codigo", Product.class, "code");

        // Definido os alias's para o demais campos
        xStream.aliasField("nome", Product.class, "name");
        xStream.aliasField("preco", Product.class, "price");
        xStream.aliasField("descrição", Product.class, "description");
        xStream.aliasField("produtos", Purchase.class, "products");

        var generatedXml = xStream.toXML(purchase);
        assertThat(generatedXml, is(equalTo(expectedXml)));
    }

    @Test
    public void shouldMapProductCollectionWithLinkedListImplementationToXml() {
        var expectedXml = """
                <compra>
                  <id>15</id>
                  <produtos class=\"linked-list\">
                    <livro codigo="1589">
                      <nome>O Pássaro Raro</nome>
                      <preco>100.0</preco>
                      <descrição>dez histórias sobre a existência</descrição>
                    </livro>
                    <musica codigo="1590">
                      <nome>Meu Passeio</nome>
                      <preco>100.0</preco>
                      <descrição>música livre</descrição>
                    </musica>
                  </produtos>
                </compra>""";

        var product1 = Book.builder() //
                .code(1589) //
                .name("O Pássaro Raro") //
                .price(100.0) //
                .description("dez histórias sobre a existência") //
                .build();
        var product2 = Music.builder() //
                .code(1590) //
                .name("Meu Passeio") //
                .price(100) //
                .description("música livre") //
                .build();

        var purchase = Purchase.builder() //
                .id(15) //
                .products(new LinkedList<>(asList(product1, product2))) //
                .build();

        var xStream = new XStream();

        // Definindo o alias do nome da classe
        xStream.alias("musica", Music.class);
        xStream.alias("livro", Book.class);
        xStream.alias("compra", Purchase.class);

        // Definindo que o campo code será usado como atributo e com o alias código
        xStream.useAttributeFor(Product.class, "code");
        xStream.aliasField("codigo", Product.class, "code");

        // Definido os alias's para o demais campos
        xStream.aliasField("nome", Product.class, "name");
        xStream.aliasField("preco", Product.class, "price");
        xStream.aliasField("descrição", Product.class, "description");
        xStream.aliasField("produtos", Purchase.class, "products");

        var generatedXml = xStream.toXML(purchase);
        assertThat(generatedXml, is(equalTo(expectedXml)));
    }

    @Test
    public void shouldMapProductToXmlUsingCustomPriceConverter() {
        var expectedXml = """
                <compra>
                  <id>15</id>
                  <produtos>
                    <produto código=\"1587\">
                      <nome>geladeira</nome>
                      <preço>R$ 1.000,00</preço>
                      <descrição>geladeira com duas portas</descrição>
                    </produto>
                  </produtos>
                </compra>""";

        var product1 = Product.builder() //
                .code(1587) //
                .name("geladeira") //
                .price(1000.0) //
                .description("geladeira com duas portas") //
                .build();

        var purchase = Purchase.builder() //
                .id(15) //
                .products(new ArrayList<>(List.of(product1))) //
                .build();

        var xStream = new XStream();

        // Definindo o alias do nome da classe
        xStream.alias("compra", Purchase.class);
        xStream.alias("produto", Product.class);

        // Definindo que o campo code será usado como atributo e com o alias código
        xStream.useAttributeFor(Product.class, "code");
        xStream.aliasField("código", Product.class, "code");

        // Definido os alias's para o demais campos
        xStream.aliasField("nome", Product.class, "name");
        xStream.aliasField("preço", Product.class, "price");
        xStream.aliasField("descrição", Product.class, "description");
        xStream.aliasField("produtos", Purchase.class, "products");

        // Registra um conversor customizado
        xStream.registerConverter(new CustomCurrencyConverter());

        var generatedXml = xStream.toXML(purchase);
        assertThat(generatedXml, is(equalTo(expectedXml)));
    }

    @Test
    public void shouldMapProductToXmlUsingCustomPriceSingleValueConverter() {
        var expectedXml = """
                <compra>
                  <id>15</id>
                  <produtos>
                    <produto código=\"1587\">
                      <nome>geladeira</nome>
                      <preço>R$ 1.000,00</preço>
                      <descrição>geladeira com duas portas</descrição>
                    </produto>
                  </produtos>
                </compra>""";

        var product1 = Product.builder() //
                .code(1587) //
                .name("geladeira") //
                .price(1000.0) //
                .description("geladeira com duas portas") //
                .build();

        var purchase = Purchase.builder() //
                .id(15) //
                .products(new ArrayList<>(List.of(product1))) //
                .build();

        var xStream = new XStream();

        // Definindo o alias do nome da classe
        xStream.alias("compra", Purchase.class);
        xStream.alias("produto", Product.class);

        // Definindo que o campo code será usado como atributo e com o alias código
        xStream.useAttributeFor(Product.class, "code");
        xStream.aliasField("código", Product.class, "code");

        // Definido os alias's para o demais campos
        xStream.aliasField("nome", Product.class, "name");
        xStream.aliasField("preço", Product.class, "price");
        xStream.aliasField("descrição", Product.class, "description");
        xStream.aliasField("produtos", Purchase.class, "products");

        // Registra um conversor customizado
        xStream.registerConverter(new CustomCurrencySingleValueConverter());

        var generatedXml = xStream.toXML(purchase);
        assertThat(generatedXml, is(equalTo(expectedXml)));
    }

    @Test
    public void shouldMapPurchaseToXmlUsingComplexConverter() {
        var expectedXml = """
                <compra estilo="novo">
                  <id>15</id>
                  <fornecedor>guilherme.silveira@caelum.com.br</fornecedor>
                  <endereco>
                    <linha1>Rua Vergueiro 3185</linha1>
                    <linha2>8 andar - Sao Paulo - SP</linha2>
                  </endereco>
                  <produtos>
                    <livro codigo="1589">
                      <nome>O Pássaro Raro</nome>
                      <preco>100.0</preco>
                      <descrição>dez histórias sobre a existência</descrição>
                    </livro>
                    <musica codigo="1590">
                      <nome>Meu Passeio</nome>
                      <preco>100.0</preco>
                      <descrição>música livre</descrição>
                    </musica>
                  </produtos>
                </compra>""";

        var product1 = Book.builder() //
                .code(1589) //
                .name("O Pássaro Raro") //
                .price(100.0) //
                .description("dez histórias sobre a existência") //
                .build();
        var product2 = Music.builder() //
                .code(1590) //
                .name("Meu Passeio") //
                .price(100) //
                .description("música livre") //
                .build();

        var purchase = Purchase.builder() //
                .id(15) //
                .products(new LinkedList<>(asList(product1, product2))) //
                .build();

        var xStream = new XStream();

        // Definindo o alias do nome da classe
        xStream.alias("musica", Music.class);
        xStream.alias("livro", Book.class);
        xStream.alias("compra", Purchase.class);

        // Definindo que o campo code será usado como atributo e com o alias código
        xStream.useAttributeFor(Product.class, "code");
        xStream.aliasField("codigo", Product.class, "code");

        // Definido os alias's para o demais campos
        xStream.aliasField("nome", Product.class, "name");
        xStream.aliasField("preco", Product.class, "price");
        xStream.aliasField("descrição", Product.class, "description");
        xStream.aliasField("produtos", Purchase.class, "products");

        // Registra um conversor customizado
        xStream.registerConverter(new ComplexConverter());

        var generatedXml = xStream.toXML(purchase);
        assertThat(generatedXml, is(equalTo(expectedXml)));
    }

}