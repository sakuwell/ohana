package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.RegistUserBL;
import model.UsersInfoDto;
/**
 * Servlet implementation class ExeRegistUser
 */
@WebServlet("/ExeRegistUser")
public class ExeRegistUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExeRegistUser() {
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
		// TODO Auto-generated method stub
//		doGet(request, response);
		
//		レスポンス（出力データ）の文字コードを設定
			response.setContentType("text/html;charset=UTF-8");
		
//		リクエスト（受信データ）の文字コードを設定
			request.setCharacterEncoding("UTF-8");
			
		
//		リクエストパラメータを取得

			
			String userid			=request.getParameter("USERID");
//			(USERID)
			String username			=request.getParameter("USERNAME");
//			(USERNAME)
			
			String password		=request.getParameter("PASSWORD");
			

			System.out.println(userid);
			System.out.println(username);
			System.out.println(password);
			
			
			//ユーザーデータ（UserInfoDto型）の作成
			UsersInfoDto dto = new UsersInfoDto();
			dto.setUserName( username );
			dto.setUserId( userid );
			dto.setPassWord( password );
			
			System.out.println(password);
			
			
//		データをDBに登録
			RegistUserBL logic = new RegistUserBL();
			boolean succesInsert = logic.exeInsertUserInfo(dto);
			
			//DB操作の成功/失敗に応じて表示させる画面を振り分ける
			
			if (succesInsert) {
//				DBに成功した場合、ログイン後のtop画面(top.jsp)を表示する
				response.sendRedirect("/WEB-INF/view/top.jsp");
			} else {
//				DBに失敗した場合、エラー画面(registusererror.html)を表示する
				response.sendRedirect("htmls/registusererror.html");
			}
		
		
		
		
	}

}
