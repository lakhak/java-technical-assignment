package kata.supermarket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BasketTest {

    @DisplayName("basket provides its total value when containing...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void basketProvidesTotalValue(String description, String expectedTotal, Iterable<Item> items) {
        final Basket basket = new Basket();
        items.forEach(basket::add);
        assertEquals(new BigDecimal(expectedTotal), basket.total());
    }

    static Stream<Arguments> basketProvidesTotalValue() {
        return Stream.of(
                noItems(),
                aSingleItemPricedPerUnit(),
                multipleItemsPricedPerUnit(),
                aSingleItemPricedByWeight(),
                multipleItemsPricedByWeight(),
                twoOfTheSameItemWithoutDiscount(),
                twoOfTheSameItemWithDiscount(),
                oneItemPricedByWeightWithDiscount()
        );
    }

    private static Arguments aSingleItemPricedByWeight() {
        return Arguments.of("a single weighed item", "1.25", Collections.singleton(twoFiftyGramsOfAmericanSweets()));
    }

    private static Arguments multipleItemsPricedByWeight() {
        return Arguments.of("multiple weighed items", "1.85",
                Arrays.asList(twoFiftyGramsOfAmericanSweets(), twoHundredGramsOfPickAndMix())
        );
    }

    private static Arguments multipleItemsPricedPerUnit() {
        return Arguments.of("multiple items priced per unit", "2.04",
                Arrays.asList(aPackOfDigestives(), aPintOfMilk()));
    }

    private static Arguments aSingleItemPricedPerUnit() {
        return Arguments.of("a single item priced per unit", "0.49", Collections.singleton(aPintOfMilk()));
    }

    private static Arguments noItems() {
        return Arguments.of("no items", "0.00", Collections.emptyList());
    }

    private static Item aPintOfMilk() {
        return new Product(new BigDecimal("0.49")).oneOf();
    }

    private static Item aPackOfDigestives() {
        return new Product(new BigDecimal("1.55")).oneOf();
    }

    private static WeighedProduct aKiloOfAmericanSweets() {
        return new WeighedProduct(new BigDecimal("4.99"));
    }

    private static Item twoFiftyGramsOfAmericanSweets() {
        return aKiloOfAmericanSweets().weighing(new BigDecimal(".25"));
    }

    private static WeighedProduct aKiloOfPickAndMix() {
        return new WeighedProduct(new BigDecimal("2.99"));
    }

    private static Item twoHundredGramsOfPickAndMix() {
        return aKiloOfPickAndMix().weighing(new BigDecimal(".2"));
    }

    private static Arguments twoOfTheSameItemWithoutDiscount() {
        return Arguments.of("two of the same item without discount", "5.90", Collections.singletonList(twoCansOfSoup()));
    }

    private static Item twoCansOfSoup() {
        return new Product(new BigDecimal("2.95")).units(2);
    }

    private static Arguments twoOfTheSameItemWithDiscount() {
        return Arguments.of("item with buy one get one free discount", "2.99",
                Collections.singletonList(twoCansOfBakedBeansWithDiscount()));
    }
    private static Item twoCansOfBakedBeansWithDiscount() {
        return new Product(new BigDecimal("2.99"))
                .withDiscounts(Collections.singletonList(buyOneGetOneFreeDiscount("2.99")))
                .units(2);
    }

    private static Discount<ItemByUnit> buyOneGetOneFreeDiscount(final String discountAmount) {
        return new ProductDiscount(2, new BigDecimal(discountAmount));
    }

    private static Arguments oneItemPricedByWeightWithDiscount() {
        return Arguments.of("item priced by weight with discount", "2.29",
                Collections.singletonList(oneKiloOfCarrotsWithDiscount()));
    }

    private static Item oneKiloOfCarrotsWithDiscount() {
        return new WeighedProduct(new BigDecimal("4.58"))
                .withDiscounts(Collections.singletonList(buyOneKiloForHalfPrice()))
                .weighing(BigDecimal.ONE);
    }

    private static Discount<ItemByWeight> buyOneKiloForHalfPrice() {
        return new WeighedProductDiscount(BigDecimal.ONE, new BigDecimal("0.5"));
    }
}
