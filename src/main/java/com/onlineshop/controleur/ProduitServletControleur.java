package com.onlineshop.controleur;

import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

import com.onlineshop.modele.Produit;
import com.onlineshop.service.ProduitDbService;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class ProduitServletControleur
 */
@WebServlet("/ProduitServletControleur")
public class ProduitServletControleur extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Resource(name="jdbc/onlineshop_db")
	private DataSource datasource;
	
	private ProduitDbService ProduitDbService;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProduitServletControleur() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() throws ServletException {
    	super.init();
    	
    	try {
    		ProduitDbService = new ProduitDbService(datasource);
    		
    	}catch(Exception ex) {
    		throw new ServletException(ex);
    	}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String action = request.getParameter("action");
			
			if(action == null) {
				action = "Liste";
			}
			
			switch(action) {
			
			case "LISTE":
				listeProduits(request, response);
				break;
				
			default:
				listeProduits(request, response);
			}
			
		}catch(Exception ex) {
			throw new ServletException(ex);
		}
	}
	
	// Methode listeProduits()
	private void listeProduits(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<Produit> produits = ProduitDbService.getProduits();
		
		request.setAttribute("PRODUIT_LIST", produits);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/liste-produits.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
