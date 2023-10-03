package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DelMessageBL;
import model.MessagesDto;

/**----------------------------------------------------------------------*
 *Filename:ExeDelMessage.java
 *
 *Description:
 *	このクラスは、メッセージ情報の削除機能を提供するためのものです。
 *	リクエストパラメータで所得してきたネコメッセージのIDと合致するメッセージの
 *	削除フラグのカラム「DEL」の値を1に更新しマイページ画面へ遷移します。
 *	更新に失敗した場合は失敗時メッセージをリクエストスコープにセットし、マイページ画面へ戻す
 *	
 *
 *Author:櫻井、加藤
 *Creation Date:2023-09-26
 *
 *Copyright © 2023 KEG Sakura All rights reserved.
 *----------------------------------------------------------------------**/
/**
 * Servlet implementation class ExeDelMessage
 */
@WebServlet("/ExeDelMessage")
public class ExeDelMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExeDelMessage() {
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
		
		
		String messageId = request.getParameter("MESSAGEID");//リクエストパラメーター(MESSAGEID)
		//「messages」テーブルから取得情報と合致するメッセージデータを検索し削除フラグを立てる
		MessagesDto dto = new MessagesDto();
		dto.setMessageId(Integer.parseInt(messageId));
		DelMessageBL logic = new DelMessageBL();
		boolean successDelete = logic.executeDeleteMessage(dto);
		
		//該当メッセージの削除成功/失敗に応じて表示する画面を振り分ける
		if(successDelete) {
			//成功時:マイページ表示
			response.sendRedirect("ExeMyPage");
			
		}else {
			//失敗時:リクエストスコープにデータをセット
			request.setAttribute("message", "送信メッセージの削除に失敗しました");
			//マイページを表示
			request.getRequestDispatcher(request.getContextPath()+"/ExeMyPage").forward(request, response);
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
