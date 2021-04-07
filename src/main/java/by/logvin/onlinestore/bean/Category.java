package by.logvin.onlinestore.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private List<Attribute> attributes;

    public Category() {
    }

    public Category(int id, String name, List<Attribute> attributes) {
        this.id = id;
        this.name = name;
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

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", attributes=" + attributes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return id == category.id && Objects.equals(name, category.name) && Objects.equals(attributes, category.attributes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, attributes);
    }
}
