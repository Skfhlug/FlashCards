package edu.team.controller;

import edu.team.entity.Flashcard;
import edu.team.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

@WebServlet(
        name = "flashcardServlet",
        urlPatterns = { "/flashcardServlet" }
)
public class FlashcardsServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        Flashcard flashcard = new Flashcard();
        GenericDao dao = new GenericDao(Flashcard.class);
        Random random = new Random();
        int random_id = random.nextInt(13);
        random_id += 1;
        String id = String.valueOf(random_id);

        request.setAttribute("questionAnswer", dao.getByPropertyEqual("id", id));

        String url = "/test.jsp";

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
