package edu.preschool.controller;

import edu.preschool.entity.Role;
import edu.preschool.entity.User;
import edu.preschool.persitence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet(
        name = "addRole",
        urlPatterns = { "/addRole" }
)
public class addRole  extends HttpServlet{
    private final Logger logger = LogManager.getLogger(this.getClass());
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Role role = new Role();
        GenericDao genericDao = new GenericDao(Role.class);
        HttpSession session = request.getSession();

        //Integer user_id = (Integer) session.getAttribute("added_id");
        User user;
        User user_id = (User) session.getAttribute("added_id");
        String added_username = (String) session.getAttribute("added_username");
        String new_user_id = (String) session.getAttribute("added_id");
        //System.out.println(user_id);
        role.setUsername(added_username);
        role.setRole_name(request.getParameter("role"));



        int id = genericDao.insert(role);
        session.setAttribute("sessionAdd", "Added User Successful");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/addRole.jsp");
        dispatcher.forward(request, response);
    }
}
