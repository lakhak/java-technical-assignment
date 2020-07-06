package kata.supermarket;

import java.math.BigDecimal;

public class ProductDiscount implements Discount<ItemByUnit> {
    private final int minUnits;
    private final BigDecimal discountAmount;

    public ProductDiscount(final int minUnits, final BigDecimal discountAmount) {
        this.minUnits = minUnits;
        this.discountAmount = discountAmount;
    }

    @Override
    public boolean isApplicable(ItemByUnit item) {
        return item.units() >= minUnits;
    }

    @Override
    public BigDecimal discountAmount(ItemByUnit item) {
        return discountAmount;
    }
}
