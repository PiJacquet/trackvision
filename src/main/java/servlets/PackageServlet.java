package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Package;
import business.PackageForm;
import business.map.Map;
import business.ResidentForm;
import common.Configuration;

/**
 * Servlet implementation class PackageSServlet
 */
public class PackageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PackageServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/package.jsp");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		String[] checkbox = request.getParameterValues("connected_object");
		String[] checkbox2 = request.getParameterValues("meal");
		
		Package Package = new Package();
		Package.setPackage_name(request.getParameter("package_name"));
		//System.out.println("cool");
        //System.out.println(request.getParameter("price"));
		Package.setPrice(Integer.parseInt(request.getParameter("price")));
		Package.setDescription(request.getParameter("description"));
		
		if (checkbox != null) {
		Package.setConnected_object(Integer.parseInt("50"));}
		else {
		Package.setConnected_object(Integer.parseInt("0"));
		}
		
		
		if (checkbox2 != null) {
			Package.setMeal(Integer.parseInt("10")); }
			else {
			Package.setMeal(Integer.parseInt("0"));
			}
		
		
		Package.setTotal_price(Package.getPrice()+Package.getConnected_object()+Package.getMeal());
		  System.out.println(Package.getTotal_price());
		
		PackageForm PackageForm = new PackageForm(Package);
		
	    if(PackageForm.executeChoice()) {
	    	request.setAttribute("optionalMessage", "succesfull");
	    	request.setAttribute("total_price", Package.getTotal_price());
	    RequestDispatcher view = request.getRequestDispatcher("WEB-INF/packagePrice.jsp");
		view.forward(request, response);}
	    else
	    	request.setAttribute("optionalMessage", "A problem occured during the process :("); 
	    
	}

}

// Test 
// Test2
