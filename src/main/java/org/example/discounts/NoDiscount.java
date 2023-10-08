package org.example.discounts;

import org.example.products.Product;

public class NoDiscount extends BaseDiscount {
    public NoDiscount() {
        super(null);
    }

    @Override
    public double apply(Product product) {
        return 0;
    }
    @Override
    public String getDescription(Product product) {
        return "";
    }
    @Override
    protected boolean isApplicable(Product product) {
        return true;
    }

    @Override
    protected double calculateDiscount(Product product) {
        return 0;
    }
}
