package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CatsInfoDto;
import model.EditCatOneBL;

/**----------------------------------------------------------------------*
 *Filename:EditCat.java
 *
 *Description:
 *	このクラスは、ネコ情報の編集機能を提供するためのものです。
 *	リクエストパラメータで所得してきたネコのIDと合致するネコの情報をデータベースから抽出し、
 *	editCat.jspで表示させるためにリクエストスコープにセットして画面を遷移します。
 *	何らかの原因で、ネコの情報の抽出に失敗した場合は、
 *	失敗時メッセージをリクエストスコープにセットし、マイページへ遷移します
 *	
 *
 *Author:大久保
 *Creation Date:2023-09-26
 *
 *Copyright © 2023 KEG Sakura All rights reserved.
 *----------------------------------------------------------------------**/

/**
 * Servlet implementation class EditCat
 */
@WebServlet("/EditCat")
public class EditCat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditCat() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//レスポンス(送信データ)の文字コードを設定
		response.setContentType("text/html;charset=UTF-8");
	
		//リクエスト（受信データ）の文字コードを設定
		request.setCharacterEncoding("UTF-8");
		
		
		String catIdStr = request.getParameter("ID");//リクエストパラメータ(ID)
		//リクエストパラメータで取得してきた内容をint型に変換
		int catId = Integer.parseInt(catIdStr);
		
		//「cats_info」テーブルから該当するネコ情報を抽出
		EditCatOneBL logic = new EditCatOneBL();		
		CatsInfoDto editCat = logic.exeSelectOneCatInfo(catId);
		//ネコ情報の抽出成功/失敗に応じて画面を切り替える
		if (editCat != null) {
			//ネコ情報抽出成功:抽出したネコ情報をセット
		    request.setAttribute("cat", editCat);
		    //editCat.jspへ画面を切り替える
		    RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/editCat.jsp");
		    dispatch.forward(request, response);
		} else {
			//失敗時:エラーメッセージをリクエストスコープにセット
			request.setAttribute("message", "ねこの情報の所得に失敗しました");
			//マイページへ画面を切り替える
			RequestDispatcher dispatch = request.getRequestDispatcher("/ExeMyPage");
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
