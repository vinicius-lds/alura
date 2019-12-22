package br.com.alura.maven;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Assert;
import org.junit.Test;

public class ProductTest {

    @Test
    public void verifyPriceWithTaxes() {
        var product = new Product("Candy", 0.1);
        Assert.assertEquals(0.11, product.getPriceWithTaxes(), 0.00001);
    }

}
