package by.logvin.onlinestore.bean;

import java.io.Serializable;
import java.util.Objects;

/**
 * The class Attribute describes product attribute bean
 *
 * @author bylogvin
 * @see java.io.Serializable
 */
public class Attribute implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String value;

    public Attribute() {
    }

    public Attribute(int id, String name, String value) {
        this.id = id;
        this.name = name;
        this.value = value;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attribute attribute = (Attribute) o;
        return id == attribute.id && Objects.equals(name, attribute.name) && Objects.equals(value, attribute.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, value);
    }
}
