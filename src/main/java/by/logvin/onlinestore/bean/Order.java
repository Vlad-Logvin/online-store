package by.logvin.onlinestore.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private double grandTotal;
    private Card card;
    private Date dateOfPurchase;
    private List<Product> products;

    public Order() {
    }

    public Order(int id, double grandTotal, Card card, Date dateOfPurchase, List<Product> products) {
        this.id = id;
        this.grandTotal = grandTotal;
        this.card = card;
        this.dateOfPurchase = dateOfPurchase;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && Double.compare(order.grandTotal, grandTotal) == 0 && Objects.equals(card, order.card) && Objects.equals(dateOfPurchase, order.dateOfPurchase) && Objects.equals(products, order.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, grandTotal, card, dateOfPurchase, products);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", grandTotal=" + grandTotal +
                ", card=" + card +
                ", dateOfPurchase=" + dateOfPurchase +
                ", products=" + products +
                '}';
    }
}
