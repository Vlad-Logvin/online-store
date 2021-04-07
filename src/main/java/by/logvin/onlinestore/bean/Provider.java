package by.logvin.onlinestore.bean;

import java.io.Serializable;
import java.util.Objects;

public class Provider implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String name;

    public Provider() {
    }

    public Provider(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "Provider{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Provider provider = (Provider) o;
        return id == provider.id && Objects.equals(name, provider.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
