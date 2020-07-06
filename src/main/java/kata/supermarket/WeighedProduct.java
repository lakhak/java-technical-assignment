package kata.supermarket;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class WeighedProduct {

    private final BigDecimal pricePerKilo;
    private List<Discount<ItemByWeight>> discounts;

    public WeighedProduct(final BigDecimal pricePerKilo) {
        this.pricePerKilo = pricePerKilo;
        this.discounts = Collections.emptyList();
    }

    BigDecimal pricePerKilo() {
        return pricePerKilo;
    }

    public Item weighing(final BigDecimal kilos) {
        return new ItemByWeight(this, kilos);
    }

    public List<Discount<ItemByWeight>> discounts() {
        return discounts;
    }

    public WeighedProduct withDiscounts(final List<Discount<ItemByWeight>> discounts) {
        this.discounts = discounts;
        return this;
    }
}
