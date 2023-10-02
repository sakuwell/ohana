package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.LoginBL;
import model.UpdateUserBL;
import model.UsersInfoDto;

/**
 * Servlet implementation class ExeEditUser
 */
@WebServlet("/ExeEditUser")
public class ExeEditUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExeEditUser() {
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
		
		
		//リクエストパラメータを取得　ユーザー名を取得userName
		int ID              = Integer.parseInt(request.getParameter("ID"));
		String userId              = request.getParameter("userId");
		String userName              = request.getParameter("userName");
		String passWord				= request.getParameter("userPass");
		
		//ユーザー情報の作成
		UsersInfoDto dto = new UsersInfoDto();
		dto.setID(ID);
		dto.setUserId(userId);
		dto.setUserName( userName );
		dto.setPassWord( passWord );
		
		
		//データをDBに登録
		UpdateUserBL logic = new UpdateUserBL();
		boolean succesUpdate = logic.executeUpdate(dto);
		
		if (succesUpdate) {
			
//			ユーザー編集に伴い元のセッション情報を破棄
			HttpSession session = request.getSession();
//			UsersInfoDto userInfoOnSession = (UsersInfoDto) session.getAttribute("LOGIN_INFO");
			session.invalidate();
			
			
//			新しいユーザー情報をセッションにセットし直す
			LoginBL logic2 = new LoginBL ();
			UsersInfoDto   dto2   = logic2.executeSelectUserInfo(userId, passWord);
			
			if (dto2.getUserName() != null) {
				
				//DBから抽出したユーザデータをセッションにセット
				session = request.getSession();
				session.setAttribute("LOGIN_INFO", dto2);
				//トップページ画面へ転送
				response.sendRedirect("ExeMyPage");

			} else {
				//ユーザーデータの抽出に失敗：ログインNGとしてログイン画面へエラーメッセージを渡す
				response.sendRedirect("Login.jsp");
			}
			
		} else {
			
			request.setAttribute("message", "登録済みユーザーIDです。別のユーザーIDで更新し直してください。");
			//トップページ画面へ転送
	        request.getRequestDispatcher("/EditUser").forward(request, response);
			
		}
	}

}
