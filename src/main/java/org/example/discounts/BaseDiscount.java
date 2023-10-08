package org.example.discounts;

import org.example.products.Product;

public abstract class BaseDiscount implements Discount {
    Discount nextDiscount;

    public BaseDiscount(Discount discount) {
        this.nextDiscount = discount;
    }
    @Override
    public double apply(Product product) {
        return nextDiscount.apply(product);
    }

    @Override
    public String getDescription(Product product) {
        return nextDiscount.getDescription(product);
    }

    protected abstract boolean isApplicable(Product product);

    protected abstract double calculateDiscount(Product product);
}
