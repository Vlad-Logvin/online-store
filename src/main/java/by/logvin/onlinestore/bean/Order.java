package by.logvin.onlinestore.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private double tax;
    private double totalCost;
    private double grandTotal;
    private Card card;
    private Date dateOfPurchase;

    public Order() {
    }

    public Order(int id, double tax, double totalCost, double grandTotal, Card card, Date dateOfPurchase) {
        this.id = id;
        this.tax = tax;
        this.totalCost = totalCost;
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

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
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
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", tax=" + tax +
                ", totalCost=" + totalCost +
                ", grandTotal=" + grandTotal +
                ", card=" + card +
                ", dateOfPurchase=" + dateOfPurchase +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && Double.compare(order.tax, tax) == 0 && Double.compare(order.totalCost, totalCost) == 0 && Double.compare(order.grandTotal, grandTotal) == 0 && Objects.equals(card, order.card) && Objects.equals(dateOfPurchase, order.dateOfPurchase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tax, totalCost, grandTotal, card, dateOfPurchase);
    }
}
