package org.imie;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.imie.Jeux;

/**
 * Servlet implementation class TennisScore
 */
@WebServlet("/TennisScore")
public class TennisScore extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	private Jeux jeux;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TennisScore() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.getSession().setAttribute("score", jeux.getScore());
		request.getRequestDispatcher("WEB-INF/jeux.jsp").forward(request,
				response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute("error");
		try {
			if (request.getParameter("joueur1")!=null) {
				jeux.marquerJoueur1();
			}
			if (request.getParameter("joueur2")!=null) {
				jeux.marquerJoueur2();
			}
			if (request.getParameter("save")!=null) {
				jeux.save();;
			}
			if (request.getParameter("reset")!=null) {
				jeux.reset();
			}
			if (request.getParameter("load")!=null) {
				jeux.load();
			}
		} catch (Exception e) {
			request.getSession().setAttribute("error", e.getMessage());
		}
		request.getSession().setAttribute("score", jeux.getScore());
		request.getRequestDispatcher("WEB-INF/jeux.jsp").forward(request,
				response);
	}

}
