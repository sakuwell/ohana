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

/**----------------------------------------------------------------------*
 *Filename:ExeMyPage.java
 *
 *Description:
 *	このクラスは、ユーザーのログアウト機能を提供するためのものです。
 *	セッション情報を取得し、セッション情報が存在する場合は
 *	セッション情報と合致する情報を抽出し、リクエストスコープにセットし、
 *	マイページ画面へ遷移する
 *	セッション情報が無い場合は、ログイン画面へ遷移する
 *	
 *Author:櫻井
 *Creation Date:2023-09-15
 *
 *Copyright © 2023 KEG Sakura All rights reserved.
 *----------------------------------------------------------------------**/
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
//		レスポンス（出力データ）の文字コードを設定
		response.setContentType("text/html;charset=UTF-8");
		
		//セッション情報の取得
		HttpSession session           = request.getSession();
		UsersInfoDto userInfoOnSession = (UsersInfoDto)session.getAttribute("LOGIN_INFO");
		
		if (userInfoOnSession == null) {
//			未ログインならトップページへ
			response.sendRedirect("index.jsp");
			
		} else {
//			セッション情報から変数に代入
			int id = userInfoOnSession.getID();
			
			//変数と合致する情報を抽出
			MyPageBL logic = new MyPageBL();
			List<MyPageDto> catList  = logic.executeSelectCatLists(id);
			List<MyPageDto> messageList = logic.executeSelectMessageLists(id);
			
			//抽出した情報をリクエストスコープにセット
			request.setAttribute("CATLIST" , catList);
			request.setAttribute("MESSAGELIST" , messageList);
			
			//マイページへ画面を切り替え
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
