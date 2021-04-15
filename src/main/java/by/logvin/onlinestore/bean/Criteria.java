package by.logvin.onlinestore.bean;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

public class Criteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private String category;
    private Map<Attribute, String> criterias;

    public Criteria() {
    }

    public Criteria(String category, Map<Attribute, String> criterias) {
        this.category = category;
        this.criterias = criterias;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Map<Attribute, String> getCriterias() {
        return criterias;
    }

    public void setCriterias(Map<Attribute, String> criterias) {
        this.criterias = criterias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Criteria criteria = (Criteria) o;
        return Objects.equals(category, criteria.category) && Objects.equals(criterias, criteria.criterias);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, criterias);
    }

    @Override
    public String toString() {
        return "Criteria{" +
                "category='" + category + '\'' +
                ", criterias=" + criterias +
                '}';
    }
}
