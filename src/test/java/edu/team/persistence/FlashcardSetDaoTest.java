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

public class FlashcardSetDaoTest {
    GenericDao<FlashcardSet> genericDao;
    GenericDao<Flashcard> genericDaoFlashcard;

    @BeforeEach
    void setUp() {

        genericDao = new GenericDao<>(FlashcardSet.class);
        genericDaoFlashcard = new GenericDao<>(Flashcard.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    @Test
    void getAllFlashcardSets() {
        List<FlashcardSet> flashcardSets = genericDao.getAll();
        assertEquals(4, flashcardSets.size());
    }

    @Test
    void getIdSuccess() {
        FlashcardSet retrievedFlashcardSet = genericDao.getById(1);
        assertEquals(1, retrievedFlashcardSet.getId());
    }

    @Test
    void insertSuccess() {
        FlashcardSet flashcardSet = new FlashcardSet("IT", "JavaScript", "Basic JavaScriptQuestions");
        int id = genericDao.insert(flashcardSet);
        assertNotEquals(0, id);
        FlashcardSet insertedFlashcardSet = genericDao.getById(id);
        assertEquals(flashcardSet, insertedFlashcardSet);
    }

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

    @Test
    void updateFlashcardSuccess() {
        FlashcardSet flashcardToUpdateSet = genericDao.getById(4);
        flashcardToUpdateSet.setCategory("History");
        genericDao.saveOrUpdate(flashcardToUpdateSet);
        assertEquals(flashcardToUpdateSet.getCategory(), "History");
    }

    @Test
    void getByPropertyEqualSuccess() {
        List<FlashcardSet> flashcardSets = genericDao.getByPropertyEqual("name", "IT");
        assertEquals(1, flashcardSets.size());
    }

    @Test
    void getPropertyLikeSuccess() {
        List<FlashcardSet> flashcardSets = genericDao.getByPropertyLike("description", "High school");
        assertEquals(2, flashcardSets.size());
    }
}
