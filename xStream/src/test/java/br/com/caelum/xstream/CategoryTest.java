package br.com.caelum.xstream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.TreeMarshaller;

public class CategoryTest {

    @Test
    public void shouldSerializeCircularReferencesWithModeXpathRelativeReferences() {

        var expectedXml = """
                <categoria>
                  <pai>
                    <pai>
                      <pai reference=\"../../..\"/>
                      <nome>futebol</nome>
                    </pai>
                    <nome>geral</nome>
                  </pai>
                  <nome>esporte</nome>
                </categoria>""";

        var category1 = Category.builder().name("esporte").parent(null).build();
        var category2 = Category.builder().name("futebol").parent(category1).build();
        var category3 = Category.builder().name("geral").parent(category2).build();
        category1.setParent(category3);

        var xStream = new XStream();
        xStream.setMode(XStream.XPATH_RELATIVE_REFERENCES);
        xStream.alias("categoria", Category.class);
        xStream.aliasField("nome", Category.class, "name");
        xStream.aliasField("pai", Category.class, "parent");

        var generatedXml = xStream.toXML(category1);
        assertThat(generatedXml, is(equalTo(expectedXml)));
    }

    @Test
    public void shouldSerializeCircularReferencesWithModeXpathAbsoluteReferences() {
        var expectedXml = """
                <categoria>
                  <pai>
                    <pai>
                      <pai reference=\"/categoria\"/>
                      <nome>futebol</nome>
                    </pai>
                    <nome>geral</nome>
                  </pai>
                  <nome>esporte</nome>
                </categoria>""";

        var category1 = Category.builder().name("esporte").parent(null).build();
        var category2 = Category.builder().name("futebol").parent(category1).build();
        var category3 = Category.builder().name("geral").parent(category2).build();
        category1.setParent(category3);

        var xStream = new XStream();
        xStream.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);
        xStream.alias("categoria", Category.class);
        xStream.aliasField("nome", Category.class, "name");
        xStream.aliasField("pai", Category.class, "parent");

        var generatedXml = xStream.toXML(category1);
        assertThat(generatedXml, is(equalTo(expectedXml)));
    }

    @Test
    public void shouldSerializeCircularReferencesWithModeIdReferences() {
        var expectedXml = """
                <categoria id=\"1\">
                  <pai id=\"2\">
                    <pai id=\"3\">
                      <pai reference=\"1\"/>
                      <nome>futebol</nome>
                    </pai>
                    <nome>geral</nome>
                  </pai>
                  <nome>esporte</nome>
                </categoria>""";

        var category1 = Category.builder().name("esporte").parent(null).build();
        var category2 = Category.builder().name("futebol").parent(category1).build();
        var category3 = Category.builder().name("geral").parent(category2).build();
        category1.setParent(category3);

        var xStream = new XStream();
        xStream.setMode(XStream.ID_REFERENCES);
        xStream.alias("categoria", Category.class);
        xStream.aliasField("nome", Category.class, "name");
        xStream.aliasField("pai", Category.class, "parent");

        var generatedXml = xStream.toXML(category1);
        assertThat(generatedXml, is(equalTo(expectedXml)));
    }

    @Test
    public void shouldThrowWhenTryingToSerializeCircularReferencesWithModeNoReferences() {
        var category1 = Category.builder().name("esporte").parent(null).build();
        var category2 = Category.builder().name("futebol").parent(category1).build();
        var category3 = Category.builder().name("geral").parent(category2).build();
        category1.setParent(category3);

        var xStream = new XStream();
        xStream.setMode(XStream.NO_REFERENCES);
        xStream.alias("categoria", Category.class);
        xStream.aliasField("nome", Category.class, "name");
        xStream.aliasField("pai", Category.class, "parent");

        var thrown = assertThrows(TreeMarshaller.CircularReferenceException.class, () -> xStream.toXML(category1));
        assertThat(thrown.getMessage(), is(equalTo("""
                                                           Recursive reference to parent object
                                                           ---- Debugging information ----
                                                           message             : Recursive reference to parent object
                                                           item-type           : br.com.caelum.xstream.Category
                                                           converter-type      : com.thoughtworks.xstream.converters.reflection.ReflectionConverter
                                                           -------------------------------""")));
    }

}