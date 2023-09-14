package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CatsInfoDto;
import model.SearchCatBL;

/**
 * Servlet implementation class SearchCat
 */
@WebServlet("/ExeSearchCat")
public class ExeSearchCat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExeSearchCat() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html;charset=UTF-8");
		
//		String[] genders=request.getParameterValues("GENDER");
		String gender1=request.getParameter("GENDER1");
		String gender2=request.getParameter("GENDER2");

		List<CatsInfoDto> list = new ArrayList<CatsInfoDto>();
		SearchCatBL logic = new SearchCatBL();
		list = logic.executeSelectCatLists(gender1,gender2);
		request.setAttribute("list",list);
		
		RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/catLists.jsp");
		dispatch.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
