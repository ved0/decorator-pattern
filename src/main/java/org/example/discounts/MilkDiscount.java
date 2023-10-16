package org.example.discounts;

import org.example.products.Product;

import java.text.DecimalFormat;

public class MilkDiscount extends BaseDiscount {
    private final double MILK_DISCOUNT = 0.05;

    public MilkDiscount(Discount discount) {
        super(discount);
    }

    @Override
    protected boolean isApplicable(Product product) {
        return product.productName().equalsIgnoreCase("Milk");
    }

    @Override
    protected double calculateDiscount(Product product) {
        return product.price() * product.quantity() * MILK_DISCOUNT;
    }

    @Override
    public String getDescription(Product product) {
        String description = isApplicable(product) ?
                "\nEnjoy your milk! Here is a " + MILK_DISCOUNT * 100 + "% (" + new DecimalFormat("#.##").format(calculateDiscount(product)) + " kr) discount on it!" :
                "\nThis is not milk!";
        return description + nextDiscount.getDescription(product);
    }
}
