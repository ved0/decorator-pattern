package org.example.discounts;

import org.example.products.Product;

import java.text.DecimalFormat;
import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionalDiscount extends BaseDiscount {
    private final Predicate<Product> isApplicable;
    private final Function<Product, Double> calculateDiscount;
    private String description;

    public FunctionalDiscount(Discount discount, Predicate<Product> isApplicable, Function<Product, Double> calculateDiscount) {
        super(discount);
        this.isApplicable = isApplicable;
        this.calculateDiscount = calculateDiscount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getDescription(Product product) {
        String whatDescription = isApplicable(product) ?
                                    description == null ?
                                        "\nEnjoy your " + product.productName().toLowerCase() + "! You saved this much money " +  new DecimalFormat("#.##").format(calculateDiscount(product)) + " kr, with this discount!" :
                                        "\n" + description :
                                    "\n" + product.productName().toLowerCase() + " is not valid for this discount!";
        return whatDescription + nextDiscount.getDescription(product);
    }

    @Override
    protected boolean isApplicable(Product product) {
        return isApplicable.test(product);
    }

    @Override
    protected double calculateDiscount(Product product) {
        return calculateDiscount.apply(product);
    }
}
