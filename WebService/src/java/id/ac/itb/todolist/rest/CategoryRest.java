/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.itb.todolist.rest;

import id.ac.itb.todolist.dao.CategoryDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import id.ac.itb.todolist.model.Category;
import org.json.JSONArray;

/**
 *
 * @author Raymond
 */
public class CategoryRest extends HttpServlet {

    private Pattern regexUpdate = Pattern.compile("^/update/([\\w._%].*)/([\\w._%].*)$");
    private Pattern regexCategoryDetail = Pattern.compile("^/detil/([0-9]{1,})$");
    private Pattern regexSearchCategory = Pattern.compile("^/search/([\\w._%].*)/([\\d]{1,})/([\\d]{1,})$");
    private Pattern regexCategoryNames = Pattern.compile("^/$");
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String pathInfo = request.getPathInfo();
        Matcher matcher;

        matcher = regexCategoryDetail.matcher(pathInfo);
        if (matcher.find()) {
            CategoryDao categoryDao = new CategoryDao();
            out.print(categoryDao.getCategory(Integer.parseInt(matcher.group(1))).toJsonObject());
            return;
        }

        matcher = regexSearchCategory.matcher(pathInfo);
        if (matcher.find()) {
            System.out.println("MATCH");
            CategoryDao categoryDao = new CategoryDao();
            Collection<Category> result = categoryDao.getCategorySearch(matcher.group(1), Integer.parseInt( matcher.group(2)), Integer.parseInt(matcher.group(3)));
            out.print(new JSONArray(result));
            return;
        }
         matcher = regexUpdate.matcher(pathInfo);
        if (matcher.find()) {
            CategoryDao categoryDao = new CategoryDao();
            out.print(categoryDao.getCategory(Integer.parseInt(matcher.group(1))).toJsonObject());
            return;
        }
         matcher = regexCategoryNames.matcher(pathInfo);
        if (matcher.find()) {
            CategoryDao categoryDao = new CategoryDao();
            out.print(categoryDao.getCategory(Integer.parseInt(matcher.group(1))).toJsonObject());
            return;
        }
        throw new ServletException("Invalid URI");
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
