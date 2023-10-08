package org.example.discounts;

import org.example.products.Product;

import java.util.function.Function;

public class FunctionalDiscount extends BaseDiscount {
    private final Function<Product, Boolean> isApplicable;
    private final Function<Product, Double> calculateDiscount;
    private String description;

    public FunctionalDiscount(Discount discount, Function<Product, Boolean> isApplicable, Function<Product, Double> calculateDiscount) {
        super(discount);
        this.isApplicable = isApplicable;
        this.calculateDiscount = calculateDiscount;
    }


    @Override
    public double apply(Product product) {
        return nextDiscount.apply(product) + calculateDiscount(product);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getDescription(Product product) {
        return isApplicable(product) ?
                description == null ?
                        "\nEnjoy your " + product.productName() + "! You saved this much money " + calculateDiscount(product) + "!" :
                        "\n" + description : "\n" + product.productName() + " is not valid for this discount!";
    }

    @Override
    protected boolean isApplicable(Product product) {
        return isApplicable.apply(product);
    }

    @Override
    protected double calculateDiscount(Product product) {
        return isApplicable(product) ? calculateDiscount.apply(product) : 0;
    }
}
