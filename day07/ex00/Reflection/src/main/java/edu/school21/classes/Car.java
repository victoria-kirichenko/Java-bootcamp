package edu.school21.classes;

public class Car {
    private String brand;
    private int numberModel;
    private int price;

    public Car() {
        this.brand = "Default brand";
        this.numberModel = 0;
        this.price = 1000;
    }

    public Car(String brand, int numberModel, int price) {
        this.brand = brand;
        this.numberModel = numberModel;
        this.price = price;
    }

    public int growPrice(int value) {
        price += value;
        return price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", numberModel=" + numberModel +
                ", price=" + price +
                '}';
    }
}