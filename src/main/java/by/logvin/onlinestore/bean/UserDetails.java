package by.logvin.onlinestore.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class UserDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Card> cards;
    private boolean access;
    private String role;
    private Basket basket;
    private Favourite favourite;

    public UserDetails() {
    }

    public UserDetails(List<Card> cards, boolean access, String role, Basket basket, Favourite favourite) {
        this.cards = cards;
        this.access = access;
        this.role = role;
        this.basket = basket;
        this.favourite = favourite;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetails that = (UserDetails) o;
        return access == that.access && Objects.equals(cards, that.cards) && Objects.equals(role, that.role) && Objects.equals(basket, that.basket) && Objects.equals(favourite, that.favourite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards, access, role, basket, favourite);
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "cards=" + cards +
                ", access=" + access +
                ", role='" + role + '\'' +
                ", basket=" + basket +
                ", favourite=" + favourite +
                '}';
    }
}
