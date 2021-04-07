package by.logvin.onlinestore.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Criteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Product> products;
    private Map<Attribute, String> criterias;

    public Criteria() {
    }

    public Criteria(List<Product> products, Map<Attribute, String> criterias) {
        this.products = products;
        this.criterias = criterias;
    }

    public void add(Attribute searchCriteria, String criteriaData) {
        criterias.put(searchCriteria, criteriaData);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Map<Attribute, String> getCriterias() {
        return criterias;
    }

    public void setCriterias(Map<Attribute, String> criterias) {
        this.criterias = criterias;
    }

    @Override
    public String toString() {
        return "Criteria{" +
                "products=" + products +
                ", criterias=" + criterias +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Criteria criteria = (Criteria) o;
        return Objects.equals(products, criteria.products) && Objects.equals(criterias, criteria.criterias);
    }

    @Override
    public int hashCode() {
        return Objects.hash(products, criterias);
    }
}
