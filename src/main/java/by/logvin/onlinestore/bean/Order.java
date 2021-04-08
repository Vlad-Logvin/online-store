package by.logvin.onlinestore.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private double grandTotal;
    private Card card;
    private Date dateOfPurchase;

    public Order() {
    }

    public Order(int id, double grandTotal, Card card, Date dateOfPurchase) {
        this.id = id;
        this.grandTotal = grandTotal;
        this.card = card;
        this.dateOfPurchase = dateOfPurchase;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && Double.compare(order.grandTotal, grandTotal) == 0 && Objects.equals(card, order.card) && Objects.equals(dateOfPurchase, order.dateOfPurchase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, grandTotal, card, dateOfPurchase);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", grandTotal=" + grandTotal +
                ", card=" + card +
                ", dateOfPurchase=" + dateOfPurchase +
                '}';
    }
}
