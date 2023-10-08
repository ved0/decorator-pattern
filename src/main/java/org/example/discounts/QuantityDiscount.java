package org.example.discounts;

import org.example.products.Product;

public class QuantityDiscount extends BaseDiscount {
    private final int LEAST_PRODUCT_AMOUNT = 5;
    private final double DISCOUNT_AMOUNT = 10.0;

    public QuantityDiscount(Discount discount) {
        super(discount);
    }

    @Override
    protected boolean isApplicable(Product product) {
        return product.quantity() >= LEAST_PRODUCT_AMOUNT;
    }

    @Override
    protected double calculateDiscount(Product product) {
        return isApplicable(product) ? DISCOUNT_AMOUNT : 0;
    }

    @Override
    public double apply(Product product) {
        return nextDiscount.apply(product) + calculateDiscount(product);
    }

    @Override
    public String getDescription(Product product) {
        String description = isApplicable(product) ? "\nYou bought more than " + LEAST_PRODUCT_AMOUNT + " of " + product.productName() +
                ", so here is a " + DISCOUNT_AMOUNT + " kr discount" :
                "\nYou dont have enough items for this discount";
        return description + nextDiscount.getDescription(product);
    }
}
