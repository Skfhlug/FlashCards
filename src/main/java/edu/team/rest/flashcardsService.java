package edu.team.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.team.entity.Flashcard;
import edu.team.entity.FlashcardSet;
import edu.team.persistence.GenericDao;

import javax.faces.context.Flash;
import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * The type Flashcards service.
 */
@Path("/flashcard")
public class flashcardsService {
    /**
     * The Flashcard dao.
     */
    GenericDao flashcardDao = new GenericDao(Flashcard.class);
    /**
     * The Set dao.
     */
    GenericDao setDao = new GenericDao(FlashcardSet.class);
    /**
     * The Mapper.
     */
    ObjectMapper mapper = new ObjectMapper();

    private final String SUCCESS = "{'result':'success'}";
    private final String FAILURE = "{'result':'failure'}";

    /**
     * Gets all sets.
     *
     * @return the all sets
     * @throws JsonProcessingException the json processing exception
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/sets")
    public Response getAllSets() throws JsonProcessingException {
        List<FlashcardSet> sets = setDao.getAll();
        String output = mapper.writeValueAsString(sets);

        return Response.status(200).entity(output).build();
    }

    /**
     * Gets set by id.
     *
     * @param id the id
     * @return the set by id
     * @throws JsonProcessingException the json processing exception
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/sets/{id}")
    public Response getSetById(@PathParam("id") int id) throws JsonProcessingException {
        FlashcardSet set = (FlashcardSet) setDao.getById(id);
        String output = mapper.writeValueAsString(set);

        return Response.status(200).entity(output).build();
    }

    /**
     * Search for set response.
     *
     * @param searchTerm the search term
     * @return the response
     * @throws JsonProcessingException the json processing exception
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/sets/search/{searchTerm}")
    public Response searchForSet(@PathParam("searchTerm") String searchTerm) throws JsonProcessingException {
        List<FlashcardSet> sets = setDao.getByPropertyLike("name", searchTerm);
        String output = mapper.writeValueAsString(sets);

        return Response.status(200).entity(output).build();
    }

    /**
     * Search for set by category response.
     *
     * @param searchTerm the search term
     * @return the response
     * @throws JsonProcessingException the json processing exception
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/sets/search/category/{searchTerm}")
    public Response searchForSetByCategory(@PathParam("searchTerm") String searchTerm) throws JsonProcessingException {
        List<FlashcardSet> sets = setDao.getByPropertyLike("category", searchTerm);
        String output = mapper.writeValueAsString(sets);

        return Response.status(200).entity(output).build();
    }

    /**
     * Gets cards from set.
     *
     * @param id the id
     * @return the cards from set
     * @throws JsonProcessingException the json processing exception
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/sets/cards/{id}")
    public Response getCardsFromSet(@PathParam("id") int id) throws JsonProcessingException {
        FlashcardSet set = (FlashcardSet) setDao.getById(id);
        String output = mapper.writeValueAsString(set.getFlashcards());

        return Response.status(200).entity(output).build();
    }

    /**
     * Gets all cards.
     *
     * @return the all cards
     * @throws JsonProcessingException the json processing exception
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/cards")
    public Response getAllCards() throws JsonProcessingException {
        List<Flashcard> cards = flashcardDao.getAll();
        String output = mapper.writeValueAsString(cards);

        return Response.status(200).entity(output).build();
    }

    /**
     * Gets card by id.
     *
     * @param id the id
     * @return the card by id
     * @throws JsonProcessingException the json processing exception
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/cards/{id}")
    public Response getCardById(@PathParam("id") int id) throws JsonProcessingException {
        Flashcard card = (Flashcard) flashcardDao.getById(id);
        String output = mapper.writeValueAsString(card);

        return Response.status(200).entity(output).build();
    }

    /**
     * Create set response.
     *
     * @param name        the name
     * @param category    the category
     * @param description the description
     * @return the response
     */
    @POST
    @Path("/sets")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createSet(@FormParam("name") String name,
                              @FormParam("category") String category,
                              @FormParam("description") String description) {
        String output = "";

        FlashcardSet newSet = new FlashcardSet(name, category, description);
        int result = setDao.insert(newSet);

        if(result == 1) {
            output = SUCCESS;
        } else {
            output = FAILURE;
        }

        return Response.status(200).entity(output).build();
    }

    /**
     * Add card to set response.
     *
     * @param question the question
     * @param answer   the answer
     * @param setId    the set id
     * @return the response
     */
    @POST
    @Path("/sets/cards")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response addCardToSet(@FormParam("question") String question,
                                 @FormParam("answer") String answer,
                                 @FormParam("id") int setId) {
        String output = "";

        FlashcardSet set = (FlashcardSet) setDao.getById(setId);
        Flashcard newCard = new Flashcard(set, question, answer);
        setDao.insert(newCard);

        return Response.status(200).entity(output).build();
    }

    /**
     * Update set response.
     *
     * @param id          the id
     * @param name        the name
     * @param category    the category
     * @param description the description
     * @return the response
     */
    @PUT
    @Path("/sets/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response updateSet(@FormParam("id") int id,
                              @FormParam("name") String name,
                              @FormParam("category") String category,
                              @FormParam("description") String description) {
        String output = "";

        FlashcardSet set = (FlashcardSet) setDao.getById(id);

        if (name.length() > 0) {
            set.setName(name);
        }

        if (category.length() > 0) {
            set.setCategory(category);
        }

        if (description.length() > 0) {
            set.setDescription(description);
        }

        setDao.saveOrUpdate(set);

        return Response.status(200).entity(output).build();
    }

    /**
     * Update card response.
     *
     * @param id       the id
     * @param question the question
     * @param answer   the answer
     * @param setId    the set id
     * @return the response
     */
    @PUT
    @Path("/cards/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response updateCard(@FormParam("id") int id,
                               @FormParam("question") String question,
                               @FormParam("answer") String answer,
                               @FormParam("setId") int setId) {
        String output = "";

        Flashcard card = (Flashcard) flashcardDao.getById(id);

        if (question.length() > 0) {
            card.setQuestion(question);
        }

        if (answer.length() > 0) {
            card.setAnswer(answer);
        }

        if (setId >= 0) {
            FlashcardSet set = (FlashcardSet) setDao.getById(setId);
            card.setFlashcardSet(set);
        }

        setDao.saveOrUpdate(card);

        return Response.status(200).entity(output).build();
    }
}