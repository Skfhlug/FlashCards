package edu.team.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.management.relation.Role;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "FlashcardSet")
@Table(name = "flashcardset")
public class FlashcardSet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

   @Column(name = "name")

    private String name;

    @Column(name = "category")
    private String category;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "flashcardSet", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Flashcard> flashcards = new HashSet<>();

    public FlashcardSet() {
    }

    public FlashcardSet(String name, String category, String description) {
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

    public Set<Flashcard> getFlashcards() { return flashcards; }

    public void setFlashcards(Set<Flashcard> flashcards) { this.flashcards = flashcards; }

    public void addFlashcard(Flashcard flashcard) {
    flashcards.add(flashcard);
    flashcard.setFlashcardSet(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FlashcardSet that = (FlashcardSet) o;

        if (id != that.id) return false;
        if (!Objects.equals(name, that.name)) return false;
        if (!Objects.equals(category, that.category)) return false;
        return Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FlashcardSet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
