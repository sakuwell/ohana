package model;

/**----------------------------------------------------------------------*
 *Filename:SendMessageBL.java
 *
 *Description:
 *	このクラスは、メッセージ機能を提供するためのものです。
 *	対象のメッセージ情報を引数として受け取り、
 *	「messages」テーブルへ登録を行うメソッドへ渡す。
 *	その後処理の成功フラグを返す
 *
 *Author:加藤
 *Creation Date:2023-09-13
 *
 *Copyright © 2023 KEG Sakura All rights reserved.
 *----------------------------------------------------------------------**/
public class SendMessageBL {
	/**----------------------------------------------------------------------*
	 *■executeInsertMessageメソッド
	 *概要　：対象のメッセージデータを登録する
	 *引数　：対象のメッセージデータ（MessagesDto型）
	 *戻り値：DB操作成功フラグ（true:成功/false:失敗）
	 *----------------------------------------------------------------------**/
	public boolean executeInsertMessage(MessagesDto dto) {

		boolean   succesInsert = false ;  //DB操作成功フラグ（true:成功/false:失敗）

		//-------------------------------------------
		//データベースへの接続を実施
		//-------------------------------------------

		//DAOクラスをインスタンス化＆対象のユーザーデータを登録するよう依頼
		MessagesDao dao = new MessagesDao();
		succesInsert = dao.doInsertMessage(dto);

		return succesInsert;
	}
}
