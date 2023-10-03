package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CatsInfoDto;
import model.SearchCatBL;

/**----------------------------------------------------------------------*
 *Filename:ExeSearchCat.java
 *
 *Description:
 *	このクラスは、ネコ情報の検索機能を提供するためのものです。
 *	リクエストパラメータから性別の値を取得し、
 *	選択した性別と合致するネコ情報を抽出し、リストにセットし、
 *	検索結果表示画面へ遷移する
 *	
 *Author:櫻井
 *Creation Date:2023-09-15
 *
 *Copyright © 2023 KEG Sakura All rights reserved.
 *----------------------------------------------------------------------**/
/**
 * Servlet implementation class SearchCat
 */
@WebServlet("/ExeSearchCat")
public class ExeSearchCat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExeSearchCat() {
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
		
		String gender1=request.getParameter("GENDER1");//リクエストパラメーター(GENDER1)
		String gender2=request.getParameter("GENDER2");//リクエストパラメーター(GENDER2)

		//ユーザーの選択した情報(性別)と合致する「cats_info」テーブルの情報を全て抽出しリストに格納
		List<CatsInfoDto> list = new ArrayList<CatsInfoDto>();
		SearchCatBL logic = new SearchCatBL();
		list = logic.executeSelectCatLists(gender1,gender2);
		
		//抽出した情報をlistにセット
		request.setAttribute("list",list);
		
		//catListへ転送
		RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/catLists.jsp");
		dispatch.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
