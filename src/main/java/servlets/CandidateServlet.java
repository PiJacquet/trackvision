package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.ResidentForm;
import common.Configuration;

/**
 * Servlet implementation class addEmployeeServlet
 */
public class CandidateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CandidateServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/candidate.jsp");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		ResidentForm form = new ResidentForm(request.getParameter("firstname"), request.getParameter("lastname"), request.getParameter("email"));
	    
	    if(form.executeInscription())
	    	request.setAttribute("optionalMessage", "The employee was added!");
	    else
	    	request.setAttribute("optionalMessage", "A problem occured during the process :(");
	    	
	    RequestDispatcher view = request.getRequestDispatcher("WEB-INF/candidate.jsp");
		view.forward(request, response);
	}

}

// Test 
