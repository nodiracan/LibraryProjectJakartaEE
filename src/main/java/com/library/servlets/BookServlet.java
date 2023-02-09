package com.library.servlets;

import com.library.dao.BookDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "BookServlet", value = "/books")
public class BookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookDAO bookDAO = BookDAO.getInstance();
        short page = Short.parseShort(Objects.requireNonNullElse(request.getParameter("page"), "0"));
        short size = Short.parseShort(Objects.requireNonNullElse(request.getParameter("size"), "3"));
        long totalCount = bookDAO.totalCount();
        long pageCount = totalCount / size;
        long currentPage = page;
        request.setAttribute("books", bookDAO.findAll(page, size));
        request.setAttribute("pageCount", pageCount);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("hasPrevious", currentPage > 0);
        request.setAttribute("hasNext", currentPage < pageCount);
        request.setAttribute("previous", page - 1);
        request.setAttribute("next", page + 1);
//        response.setIntHeader("Refresh", 2);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/books/books.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!req.getMethod().equalsIgnoreCase("get"))
            resp.sendError(405, "Method not allowed");
        else
            super.service(req, resp);
    }
}
