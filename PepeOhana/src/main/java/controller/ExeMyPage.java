package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MyPageBL;
import model.MyPageDto;
import model.UsersInfoDto;

/**
 * Servlet implementation class ExeMyPage
 */
@WebServlet("/ExeMyPage")
public class ExeMyPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExeMyPage() {
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
		
		HttpSession session           = request.getSession();
		UsersInfoDto userInfoOnSession = (UsersInfoDto)session.getAttribute("LOGIN_INFO");
		
		if (userInfoOnSession == null) {
//			未ログインならトップページへ
			response.sendRedirect("index.jsp");
			
		} else {
//			セッション情報から変数に代入
			int id = userInfoOnSession.getID();
			
			MyPageBL logic = new MyPageBL();
			List<MyPageDto> catList  = logic.executeSelectCatLists(id);
			List<MyPageDto> messageList = logic.executeSelectMessageLists(id);
			request.setAttribute("CATLIST" , catList);
			request.setAttribute("MESSAGELIST" , messageList);
		
			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/mypage.jsp");
			dispatch.forward(request, response);
			
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
