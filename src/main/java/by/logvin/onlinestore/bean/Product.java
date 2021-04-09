package by.logvin.onlinestore.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private double price;
    private String description;
    private int quantity;
    private String photoURL;
    private Category category;
    private List<Attribute> attributes;

    public Product() {
    }

    public Product(int id, String name, double price, String description, int quantity, String photoURL, Category category, List<Attribute> attributes) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.photoURL = photoURL;
        this.category = category;
        this.attributes = attributes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && Double.compare(product.price, price) == 0 && quantity == product.quantity && Objects.equals(name, product.name) && Objects.equals(description, product.description) && Objects.equals(photoURL, product.photoURL) && Objects.equals(category, product.category) && Objects.equals(attributes, product.attributes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, description, quantity, photoURL, category, attributes);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", photoURL='" + photoURL + '\'' +
                ", category=" + category +
                ", attributes=" + attributes +
                '}';
    }
}

