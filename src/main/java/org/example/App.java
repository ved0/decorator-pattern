package org.example;

import org.example.discounts.*;
import org.example.products.Product;

public class App {
    public static void main(String[] args) {
        Product first = new Product("Hammer", 55.00, 4);
        Product second = new Product("Screwdriver", 33.00, 7);
        Product milk = new Product("mILk", 11, 12);

        Discount discount = new MilkDiscount(new FridayDiscount(new QuantityDiscount((new NoDiscount()))));
        System.out.println(discount.getDescription(milk));
        System.out.println("Total discount "+discount.apply(milk));

        System.out.println(discount.getDescription(first));
        System.out.println("Total discount "+discount.apply(first));


        System.out.println(discount.getDescription(second));
        System.out.println("Total discount "+discount.apply(second));
    }
}