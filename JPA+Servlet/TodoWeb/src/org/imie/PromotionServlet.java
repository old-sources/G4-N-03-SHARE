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
@WebServlet("/PromotionServlet")
public class PromotionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager entityManager;

	@Resource
	private UserTransaction tx;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PromotionServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Promotion result = null;
		String promotionIdString = request.getParameter("promotionId");
		Integer promotionId = Integer.valueOf(promotionIdString);
		try {
			tx.begin();
			result = entityManager.find(Promotion.class, promotionId);

			result.getPersonnes().size();

			tx.commit();

		} catch (NotSupportedException | SystemException | SecurityException
				| IllegalStateException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("promotion", result);
		request.getRequestDispatcher("WEB-INF/promotion.jsp").forward(request,
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
