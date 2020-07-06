package kata.supermarket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WeighedProductDiscountTest {

    @DisplayName("discount is applied for items priced by weight")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    public void apply(
            String description, String expectedDiscount, String itemWeight) {

        WeighedProductDiscount discount = new WeighedProductDiscount(BigDecimal.ONE, new BigDecimal("0.25"));
        ItemByWeight item = (ItemByWeight) new WeighedProduct(new BigDecimal("5"))
                .weighing(new BigDecimal(itemWeight));

        assertEquals(new BigDecimal(expectedDiscount), discount.apply(item));
    }

    static Stream<Arguments> apply() {
        return Stream.of(
                Arguments.of("item weight under minimum weight (1kg)", "0", "0.99"),
                Arguments.of("item weight equal to minimum weight (1kg)", "1.25", "1"),
                Arguments.of("item weight over minimum weight (1kg)", "2.50", "2")
        );
    }
}
