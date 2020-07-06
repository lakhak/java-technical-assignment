package kata.supermarket;

import java.math.BigDecimal;

public interface Discount<T extends Item> {

    boolean isApplicable(T item);

    BigDecimal discountAmount();
}
