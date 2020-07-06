package kata.supermarket;

import java.math.BigDecimal;

public class ProductDiscount implements Discount<ItemByUnit> {
    private final int minUnits;

    public ProductDiscount(final int minUnits) {
        this.minUnits = minUnits;
    }

    @Override
    public boolean isApplicable(ItemByUnit item) {
        return item.units() >= minUnits;
    }

    @Override
    public BigDecimal discountAmount(ItemByUnit item) {
        return item.pricePerUnit();
    }
}
