package kata.supermarket;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class WeighedProductDiscount implements Discount<ItemByWeight> {
    private final BigDecimal minWeightInKilos;
    private final BigDecimal discountPercentage;

    public WeighedProductDiscount(BigDecimal minWeightInKilos, BigDecimal discountPercentage) {
        this.minWeightInKilos = minWeightInKilos;
        this.discountPercentage = discountPercentage;
    }

    @Override
    public boolean isApplicable(final ItemByWeight item) {
        return item.weightInKilos().compareTo(minWeightInKilos) >= 0;
    }

    @Override
    public BigDecimal discountAmount(final ItemByWeight item) {
        return item.price().multiply(discountPercentage).setScale(2, RoundingMode.HALF_UP);
    }
}
