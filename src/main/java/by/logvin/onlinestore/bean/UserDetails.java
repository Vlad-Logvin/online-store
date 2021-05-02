package by.logvin.onlinestore.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * The class UserDetails describes user details bean
 *
 * @author bylogvin
 * @see java.io.Serializable
 */
public class UserDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Card> cards;
    private boolean access;
    private String role;
    private Basket basket;
    private Favourite favourite;
    private List<Order> orders;

    public UserDetails() {
    }

    public UserDetails(List<Card> cards, boolean access, String role, Basket basket, Favourite favourite, List<Order> orders) {
        this.cards = cards;
        this.access = access;
        this.role = role;
        this.basket = basket;
        this.favourite = favourite;
        this.orders = orders;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public boolean isAccess() {
        return access;
    }

    public void setAccess(boolean access) {
        this.access = access;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public Favourite getFavourite() {
        return favourite;
    }

    public void setFavourite(Favourite favourite) {
        this.favourite = favourite;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetails that = (UserDetails) o;
        return access == that.access && Objects.equals(cards, that.cards) && Objects.equals(role, that.role) && Objects.equals(basket, that.basket) && Objects.equals(favourite, that.favourite) && Objects.equals(orders, that.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards, access, role, basket, favourite, orders);
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "cards=" + cards +
                ", access=" + access +
                ", role='" + role + '\'' +
                ", basket=" + basket +
                ", favourite=" + favourite +
                ", orders=" + orders +
                '}';
    }
}
