package kata.supermarket;

import java.math.BigDecimal;
import java.util.function.Function;

public interface Discount<T extends Item> extends Function<T, BigDecimal> {

    @Override
    default BigDecimal apply(T item) {
        if (isApplicable(item)) {
            return discountAmount(item);
        }
        return BigDecimal.ZERO;
    }

    boolean isApplicable(T item);

    BigDecimal discountAmount(T item);
}
