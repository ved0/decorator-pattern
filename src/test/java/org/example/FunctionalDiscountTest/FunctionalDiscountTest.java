package org.example.FunctionalDiscountTest;

import org.example.discounts.NoDiscount;
import org.example.discounts.FunctionalDiscount;
import org.example.products.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.function.Function;
import java.util.function.Predicate;

@DisplayName("Test of discounts implemented with functional interfaces")
public class FunctionalDiscountTest {
    FunctionalDiscount first;
    FunctionalDiscount second;
    FunctionalDiscount third;
    double toiletPaperDiscount = 0.15;
    double quantityPriceDiscount = 100.00;
    double fivePercentDiscount = 0.05;

    @BeforeEach
    void createDiscounts() {
        //Discount conditions
        Predicate<Product> productNameToiletPaper = product -> product.productName().equalsIgnoreCase("Toilet paper");
        Predicate<Product> productQuantityPriceMoreThan1000 = product -> product.price() * product.quantity() >= 1000;
        Predicate<Product> productPriceLessThan20 = product -> product.price() <= 20;
        //Discount amount
        Function<Product, Double> productNameDiscount = product -> product.price() * product.quantity() * toiletPaperDiscount;
        Function<Product, Double> productQuantityPriceDiscount = product -> quantityPriceDiscount;
        Function<Product, Double> productPriceLessThan20Discount = product -> product.price() * product.quantity() * fivePercentDiscount;
        //Initialize discounts
        first = new FunctionalDiscount(new NoDiscount(), productNameToiletPaper, productNameDiscount);
        second = new FunctionalDiscount(first, productQuantityPriceMoreThan1000, productQuantityPriceDiscount);
        third = new FunctionalDiscount(second, productPriceLessThan20, productPriceLessThan20Discount);
    }

    @Test
    @DisplayName("Test Toilet paper discount on something that is not toilet paper")
    void applyToiletPaperDiscountOnRandomProduct() {
        Product someProduct = new Product("Charger", 100, 5);
        assertEquals(0.0, first.apply(someProduct));
    }

    @Test
    @DisplayName("Apply discount on toilet paper")
    void applyDiscountOnToiletPaper() {
        Product toiletPaper = new Product("Toilet paper", 100, 5);
        double discount = toiletPaper.price() * toiletPaper.quantity() * toiletPaperDiscount;
        assertEquals(discount, first.apply(toiletPaper));
    }

    @Test
    @DisplayName("Test the setDescription method on the toilet paper discount")
    void testingSetDescriptionOnFunctionalDiscount() {
        Product toiletPaper = new Product("Toilet paper", 100, 5);
        String discountText = "This is a 15% discount on toilet paper!";
        first.setDescription(discountText);
        assertEquals("\n" + discountText, first.getDescription(toiletPaper));
    }

    @Test
    @DisplayName("Chain together a couple of Functional discounts")
    void applyMultipleDiscounts() {
        Product toiletPaper = new Product("Toilet paper", 50, 20);
        //15% off with the toilet paper discount and 100 kr off with the quantity-price-over-1000 discount
        //The product-price-under-20 discount does not apply.
        double discount = toiletPaper.price() * toiletPaper.quantity() * toiletPaperDiscount + quantityPriceDiscount;
        assertEquals(discount, third.apply(toiletPaper));
    }
}
