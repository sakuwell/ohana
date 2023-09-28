package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CatsInfoDto;
import model.EditCatOneBL;
import model.UsersInfoDto;

/**
 * Servlet implementation class EditCat
 */
@WebServlet("/EditCat")
public class EditCat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditCat() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	
		
		System.out.println("OK");

		response.setContentType("text/html;charset=UTF-8");
	
//	リクエスト（受信データ）の文字コードを設定
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session           = request.getSession();
		UsersInfoDto userInfoOnSession = (UsersInfoDto)session.getAttribute("LOGIN_INFO");
		

//		int userId = userInfoOnSession.getID(); 
//		
//		System.out.println(userId);
		
//		int catId =request.getParameter("CATID");
//		String catId = request.getParameter("ID"); 
//		int catId = Integer.parseInt(catId);
		
		String catIdStr = request.getParameter("ID");
		int catId = Integer.parseInt(catIdStr);
		
		
		System.out.println(catId);
		
		EditCatOneBL logic = new EditCatOneBL();		
		CatsInfoDto editCat = logic.exeSelectOneCatInfo(catId);
		
		if (editCat != null) {
		    request.setAttribute("cat", editCat);
		    RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/editCat.jsp");
		    dispatch.forward(request, response);
		} else {
			System.out.println("ネコ情報はありません");
		    // ネコちゃん情報が見つからない場合の処理を追加
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
