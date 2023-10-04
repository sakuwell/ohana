package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UsersInfoDto;
/**----------------------------------------------------------------------*
 *Filename:RegistCat.java
 *
 *Description:
 *	このクラスは、ネコ情報登録機能を提供するためのものです。
 *	セッション情報を取得し、ログイン状態であれば
 *	ネコ情報登録画面へ遷移する
 *	ログイン状態でなければ、トップ画面へ遷移する
 *	
 *Author:大久保
 *Creation Date:2023-09-11
 *
 *Copyright © 2023 KEG Sakura All rights reserved.
 *----------------------------------------------------------------------**/
/**
 * Servlet implementation class RegistCat
 */
@WebServlet("/RegistCat")
public class RegistCat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistCat() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//レスポンス（出力データ）の文字コードを設定
		response.setContentType("text/html;charset=UTF-8");
		//リクエスト（受信データ）の文字コードを設定
		request.setCharacterEncoding("UTF-8"); 
		
		//セッション情報の取得
		HttpSession session           = request.getSession();
		UsersInfoDto userInfoOnSession = (UsersInfoDto)session.getAttribute("LOGIN_INFO");
		
		
		if (userInfoOnSession != null) {
			//セッション情報の取得成功時:registCat.jspへ
			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/registCat.jsp");
			dispatch.forward(request, response);
		} else {
			//失敗時:トップへ戻る
			response.sendRedirect("index.jsp");
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
