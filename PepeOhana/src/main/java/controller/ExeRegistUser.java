package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.RegistUserBL;
import model.UsersInfoDto;

/**----------------------------------------------------------------------*
 *Filename:ExeRegistUser.java
 *
 *Description:
 *	このクラスは、ユーザー情報の新規登録機能を提供するためのものです。
 *	入力された内容をリクエストパラメータで取得し、
 *	データをデータベースに登録し、ログイン画面へ遷移する
 *	登録が失敗した場合は、失敗時メッセージをリクエストスコープにセットし、登録画面へ戻る
 *	
 *Author:大久保
 *Creation Date:2023-09-19
 *
 *Copyright © 2023 KEG Sakura All rights reserved.
 *----------------------------------------------------------------------**/
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

			
		String userId			=request.getParameter("userId");
//		(USERID)
		String userName			=request.getParameter("userName");
//		(USERNAME)
			
		String userPass		=request.getParameter("userPass");
						
			
		//ユーザーデータ（UserInfoDto型）の作成
		UsersInfoDto dto = new UsersInfoDto();
		dto.setUserId( userId );
		dto.setUserName( userName );
		dto.setPassWord( userPass );
			
//		データをDBに登録
		RegistUserBL logic = new RegistUserBL();
		boolean succesInsert = logic.exeInsertUserInfo(dto);
			
		//DB操作の成功/失敗に応じて表示させる画面を振り分ける
		
		if (succesInsert) {
//			DBに成功した場合、ログイン後のログイン画面(Login.jsp)を表示する
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		} else {
//		DBに失敗した場合、失敗時メッセージをリクエストスコープにセットし、ユーザー情報新規登録画面へ戻す
			request.setAttribute("message", "登録済みユーザーIDです。別のユーザーIDで登録し直してください。");
	        request.getRequestDispatcher("registUser.jsp").forward(request, response);
		}
	}

}
