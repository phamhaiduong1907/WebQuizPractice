/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.commonController;

import dal.AccountDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import model.Account;

/**
 *
 * @author Zuys
 */
public class LoginController extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("email");
        String password = request.getParameter("password");
        AccountDBContext db = new AccountDBContext();
        Account account = db.getAccount(username, password);
        if(account == null)
        {
            request.getSession().setAttribute("login_status", "Wrong email or password, please try again!");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
        else
        {
            request.getSession().setAttribute("account", account);
            switch (account.getRole().getRoleID()) {
                case 1:
                    request.getRequestDispatcher("/view/customer/index.jsp").forward(request, response);
                    break;
                case 2:
                    request.getRequestDispatcher("/view/expert/index.jsp").forward(request, response);
                    break;
                case 3:
                    request.getRequestDispatcher("/view/marketing/index.jsp").forward(request, response);
                    break;
                case 4:
                    request.getRequestDispatcher("/view/sale/index.jsp").forward(request, response);
                    break;
                case 5:
                    request.getRequestDispatcher("/view/admin/index.jsp").forward(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
        }
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
