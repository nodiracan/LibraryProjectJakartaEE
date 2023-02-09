package com.library.servlets;

import com.library.dao.BookDAO;
import com.library.dao.CategoryDAO;
import com.library.dto.BookCreateDTO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

@WebServlet(name = "BookAddServlet", value = "/books/add")
public class BookAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setAttribute("categories", CategoryDAO.getAllCategory());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/books/add.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookDAO bookDAO = BookDAO.getInstance();
        CategoryDAO categoryDAO = CategoryDAO.getInstance();
        int categoryId = categoryDAO.getCategoryIdFromName(request.getParameter("categoryName"));
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String description = request.getParameter("description");
        int cost = Integer.parseInt((request.getParameter("cost")));
        int publishedYear = Integer.parseInt(request.getParameter("publishedYear"));
        bookDAO.save(new BookCreateDTO(title, author,description,cost,categoryId,publishedYear));
        response.sendRedirect("/books");
    }
}
