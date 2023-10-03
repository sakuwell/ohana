package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UsersInfoDto;

/**----------------------------------------------------------------------*
 *Filename:ExeLogout.java
 *
 *Description:
 *	このクラスは、ユーザーのログアウト機能を提供するためのものです。
 *	セッション情報を取得し、セッション情報が存在する場合は
 *	セッション情報を破棄し、ログアウト画面へ遷移する
 *	セッション情報が無い場合は、ログイン画面へ遷移する
 *	
 *Author:上月
 *Creation Date:2023-09-07
 *
 *Copyright © 2023 KEG Sakura All rights reserved.
 *----------------------------------------------------------------------**/
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
