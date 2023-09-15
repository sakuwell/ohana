package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UsersInfoDto;

/**
 * Servlet implementation class ExeLogout
 */
@WebServlet("/ExeLogout")
public class ExeLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExeLogout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//レスポンス（出力データ）の文字コードを設定
		response.setContentType("text/html;charset=UTF-8");
		
		//セッションからユーザーデータを取得
		HttpSession session           = request.getSession();
		UsersInfoDto userInfoOnSession = (UsersInfoDto)session.getAttribute("LOGIN_INFO");
		
		if (userInfoOnSession != null) {
			
			//ログアウトに伴いセッション情報を破棄
			session.invalidate();
			response.sendRedirect("html/logout.html");
			

			
		} else {
			//未ログイン：ログイン画面へ転送
			response.sendRedirect("Login");
			
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
