package by.logvin.onlinestore.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Basket implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private double totalCost;
    private int productsNumber;
    private List<Product> products;

    public Basket() {
    }

    public Basket(int id) {
        this.id = id;
        products = new ArrayList<>();
    }

    public Basket(int id, double totalCost, int productsNumber, List<Product> products) {
        this.id = id;
        this.totalCost = totalCost;
        this.productsNumber = productsNumber;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public int getProductsNumber() {
        return productsNumber;
    }

    public void setProductsNumber(int productsNumber) {
        this.productsNumber = productsNumber;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public boolean hasProduct(Product product){
        return products.contains(product);
    }

    @Override
    public String toString() {
        return "Basket{" +
                "id=" + id +
                ", totalCost=" + totalCost +
                ", productsNumber=" + productsNumber +
                ", products=" + products +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Basket basket = (Basket) o;
        return id == basket.id && Double.compare(basket.totalCost, totalCost) == 0 && productsNumber == basket.productsNumber && Objects.equals(products, basket.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalCost, productsNumber, products);
    }
}
