package kata.supermarket;

import java.math.BigDecimal;

public class ItemByUnit implements Item {

    private final Product product;
    private final int units;

    ItemByUnit(final Product product) {
        this.product = product;
        units = 1;
    }

    ItemByUnit(final Product product, final int units) {
        this.product = product;
        this.units = units;
    }

    public BigDecimal price() {
        return product.pricePerUnit().multiply(new BigDecimal(units));
    }

    public BigDecimal discount() {
        return product.discounts().stream()
                .map(discount -> discount.apply(this))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    public int units() {
        return units;
    }
}
