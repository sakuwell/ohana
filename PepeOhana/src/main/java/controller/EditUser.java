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
 *Filename:EditUser.java
 *
 *Description:
 *	このクラスは、ユーザー情報の編集機能を提供するためのものです。
 *	セッション情報から所得してきた、ユーザー情報を基にデータベースから抽出し、
 *	editUser.jspで表示させるためにリクエストスコープにセットして画面を遷移します。
 *	セッション情報が無い場合は、トップページへ遷移します
 *	
 *
 *Author:大久保
 *Creation Date:2023-09-26
 *
 *Copyright © 2023 KEG Sakura All rights reserved.
 *----------------------------------------------------------------------**/
/**
 * Servlet implementation class EditUser
 */
@WebServlet("/EditUser")
public class EditUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//セッション情報の取得
		HttpSession session           = request.getSession();
		UsersInfoDto userInfoOnSession = (UsersInfoDto)session.getAttribute("LOGIN_INFO");
		//セッション情報の有無によって画面を切り替える
		if (userInfoOnSession != null) {
			//セッション情報が存在:editUser.jspへ画面を切り替える
			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/editUser.jsp");
			dispatch.forward(request, response);
			
		} else {
			//セッション情報が無い:index.jspへ画面を切り替える
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


