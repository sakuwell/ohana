package controller;

import java.io.IOException;


import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.SelectUserBL;
import model.UpdateUserBL
import model.UsersInfoDto;

/**
 * Servlet implementation class EditUser
 */
@WebServlet("/EditUser")
public class EditUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session           = request.getSession();
		UsersInfoDto userInfoOnSession = (UsersInfoDto)session.getAttribute("USERINFO");
		
		if (userInfoOnSession == null) {
			//未ログインの場合ログイン画面へ
			response.sendRedirect("login.jsp");
		}else{
			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/editUser.jsp");
			dispatch.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		
		request.setCharacterEncoding("UTF-8"); 
		
		HttpSession session = request.getSession();
		UsersInfoDto userInfoOnSession = (UsersInfoDto) session.getAttribute("USERINFO");
		
		//リクエストパラメータを取得　ユーザー名を取得userName
		String userId              = request.getParameter("USERID");
		String userName              = request.getParameter("USERNAME");
		String passWord				= request.getParameter("PassWord");
		
		//ユーザー情報の作成
		UsersInfoDto dto = new UsersInfoDto();
		dto.setUserId(userId);
		dto.setUserName( userName );
		dto.setPassWord( passWord );
		
		
		//データをDBに登録
		UpdateUserBL logic = new UpdateUserBL();
		boolean succesUpdate = logic.executeUpdateUserBL(dto);
		
		if (succesUpdate) {
			
			response.sendRedirect("/WEB-INF/view/mypage.jsp");
		} else {
			response.sendRedirect("html/error.html");
			
		}
		
	}

}
