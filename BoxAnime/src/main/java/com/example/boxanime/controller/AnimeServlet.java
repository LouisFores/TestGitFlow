package com.example.boxanime.controller;

import com.example.boxanime.model.Anime;
import com.example.boxanime.service.AnimeSQL;

import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AnimeServlet", urlPatterns = "/anime")
public class AnimeServlet extends HttpServlet {
    private AnimeSQL animeSQL;

    @Override
    public void init() throws ServletException {
        animeSQL = new AnimeSQL();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    showNewForm(req, resp);
                    break;
                case "update":
                    showEditForm(req, resp);
                    break;
                case "delete":
                    deleteAnime(req, resp);
                    break;
                case "search":
                    searchAnime(req, resp);
                    break;
                default:
                    listAnime(req, resp);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showSearchForm(HttpServletRequest req, HttpServletResponse resp) {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    insertAnime(req, resp);
                    break;
                case "update":
                    updateAnime(req, resp);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void searchAnime(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        String name = req.getParameter("name");
        List<Anime> listAnime = animeSQL.findAllAnime(name);
        req.setAttribute("listAnime", listAnime);
        RequestDispatcher dispatcher = req.getRequestDispatcher("view/search.jsp");
        dispatcher.forward(req, resp);
    }

    private void insertAnime(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        String name = req.getParameter("name");
        String status = req.getParameter("status");
        String link = req.getParameter("link");
        String picture = req.getParameter("picture");
        int year = Integer.parseInt(req.getParameter("year"));
        Anime anime = new Anime(name, status, link, picture, year);
        animeSQL.insertAnime(anime);

        //switch to another tab
        //resp.sendRedirect("view/list");

        //do not switch tabs
        RequestDispatcher dispatcher = req.getRequestDispatcher("view/create.jsp");
        dispatcher.forward(req, resp);
    }


    private void listAnime(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException{
        List<Anime> listAnime = animeSQL.selectAllAnime();
        req.setAttribute("listAnime", listAnime);
        RequestDispatcher dispatcher = req.getRequestDispatcher("view/list.jsp");
        dispatcher.forward(req, resp);
    }
    private void updateAnime(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String status = req.getParameter("status");
        String link = req.getParameter("link");
        String picture = req.getParameter("picture");
        int year = Integer.parseInt(req.getParameter("year"));

        Anime anime = new Anime(id, name, status, link, picture, year);
        animeSQL.updateAnime(anime);

        List<Anime> listAnime = animeSQL.selectAllAnime();
        req.setAttribute("listAnime", listAnime);
        RequestDispatcher dispatcher = req.getRequestDispatcher("view/list.jsp");
        dispatcher.forward(req, resp);
    }
    private void deleteAnime(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));
        animeSQL.deleteAnime(id);
        List<Anime> listAnime = animeSQL.selectAllAnime();
        req.setAttribute("listAnime", listAnime);
        req.getRequestDispatcher("view/list.jsp").forward(req, resp);
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException{
        RequestDispatcher dispatcher = req.getRequestDispatcher("view/create.jsp");
        dispatcher.forward(req, resp);
    }
    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));
        Anime anime = animeSQL.selectAnime(id);
        req.setAttribute("anime", anime);
        RequestDispatcher dispatcher = req.getRequestDispatcher("view/update.jsp");
        dispatcher.forward(req, resp);
    }

    private void fixFontVN(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
    }
}
