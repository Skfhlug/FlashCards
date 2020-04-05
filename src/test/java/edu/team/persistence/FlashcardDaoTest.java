package edu.team.persistence;

import edu.team.entity.FlashcardSet;
import edu.team.entity.Flashcard;

import edu.team.util.Database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FlashcardDaoTest {
    GenericDao<Flashcard> genericDao;
    GenericDao<FlashcardSet> genericDaoFlashcardSet;

    @BeforeEach
    void setUp() {

        genericDao = new GenericDao<>(Flashcard.class);
        genericDaoFlashcardSet = new GenericDao<>(FlashcardSet.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    @Test
    void getAllFlashcards() {
        List<Flashcard> flashcards = genericDao.getAll();
        assertEquals(14, flashcards.size());
    }

    @Test
    void getIdSuccess() {
        Flashcard retrievedFlashcard = genericDao.getById(13);
        assertEquals(13, retrievedFlashcard.getId());
    }

//    @Test
//    void insertSuccess() {
//        FlashcardSet flashcardSet = genericDaoFlashcardSet.getById(5);
//        Flashcard flashcard = new Flashcard(flashcardSet, "What is the difference between 'let' and 'const'?", "Let value can change and const should remain constant.");
//        int id = genericDao.insert(flashcard);
//        assertNotEquals(0, id);
//        Flashcard insertedFlashcard = genericDao.getById(id);
//        assertEquals(insertedFlashcard.getAnswer(), flashcard.getAnswer());
//    }

    @Test
    void deleteSuccess() {
        Flashcard deletedFlashcard = genericDao.getById(3);
        int flashcardSetId = deletedFlashcard.getFlashcardSet().getId();
        FlashcardSet deletedFlashcardSet = genericDaoFlashcardSet.getById(flashcardSetId);
        genericDao.delete(deletedFlashcard);
        assertNull(genericDao.getById(3));
        assertNotNull(deletedFlashcardSet);
    }

    @Test
    void updateFlashcardSuccess() {
        Flashcard flashcardToUpdate = genericDao.getById(11);
        flashcardToUpdate.setAnswer("true");
        genericDao.saveOrUpdate(flashcardToUpdate);
        assertEquals(flashcardToUpdate.getAnswer(), "true");
    }

    @Test
    void getByPropertyEqualSuccess() {
        List<Flashcard> flashcard = genericDao.getByPropertyEqual("question", "What was the first name of JAVA?");
        assertEquals(1, flashcard.size());
    }

    @Test
    void getPropertyLikeSuccess() {
        List<Flashcard> flashcard = genericDao.getByPropertyLike("question", "What is the capital of");
        assertEquals(5, flashcard.size());
    }
}
