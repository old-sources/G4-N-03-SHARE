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
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import model.Todo;

/**
 * Servlet implementation class PersonnesServlet
 */
@WebServlet("/TodoServlet")
public class TodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	EntityManager entityManager;

	@Resource
	private UserTransaction tx;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TodoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Todo> todos = entityManager.createNamedQuery("Todo.findAll")
				.getResultList();
		request.setAttribute("todos", todos);
		request.getRequestDispatcher("WEB-INF/todo.jsp").forward(request,
				response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if (request.getParameter("creer") != null) {
			String description = request.getParameter("description");
			Todo newTodo = new Todo();
			newTodo.setDescription(description);
			try {
				tx.begin();
				entityManager.persist(newTodo);
				tx.commit();
			} catch (Exception e) {
				try {
					tx.rollback();
				} catch (IllegalStateException | SecurityException
						| SystemException e1) {
					e1.printStackTrace();
				}
			}
		} else if (request.getParameter("supprimer") != null) {
			try {
				tx.begin();
				String idString = request.getParameter("id");
				Integer id = Integer.valueOf(idString);
				Todo todoToDelete = new Todo();
				todoToDelete.setId(id);
				todoToDelete = entityManager.merge(todoToDelete);
				entityManager.remove(todoToDelete);
				tx.commit();
			} catch (Exception e) {
				try {
					tx.rollback();
				} catch (IllegalStateException | SecurityException
						| SystemException e1) {
					e1.printStackTrace();
				}
			}
		}
		List<Todo> todos = entityManager.createNamedQuery("Todo.findAll")
				.getResultList();
		request.setAttribute("todos", todos);
		request.getRequestDispatcher("WEB-INF/todo.jsp").forward(request,
				response);

	}

}
