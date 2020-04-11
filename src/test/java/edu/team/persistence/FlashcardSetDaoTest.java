package edu.team.persistence;

import edu.team.entity.FlashcardSet;
import edu.team.entity.Flashcard;

import edu.team.util.Database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Flashcard set dao test.
 */
public class FlashcardSetDaoTest {
    /**
     * The Generic dao.
     */
    GenericDao<FlashcardSet> genericDao;
    /**
     * The Generic dao flashcard.
     */
    GenericDao<Flashcard> genericDaoFlashcard;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {

        genericDao = new GenericDao<>(FlashcardSet.class);
        genericDaoFlashcard = new GenericDao<>(Flashcard.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * Gets all flashcard sets.
     */
    @Test
    void getAllFlashcardSets() {
        List<FlashcardSet> flashcardSets = genericDao.getAll();
        assertEquals(4, flashcardSets.size());
    }

    /**
     * Gets id success.
     */
    @Test
    void getIdSuccess() {
        FlashcardSet retrievedFlashcardSet = genericDao.getById(1);
        assertEquals(1, retrievedFlashcardSet.getId());
    }

    /**
     * Insert success.
     */
    @Test
    void insertSuccess() {
        FlashcardSet flashcardSet = new FlashcardSet("IT", "JavaScript", "Basic JavaScriptQuestions");
        int id = genericDao.insert(flashcardSet);
        assertNotEquals(0, id);
        FlashcardSet insertedFlashcardSet = genericDao.getById(id);
        assertEquals(flashcardSet, insertedFlashcardSet);
    }

    /**
     * Delete success.
     */
    @Test
    void deleteSuccess() {
        FlashcardSet deletedFlashcardSet = genericDao.getById(3);
        Set<Flashcard> deletedFlashcardSetFlashCards = deletedFlashcardSet.getFlashcards();
        List<Integer> idOfFlashcards = new ArrayList<>();
        for (Flashcard f : deletedFlashcardSetFlashCards) {
            idOfFlashcards.add(f.getId());
        }
        genericDao.delete(genericDao.getById(3));
        assertNull(genericDao.getById(3));
        for (Integer id : idOfFlashcards) {
            assertNull(genericDaoFlashcard.getById(id));
        }
    }

    /**
     * Update flashcard success.
     */
    @Test
    void updateFlashcardSuccess() {
        FlashcardSet flashcardToUpdateSet = genericDao.getById(4);
        flashcardToUpdateSet.setCategory("History");
        genericDao.saveOrUpdate(flashcardToUpdateSet);
        assertEquals(flashcardToUpdateSet.getCategory(), "History");
    }

    /**
     * Gets by property equal success.
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<FlashcardSet> flashcardSets = genericDao.getByPropertyEqual("name", "IT");
        assertEquals(1, flashcardSets.size());
    }

    /**
     * Gets property like success.
     */
    @Test
    void getPropertyLikeSuccess() {
        List<FlashcardSet> flashcardSets = genericDao.getByPropertyLike("description", "High school");
        assertEquals(2, flashcardSets.size());
    }
}
