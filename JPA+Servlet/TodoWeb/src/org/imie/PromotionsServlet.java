package org.imie;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import model.Promotion;

/**
 * Servlet implementation class PromotionServlet
 */
@WebServlet("/PromotionsServlet")
public class PromotionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager entityManager;

	@Resource
	private UserTransaction tx;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PromotionsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Promotion> resultList = null;
		try {
			tx.begin();
			resultList = entityManager.createNamedQuery("Promotion.findAll")
					.getResultList();

			for (Promotion promotion : resultList) {
				promotion.getPersonnes().size();
			}
			tx.commit();

		} catch (NotSupportedException | SystemException | SecurityException
				| IllegalStateException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("promotions", resultList);
		request.getRequestDispatcher("WEB-INF/promotions.jsp").forward(request,
				response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
