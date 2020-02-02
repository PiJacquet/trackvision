package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class indexServlet
 */
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public IndexServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if((Integer)request.getAttribute("isIdentified") == 2) {
			// If The user is an administrator identified, we redirect him
			response.sendRedirect("/tv/dashboard"); 
			return;
		}
		
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/index.jsp");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/index.jsp");
		view.forward(request, response);
	}

}
