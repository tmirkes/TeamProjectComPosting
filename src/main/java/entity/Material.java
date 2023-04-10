package entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity(name="Material")
@Table(name="material", schema="com.posting")
public class Material {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Basic
    @Column(name = "comments", nullable = false, length = 255)
    private String comments;

    public Material() {
    }

    public Material(String name, String comments) {
        this.name = name;
        this.comments = comments;
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Material material = (Material) o;
        return id == material.id && Objects.equals(name, material.name) && Objects.equals(comments, material.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, comments);
    }

    @Override
    public String toString() {
        return "Material{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }
}
