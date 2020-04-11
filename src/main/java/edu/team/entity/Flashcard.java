package edu.team.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

/**
 * The type Flashcard.
 */
@Entity(name = "Flashcard")
@Table(name = "flashcard")
public class Flashcard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @Column(name = "question")
    private String question;

    @Column(name = "answer")
    private String answer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "set_id",
            foreignKey = @ForeignKey(name = "flashcards_set_set_id_fk")
    )
    private FlashcardSet flashcardSet;

    /**
     * Instantiates a new Flashcard.
     */
    public Flashcard() {
    }

    /**
     * Instantiates a new Flashcard.
     *
     * @param flashcardSet the flashcard set
     * @param question     the question
     * @param answer       the answer
     */
    public Flashcard(FlashcardSet flashcardSet, String question, String answer) {
        this.flashcardSet = flashcardSet;
        this.question = question;
        this.answer = answer;
    }

    /**
     * Gets flashcard set.
     *
     * @return the flashcard set
     */
    public FlashcardSet getFlashcardSet() { return flashcardSet; }

    /**
     * Sets flashcard set.
     *
     * @param flashcardSet the flashcard set
     */
    public void setFlashcardSet(FlashcardSet flashcardSet) { this.flashcardSet = flashcardSet; }

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
     * Gets question.
     *
     * @return the question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Sets question.
     *
     * @param question the question
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * Gets answer.
     *
     * @return the answer
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Sets answer.
     *
     * @param answer the answer
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flashcard flashcard = (Flashcard) o;

        if (id != flashcard.id) return false;
        if (!Objects.equals(question, flashcard.question)) return false;
        return (!Objects.equals(answer, flashcard.answer));
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, answer);
    }


    @Override
    public String toString() {
        return "Flashcard{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
