package com.example.demo.strategy;

public class DiscountContext {

    private DiscountStrategy strategy;

    public void setStrategy(DiscountStrategy strategy) {
        this.strategy = strategy;
    }

    public double calculateDiscount(double price) {
        if (strategy == null) {
            return 0;
        }

        return strategy.calculateDiscount(price);
    }
}
