package by.logvin.onlinestore.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * The class Basket describes user basket bean
 *
 * @author bylogvin
 * @see java.io.Serializable
 */
public class Basket implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private List<Product> products;

    public Basket() {
    }

    public Basket(int id, List<Product> products) {
        this.id = id;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public boolean isProductContains(Product product) {
        return products != null && products.contains(product);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Basket basket = (Basket) o;
        return id == basket.id && Objects.equals(products, basket.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, products);
    }

    @Override
    public String toString() {
        return "Basket{" +
                "id=" + id +
                ", products=" + products +
                '}';
    }
}
