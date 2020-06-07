package br.com.caelum.xstream;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class ComplexConverter implements Converter {

    @Override
    @SuppressWarnings("unchecked")
    public boolean canConvert(Class aClass) {
        return aClass.isAssignableFrom(Purchase.class);
    }

    @Override
    public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {
        writer.addAttribute("estilo", "novo");
        var purchase = (Purchase) value;
        writer.startNode("id");
        context.convertAnother(purchase.getId());
        writer.endNode();
        writer.startNode("fornecedor");
        writer.setValue("guilherme.silveira@caelum.com.br");
        writer.endNode();
        writer.startNode("endereco");
        writer.startNode("linha1");
        writer.setValue("Rua Vergueiro 3185");
        writer.endNode();
        writer.startNode("linha2");
        writer.setValue("8 andar - Sao Paulo - SP");
        writer.endNode();
        writer.endNode();
        writer.startNode("produtos");
        context.convertAnother(purchase.getProducts());
        writer.endNode();
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        var style = reader.getAttribute("estilo");

        reader.moveDown();
        var nameId = reader.getNodeName();
        var valueId = reader.getValue();
        reader.moveUp();

        reader.moveDown();
        var supplier = reader.getValue();
        reader.moveUp();

        reader.moveDown();
        var nameAddressTag = reader.getNodeName();
        reader.moveDown();
        var line1 = reader.getValue();
        reader.moveUp();
        reader.moveDown();
        var line2 = reader.getValue();
        reader.moveUp();
        reader.moveUp();

        var products = new ArrayList<Product>();
        int id = Integer.parseInt(valueId);
        var purchase = new Purchase(id, products);

        reader.moveDown();
        var convertedProducts = (List<Product>) context.convertAnother(purchase, List.class);
        products.addAll(convertedProducts);
        reader.moveUp();

        return purchase;
    }
}
