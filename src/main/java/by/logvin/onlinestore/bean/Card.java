package by.logvin.onlinestore.bean;

import java.io.Serializable;
import java.util.Objects;

public class Card implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private long number;
    private int validityPeriod;
    private int authenticationCode;
    private String cardholder;

    public Card(){

    }

    public Card(long number, int validityPeriod, int authenticationCode, String cardholder) {
        this.number = number;
        this.validityPeriod = validityPeriod;
        this.authenticationCode = authenticationCode;
        this.cardholder = cardholder;
    }

    public Card(int id, long number, int validityPeriod, int authenticationCode, String cardholder) {
        this.id = id;
        this.number = number;
        this.validityPeriod = validityPeriod;
        this.authenticationCode = authenticationCode;
        this.cardholder = cardholder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public int getValidityPeriod() {
        return validityPeriod;
    }

    public void setValidityPeriod(int validityPeriod) {
        this.validityPeriod = validityPeriod;
    }

    public int getAuthenticationCode() {
        return authenticationCode;
    }

    public void setAuthenticationCode(int authenticationCode) {
        this.authenticationCode = authenticationCode;
    }

    public String getCardholder() {
        return cardholder;
    }

    public void setCardholder(String cardholder) {
        this.cardholder = cardholder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return id == card.id && number == card.number && validityPeriod == card.validityPeriod && authenticationCode == card.authenticationCode && Objects.equals(cardholder, card.cardholder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, validityPeriod, authenticationCode, cardholder);
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", number=" + number +
                ", validityPeriod=" + validityPeriod +
                ", authenticationCode=" + authenticationCode +
                ", cardholder='" + cardholder + '\'' +
                '}';
    }
}
