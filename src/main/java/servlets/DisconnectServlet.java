package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Configuration;

/**
 * Servlet implementation class addEmployeeServlet
 */
public class DisconnectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisconnectServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Clean the cache
		Configuration.connectedAccounts.disconnectAccount((String) request.getAttribute("TokenTV"));
		// Delete the cookie
		Cookie deleteToken = new Cookie("TokenTV", "");
		deleteToken.setMaxAge(0);
		response.addCookie(deleteToken);
		// Clean the attribute
		request.setAttribute("TokenTV",null);
		response.sendRedirect("/tv");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    doGet(request, response);
	}

}
