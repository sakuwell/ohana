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
 *Filename:Message.java
 *
 *Description:
 *	このクラスは、メッセージ作成画面を提供するためのものです。
 *	セッション情報を取得し、ログイン状態であれば、
 *	リクエストパラメータから所得した内容と合致する情報をデータベースから抽出し、
 *	リクエストスコープに抽出した情報をセットし、メッセージ作成画面へ遷移する
 *	ログイン状態でなければ、ログイン画面へ遷移する
 *	
 *Author:加藤
 *Creation Date:2023-09-15
 *
 *Copyright © 2023 KEG Sakura All rights reserved.
 *----------------------------------------------------------------------**/
/**
 * Servlet implementation class Message
 */
@WebServlet("/Message")
public class Message extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Message() {
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
		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		//セッション情報の取得
		HttpSession session           = request.getSession();
		UsersInfoDto userInfoOnSession = (UsersInfoDto)session.getAttribute("LOGIN_INFO");
		
		
		if (userInfoOnSession != null) {
			//ログイン済み:メッセージ作成画面を出力
			String catId = request.getParameter("CATID"); //リクエストパラメータ（CATID)
			String recieverId = request.getParameter("USERID"); //リクエストパラメータ（RECIEVERID)
			String recieverName = request.getParameter("USERNAME"); //リクエストパラメータ（RECIEVERID)
			String catName = request.getParameter("CATNAME"); //リクエストパラメータ（RECIEVERID)
			String message = request.getParameter("MESSAGE"); //リクエストパラメータ（MESSAGE)
			
			//取得してきたリクエストパラメータをセット
			request.setAttribute("CATID",catId );
			request.setAttribute("RECIEVERID",recieverId );
			request.setAttribute("RECIEVERNAME",recieverName );
			request.setAttribute("CATNAME",catName );
			request.setAttribute("MESSAGE",message );
			
			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/message.jsp");
			dispatch.forward(request, response);
		
		
		} else {
			//未ログイン:ログイン画面を出力
			response.sendRedirect(request.getContextPath()+"/Login.jsp");
		}
	}

}
