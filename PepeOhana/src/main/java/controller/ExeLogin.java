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

/**----------------------------------------------------------------------*
 *Filename:ExeLogin.java
 *
 *Description:
 *	このクラスは、ユーザーのログイン機能を提供するためのものです。
 *	セッション情報を取得し、セッション情報が存在する場合はトップページへ遷移する
 *	セッション情報が空の場合は、リクエストパラメータで入力された内容を所得し、
 *	データベースと照合し、合致するユーザーデータがあれば抽出、
 *	セッションにセット後、トップページへ遷移する
 *	ユーザー情報の抽出に失敗した場合はログイン画面へ遷移する
 *	
 *
 *Author:上月
 *Creation Date:2023-09-07
 *
 *Copyright © 2023 KEG Sakura All rights reserved.
 *----------------------------------------------------------------------**/
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
		//レスポンス(送信データ)の文字コードを設定
		response.setContentType("text/html;charset=UTF-8");
		//リクエスト（受信データ）の文字コードを設定
		request.setCharacterEncoding("UTF-8"); 
		
		//セッション情報の取得
		HttpSession session           = request.getSession();
		UsersInfoDto userInfoOnSession = (UsersInfoDto)session.getAttribute("LOGIN_INFO");
		
		if (userInfoOnSession != null) {
			//ログイン済：トップページ
			response.sendRedirect("index.jsp");
			
		} else {
			//ログイン状態で無い場合
			
			String userId   = request.getParameter("USERID");//リクエストパラメータ(USERID)
			String passWord = request.getParameter("PASS");//リクエストパラメータ(PASS)
			
			//「users_info」テーブルからユーザー入力値と合致するユーザーデータ（UsersInfoDto型）を抽出
			// ※合致するデータがなかった場合、各フィールドがnullのDTOを得る
			LoginBL logic = new LoginBL ();
			UsersInfoDto   dto   = logic.executeSelectUserInfo(userId, passWord);
			
			//ユーザーデータの抽出成功/失敗に応じて表示させる画面を振り分ける
			if (dto.getUserName() != null) {
				//ユーザーデータの抽出成功
				//DBから抽出したユーザデータをセッションにセット
				session.setAttribute("LOGIN_INFO", dto);
				//トップページ画面へ転送
		        request.getRequestDispatcher("index.jsp").forward(request, response);

			} else {
				//ユーザーデータの抽出失敗
				// リクエストスコープにデータを設定
				request.setAttribute("message", "ログインできませんでした。ユーザーIDかパスワードが間違っています。");
				//トップページ画面へ転送
		        request.getRequestDispatcher("Login.jsp").forward(request, response);
			}
		}
	}
}
		

