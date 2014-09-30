package org.imie.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.imie.DTO.PersonneDTO;
import org.imie.factory.IFactory;
import org.imie.launcher.Factory1;
import org.imie.service.IEcoleService;

/**
 * Servlet implementation class ServletListPersonne
 */
@WebServlet("/ServletListPersonne")
public class ServletListPersonne extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletListPersonne() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer= response.getWriter();
		IFactory fact = new Factory1();
		IEcoleService service=  fact.createEcoleService();
		List<PersonneDTO> list =  service.getAllPersonne();
		
		writer.print("HELLO");
		
		for (PersonneDTO personneDTO : list) {
			writer.println(personneDTO.getNom());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
