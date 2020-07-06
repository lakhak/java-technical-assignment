package kata.supermarket;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class Product {

    private final BigDecimal pricePerUnit;
    private List<Discount<ItemByUnit>> discounts;

    public Product(final BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
        this.discounts = Collections.emptyList();
    }

    BigDecimal pricePerUnit() {
        return pricePerUnit;
    }

    public Item oneOf() {
        return new ItemByUnit(this);
    }

    public Item units(final int units) {
        return new ItemByUnit(this, units);
    }

    public List<Discount<ItemByUnit>> discounts() {
        return discounts;
    }

    public Product withDiscounts(final List<Discount<ItemByUnit>> discounts) {
        this.discounts = discounts;
        return this;
    }
}
