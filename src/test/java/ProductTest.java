import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class ProductTest {

    @Test
    public void testConvertIntToProduct() {
        Product coke = Product.convertIntToProduct(1);
        Product pepsi = Product.convertIntToProduct(2);
        Product soda = Product.convertIntToProduct(3);
        Product nothing = Product.convertIntToProduct(0);

        assertThat(coke).isEqualByComparingTo(Product.Coke);
        assertThat(pepsi).isEqualByComparingTo(Product.Pepsi);
        assertThat(soda).isEqualByComparingTo(Product.Soda);
        assertThat(nothing).isNull();

    }
}