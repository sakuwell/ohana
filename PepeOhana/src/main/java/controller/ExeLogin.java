package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.LoginBL;
import model.UsersInfoDto;

/**
 * Servlet implementation class ExeLogin
 */
@WebServlet("/ExeLogin")
public class ExeLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExeLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8"); 
		request.setCharacterEncoding("UTF-8"); 
		
		HttpSession session           = request.getSession();
		UsersInfoDto userInfoOnSession = (UsersInfoDto)session.getAttribute("USERINFO");
		
		if (userInfoOnSession != null) {
			//ログイン済：トップページ
			response.sendRedirect("Top.jsp");
		} else {
			
			String userId   = request.getParameter("USERID");      
			String passWord = request.getParameter("PASS");
			
			LoginBL logic = new LoginBL ();
			UsersInfoDto   dto   = logic.executeSelectUserInfo(userId, passWord);
			
			if (dto.getUserId() != null) {
				

				//DBから抽出したユーザデータをセッションにセット
				session.setAttribute("USERINFO", dto);

				//トップページ画面へ転送
				response.sendRedirect("top.jsp");

			} else {
				//ユーザーデータの抽出に失敗：ログインNGとしてログイン画面へ転送
				response.sendRedirect("login.jsp");

			}
		}
	}
}
		

