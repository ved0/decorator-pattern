package org.example.discounts;

import org.example.products.Product;

import java.util.Calendar;

public class FridayDiscount extends BaseDiscount {
    private final double FRIDAY_DISCOUNT = 0.15;

    public FridayDiscount(Discount discount) {
        super(discount);
    }

    @Override
    protected boolean isApplicable(Product product) {
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY;
    }

    @Override
    protected double calculateDiscount(Product product) {
        return product.price() * product.quantity() * FRIDAY_DISCOUNT;
    }

    @Override
    public String getDescription(Product product) {
        String description = isApplicable(product) ? "\nIt's Friday man! Here you go, a " + FRIDAY_DISCOUNT * 100 + "% discount!" :
                "\nIts not Friday today!";
        return description + nextDiscount.getDescription(product);
    }
}
