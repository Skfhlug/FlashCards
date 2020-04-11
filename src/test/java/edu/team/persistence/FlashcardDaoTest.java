package edu.team.persistence;

import edu.team.entity.FlashcardSet;
import edu.team.entity.Flashcard;

import edu.team.util.Database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Flashcard dao test.
 */
public class FlashcardDaoTest {
    /**
     * The Generic dao.
     */
    GenericDao<Flashcard> genericDao;
    /**
     * The Generic dao flashcard set.
     */
    GenericDao<FlashcardSet> genericDaoFlashcardSet;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {

        genericDao = new GenericDao<>(Flashcard.class);
        genericDaoFlashcardSet = new GenericDao<>(FlashcardSet.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * Gets all flashcards.
     */
    @Test
    void getAllFlashcards() {
        List<Flashcard> flashcards = genericDao.getAll();
        assertEquals(14, flashcards.size());
    }

    /**
     * Gets id success.
     */
    @Test
    void getIdSuccess() {
        Flashcard retrievedFlashcard = genericDao.getById(13);
        assertEquals(13, retrievedFlashcard.getId());
    }

    /**
     * Insert success.
     */
    @Test
    void insertSuccess() {
        FlashcardSet flashcardSet = new FlashcardSet("Javascript", "Computer Science", "Simple computer science questions");
        genericDaoFlashcardSet.insert(flashcardSet);
        Flashcard flashcard = new Flashcard(flashcardSet, "What is the difference between 'let' and 'const'?", "Let value can change and const should remain constant.");
        int id = genericDao.insert(flashcard);
        assertNotEquals(0, id);
        Flashcard insertedFlashcard = genericDao.getById(id);
        assertEquals(insertedFlashcard.getAnswer(), flashcard.getAnswer());
    }

    /**
     * Delete success.
     */
    @Test
    void deleteSuccess() {
        Flashcard deletedFlashcard = genericDao.getById(3);
        int flashcardSetId = deletedFlashcard.getFlashcardSet().getId();
        FlashcardSet deletedFlashcardSet = genericDaoFlashcardSet.getById(flashcardSetId);
        genericDao.delete(deletedFlashcard);
        assertNull(genericDao.getById(3));
        assertNotNull(deletedFlashcardSet);
    }

    /**
     * Update flashcard success.
     */
    @Test
    void updateFlashcardSuccess() {
        Flashcard flashcardToUpdate = genericDao.getById(11);
        flashcardToUpdate.setAnswer("true");
        genericDao.saveOrUpdate(flashcardToUpdate);
        assertEquals(flashcardToUpdate.getAnswer(), "true");
    }

    /**
     * Gets by property equal success.
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<Flashcard> flashcard = genericDao.getByPropertyEqual("question", "What was the first name of JAVA?");
        assertEquals(1, flashcard.size());
    }

    /**
     * Gets property like success.
     */
    @Test
    void getPropertyLikeSuccess() {
        List<Flashcard> flashcard = genericDao.getByPropertyLike("question", "What is the capital of");
        assertEquals(5, flashcard.size());
    }
}
