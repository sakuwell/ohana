package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CatsInfoDto;
import model.ShowCatInfoBL;

/**
 * Servlet implementation class ShowCatInfo
 */
@WebServlet("/ExeShowCatInfo")
public class ExeShowCatInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExeShowCatInfo() {
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
		String catId = request.getParameter("CATID"); //リクエストパラメータ（CAT_ID)
//		System.out.println(catId);

		ShowCatInfoBL logic = new ShowCatInfoBL();
		CatsInfoDto ShowCatInfo = logic.executeSelectShowCatInfo(Integer.parseInt(catId));
		
		if (ShowCatInfo.getCatName() != null) {

			request.setAttribute("showCatInfo",ShowCatInfo);
			
			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/showCatInfo.jsp");
			dispatch.forward(request, response);

		} else {			
			// リクエストスコープにデータを設定
			request.setAttribute("message", "ご指定のIDのねこが見つかりませんでした");
			
			RequestDispatcher dispatch = request.getRequestDispatcher("index.jsp");
			dispatch.forward(request, response);
			//トップページ画面へ転送
//	        request.getRequestDispatcher("Login.jsp").forward(request, response);
		}
		

		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
