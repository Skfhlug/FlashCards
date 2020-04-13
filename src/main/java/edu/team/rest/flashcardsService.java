package edu.team.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import edu.team.entity.Flashcard;
import edu.team.entity.FlashcardSet;
import edu.team.exceptions.NotFoundException;
import edu.team.persistence.GenericDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;

/**
 * The type Flashcards service.
 */
@Path("/flashcards")
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
     * The Json Mapper.
     */
    ObjectMapper jsonMapper = new ObjectMapper();

    /**
     * The XML Mapper
     */
    XmlMapper xmlMapper = new XmlMapper();

   /**
     * Gets all sets, returning xml.
     *
     * @return the all sets
     * @throws JsonProcessingException the json processing exception
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/sets/xml")
    public Response getAllSetsXml() throws JsonProcessingException {
        List<FlashcardSet> sets = setDao.getAll();
        String output = xmlMapper.writeValueAsString(sets);

        return Response.status(200).entity(output).build();
    }

    /**
     * Gets all sets.
     *
     * @return the all sets
     * @throws JsonProcessingException the json processing exception
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/sets")
    public Response getAllSetsJson() throws JsonProcessingException {
        List<FlashcardSet> sets = setDao.getAll();
        String output = jsonMapper.writeValueAsString(sets);

        return Response.status(200).entity(output).build();
    }

    /**
     * Gets set by id, returning xml.
     *
     * @param id the id
     * @return the set by id
     * @throws JsonProcessingException the json processing exception
     * @throws NotFoundException       the not found exception
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/sets/{id}/xml")
    public Response getSetByIdXml(@PathParam("id") int id) throws JsonProcessingException, NotFoundException {
        FlashcardSet set = searchSetById(id);
        String output = xmlMapper.writeValueAsString(set);

        return Response.status(200).entity(output).build();
    }

    /**
     * Gets set by id.
     *
     * @param id the id
     * @return the set by id
     * @throws JsonProcessingException the json processing exception
     * @throws NotFoundException       the not found exception
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/sets/{id}")
    public Response getSetByIdJson(@PathParam("id") int id) throws JsonProcessingException, NotFoundException {
        FlashcardSet set = searchSetById(id);

        String output = jsonMapper.writeValueAsString(set);

        return Response.status(200).entity(output).build();
    }

    /**
     * Search for a set by id
     *
     * @param id
     * @return
     * @throws NotFoundException
     */
    private FlashcardSet searchSetById(int id) throws NotFoundException {
        FlashcardSet set = (FlashcardSet) setDao.getById(id);

        if (set == null) {
            throw new NotFoundException("Set with id " + id + " was not found");
        }

        return set;
    }

    /**
     * Search for set by name, returning xml.
     *
     * @param searchTerm the search term
     * @return the response
     * @throws JsonProcessingException the json processing exception
     * @throws NotFoundException       the not found exception
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/sets/search/{searchTerm}/xml")
    public Response searchForSetXml(@PathParam("searchTerm") String searchTerm) throws JsonProcessingException, NotFoundException {
        List<FlashcardSet> sets = searchSetByName(searchTerm);
        String output = xmlMapper.writeValueAsString(sets);

        return Response.status(200).entity(output).build();
    }

    /**
     * Search for set by name.
     *
     * @param searchTerm the search term
     * @return the response
     * @throws JsonProcessingException the json processing exception
     * @throws NotFoundException       the not found exception
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/sets/search/{searchTerm}")
    public Response searchForSetJson(@PathParam("searchTerm") String searchTerm) throws JsonProcessingException, NotFoundException {
        List<FlashcardSet> sets = searchSetByName(searchTerm);
        String output = jsonMapper.writeValueAsString(sets);

        return Response.status(200).entity(output).build();
    }

    /**
     * Search for a set by name (w/ exception handling)
     *
     * @param searchTerm
     * @return
     * @throws NotFoundException
     */
    private List<FlashcardSet> searchSetByName(String searchTerm) throws NotFoundException {
        List<FlashcardSet> results = setDao.getByPropertyLike("name", searchTerm);

        if (results.size() == 0) {
            throw new NotFoundException("There were no sets containing '" + searchTerm + "' in the name");
        }

        return results;
    }

    /**
     * Search for set by category, returning xml.
     *
     * @param searchTerm the search term
     * @return the response
     * @throws JsonProcessingException the json processing exception
     * @throws NotFoundException       the not found exception
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/sets/search/category/{searchTerm}/xml")
    public Response searchForSetByCategoryXml(@PathParam("searchTerm") String searchTerm) throws JsonProcessingException, NotFoundException {
        List<FlashcardSet> sets = searchSetByCategory(searchTerm);
        String output = xmlMapper.writeValueAsString(sets);

        return Response.status(200).entity(output).build();
    }

    /**
     * Search for set by category.
     *
     * @param searchTerm the search term
     * @return the response
     * @throws JsonProcessingException the json processing exception
     * @throws NotFoundException       the not found exception
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/sets/search/category/{searchTerm}")
    public Response searchForSetByCategoryJson(@PathParam("searchTerm") String searchTerm) throws JsonProcessingException, NotFoundException {
        List<FlashcardSet> sets = searchSetByCategory(searchTerm);
        String output = jsonMapper.writeValueAsString(sets);

        return Response.status(200).entity(output).build();
    }

    /**
     * Search for a set by category (w/ exception handling)
     * @param searchTerm
     * @return
     * @throws NotFoundException
     */
    private List<FlashcardSet> searchSetByCategory(String searchTerm) throws NotFoundException {
        List<FlashcardSet> results = setDao.getByPropertyLike("category", searchTerm);

        if (results.size() == 0) {
            throw new NotFoundException("There were no sets containing '" + searchTerm + "' in their category");
        }

        return results;
    }

    /**
     * Gets cards from set, returning xml.
     *
     * @param id the id
     * @return the cards from set
     * @throws JsonProcessingException the json processing exception
     * @throws NotFoundException       the not found exception
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/sets/cards/{id}/xml")
    public Response getCardsFromSetXml(@PathParam("id") int id) throws JsonProcessingException, NotFoundException {
        String output = xmlMapper.writeValueAsString(getCardsInSet(id));

        return Response.status(200).entity(output).build();
    }

    /**
     * Gets cards from set.
     *
     * @param id the id
     * @return the cards from set
     * @throws JsonProcessingException the json processing exception
     * @throws NotFoundException       the not found exception
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/sets/cards/{id}")
    public Response getCardsFromSetJson(@PathParam("id") int id) throws JsonProcessingException, NotFoundException {
        String output = jsonMapper.writeValueAsString(getCardsInSet(id));

        return Response.status(200).entity(output).build();
    }

    /**
     * Get all cards in a set by set id
     * @param id
     * @return
     * @throws NotFoundException
     */
    private Set<Flashcard> getCardsInSet(int id) throws NotFoundException {
        FlashcardSet set = (FlashcardSet) setDao.getById(id);

        if (set == null) {
            throw new NotFoundException("Set with id " + id + " was not found");
        }

        Set<Flashcard> cardsInSet = set.getFlashcards();

        if (cardsInSet.size() == 0) {
            throw new NotFoundException("There were no cards in set with id " + id);
        }

        return cardsInSet;
    }

    /**
     * Gets all cards, returning xml.
     *
     * @return the all cards
     * @throws JsonProcessingException the json processing exception
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/cards/xml")
    public Response getAllCardsXml() throws JsonProcessingException {
        List<Flashcard> cards = flashcardDao.getAll();
        String output = xmlMapper.writeValueAsString(cards);

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
    public Response getAllCardsJson() throws JsonProcessingException {
        List<Flashcard> cards = flashcardDao.getAll();
        String output = jsonMapper.writeValueAsString(cards);

        return Response.status(200).entity(output).build();
    }

    /**
     * Gets card by id, returning xml.
     *
     * @param id the id
     * @return the card by id
     * @throws JsonProcessingException the json processing exception
     * @throws NotFoundException       the not found exception
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/cards/{id}/xml")
    public Response getCardByIdXml(@PathParam("id") int id) throws JsonProcessingException, NotFoundException {
        Flashcard card = searchCardById(id);
        String output = xmlMapper.writeValueAsString(card);

        return Response.status(200).entity(output).build();
    }

    /**
     * Gets card by id.
     *
     * @param id the id
     * @return the card by id
     * @throws JsonProcessingException the json processing exception
     * @throws NotFoundException       the not found exception
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/cards/{id}")
    public Response getCardByIdJson(@PathParam("id") int id) throws JsonProcessingException, NotFoundException {
        Flashcard card = searchCardById(id);
        String output = jsonMapper.writeValueAsString(card);

        return Response.status(200).entity(output).build();
    }

    /**
     * Search for a card by id (w/ exception handling)
     * @param id
     * @return
     * @throws NotFoundException
     */
    private Flashcard searchCardById(int id) throws NotFoundException {
        Flashcard card = (Flashcard) flashcardDao.getById(id);

        if (card == null) {
            throw new NotFoundException("Card with id " + id + " was not found");
        }

        return card;
    }

    /**
     * Create Flashcard Set.
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
        setDao.insert(newSet);

        return Response.status(200).entity(output).build();
    }

    /**
     * Add card to Flashcard Set.
     *
     * @param question the question
     * @param answer   the answer
     * @param setId    the set id
     * @return the response
     * @throws NotFoundException the not found exception
     */
    @POST
    @Path("/sets/cards")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response addCardToSet(@FormParam("question") String question,
                                 @FormParam("answer") String answer,
                                 @FormParam("id") int setId) throws NotFoundException {
        String output = "";

        FlashcardSet set = searchSetById(setId);
        Flashcard newCard = new Flashcard(set, question, answer);
        setDao.insert(newCard);

        return Response.status(200).entity(output).build();
    }

    /**
     * Update Flashcard Set.
     *
     * @param id          the id
     * @param name        the name
     * @param category    the category
     * @param description the description
     * @return the response
     * @throws NotFoundException the not found exception
     */
    @PUT
    @Path("/sets/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response updateSet(@FormParam("id") int id,
                              @FormParam("name") String name,
                              @FormParam("category") String category,
                              @FormParam("description") String description) throws NotFoundException {
        String output = "";

        FlashcardSet set = searchSetById(id);

        if (name != null) {
            set.setName(name);
        }

        if (category != null) {
            set.setCategory(category);
        }

        if (description != null) {
            set.setDescription(description);
        }

        setDao.saveOrUpdate(set);

        return Response.status(200).entity(output).build();
    }

    /**
     * Update Flashcard.
     *
     * @param id       the id
     * @param question the question
     * @param answer   the answer
     * @param setId    the set id
     * @return the response
     * @throws NotFoundException the not found exception
     */
    @PUT
    @Path("/cards/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response updateCard(@FormParam("id") int id,
                               @FormParam("question") String question,
                               @FormParam("answer") String answer,
                               @FormParam("setId") int setId) throws NotFoundException {
        String output = "";

        Flashcard card = searchCardById(id);

        if (question != null) {
            card.setQuestion(question);
        }

        if (answer != null) {
            card.setAnswer(answer);
        }

        if (setId > 0) {
            FlashcardSet set = searchSetById(setId);
            if (card.getFlashcardSet().getId() != set.getId()) {
                card.setFlashcardSet(set);
            }
        }

        setDao.saveOrUpdate(card);

        return Response.status(200).entity(output).build();
    }
}