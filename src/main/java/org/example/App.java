package org.example;

import org.example.discounts.*;
import org.example.products.Product;

import java.util.function.Function;
import java.util.function.Predicate;

public class App {
    public static void main(String[] args) {
        Product first = new Product("Hammer", 55.00, 4);
        Product second = new Product("Screwdriver", 19.00, 7);
        Product milk = new Product("mILk", 21, 12);

        Predicate<Product> productPriceLessThan20 = product -> product.price() <= 20;
        Function<Product, Double> productPriceLessThan20Discount = product -> product.price() * product.quantity() * 0.05;

        Discount discount = new FunctionalDiscount(new MilkDiscount(new FridayDiscount(new QuantityDiscount((new NoDiscount())))), productPriceLessThan20, productPriceLessThan20Discount);
        System.out.println(discount.getDescription(milk));
        System.out.println("Total discount " + discount.apply(milk) + " kr");

        System.out.println(discount.getDescription(first));
        System.out.println("Total discount " + discount.apply(first) + " kr");


        System.out.println(discount.getDescription(second));
        System.out.println("Total discount " + discount.apply(second) + " kr");
    }
}