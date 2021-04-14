package by.logvin.onlinestore.bean;

import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Favourite implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private List<Product> products;

    public Favourite() {
    }

    public Favourite(int id){
        this.id = id;
        products = new ArrayList<>();
    }

    public Favourite(int id, List<Product> products) {
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


    // FIXME: 14.04.2021
    Logger logger = Logger.getLogger(Favourite.class);
    public boolean isProductContains(Product product) {
        return products != null ? products.contains(product) : false;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Favourite{" +
                "id=" + id +
                ", products=" + products +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Favourite favourite = (Favourite) o;
        return id == favourite.id && Objects.equals(products, favourite.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, products);
    }
}
