package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Candidate;
import business.CandidateForm;
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
	    
		Candidate candidate = new Candidate();
		candidate.setFirstname(request.getParameter("firstname"));
		candidate.setLastname(request.getParameter("lastname"));
		candidate.setEmail(request.getParameter("email"));
		candidate.setEmail(request.getParameter("password"));
		CandidateForm candidateForm = new CandidateForm();
		
	    
	    if(candidateForm.executeInscription())
	    	request.setAttribute("optionalMessage", "succesfull inscription!");
	    else
	    	request.setAttribute("optionalMessage", "A problem occured during the process :(");
	    	
	    RequestDispatcher view = request.getRequestDispatcher("WEB-INF/candidate.jsp");
		view.forward(request, response);
	}

}

// Test 
