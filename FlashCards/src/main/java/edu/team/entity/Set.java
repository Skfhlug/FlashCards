package edu.team.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.management.relation.Role;
import javax.persistence.*;
import java.util.HashSet;

@Entity(name = "Set")
@Table(name = "set")
public class Set {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;
    private String name;
    private String category;
    private String description;
    @OneToMany(mappedBy = "flashcards", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private java.util.Set<javax.management.relation.Role> roles = new HashSet<Role>();


    public Set() {
    }

    public Set(String name, String category, String description) {
        this.name = name;
        this.category = category;
        this.description = description;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Set{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
