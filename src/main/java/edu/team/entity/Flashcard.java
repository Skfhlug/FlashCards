package edu.team.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

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
    @JoinColumn(name = "set_id",
            foreignKey = @ForeignKey(name = "flashcards_set_set_id_fk")
    )
    private FlashcardSet flashcardSet;

    public Flashcard() {
    }

    public Flashcard(FlashcardSet flashcardSet, String question, String answer) {
        this.flashcardSet = flashcardSet;
        this.question = question;
        this.answer = answer;
    }

    public FlashcardSet getFlashcardSet() { return flashcardSet; }

    public void setFlashcardSet(FlashcardSet flashcardSet) { this.flashcardSet = flashcardSet; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

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
