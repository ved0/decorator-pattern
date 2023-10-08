package org.example.discounts;

import org.example.products.Product;

import java.math.BigDecimal;

public interface Discount {

    double apply(Product product);

    String getDescription(Product product);
}
