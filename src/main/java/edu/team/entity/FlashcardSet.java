package edu.team.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.management.relation.Role;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * The type Flashcard set.
 */
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

    /**
     * Instantiates a new Flashcard set.
     */
    public FlashcardSet() {
    }

    /**
     * Instantiates a new Flashcard set.
     *
     * @param name        the name
     * @param category    the category
     * @param description the description
     */
    public FlashcardSet(String name, String category, String description) {
        this.name = name;
        this.category = category;
        this.description = description;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets category.
     *
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets category.
     *
     * @param category the category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets flashcards.
     *
     * @return the flashcards
     */
    public Set<Flashcard> getFlashcards() { return flashcards; }

    /**
     * Sets flashcards.
     *
     * @param flashcards the flashcards
     */
    public void setFlashcards(Set<Flashcard> flashcards) { this.flashcards = flashcards; }

    /**
     * Add flashcard.
     *
     * @param flashcard the flashcard
     */
    public void addFlashcard(Flashcard flashcard) {
    flashcards.add(flashcard);
    flashcard.setFlashcardSet(this);
    }

    /**
     * Remove flash card.
     *
     * @param flashcard the flashcard
     */
    public void removeFlashCard(Flashcard flashcard) {
        flashcards.remove(flashcard);
        flashcard.setFlashcardSet(null);
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
        return Objects.hash(name, category, description);
    }

    @Override
    public String toString() {
        return "FlashcardSet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", flashcards=" + flashcards + '\'' +
                '}';
    }
}
