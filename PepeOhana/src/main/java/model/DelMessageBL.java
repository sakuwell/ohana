package model;

/**----------------------------------------------------------------------*
 *Filename:DelMessageBL.java
 *
 *Description:
 *	このクラスは、メッセージ削除機能を提供するためのものです。
 *	対象のメッセージデータを引数として受け取り、
 *	削除する処理を行うメソッドへ渡す。
 *	その後処理の成功フラグを返す
 *
 *Author:加藤
 *Creation Date:2023-09-12
 *
 *Copyright © 2023 KEG Sakura All rights reserved.
 *----------------------------------------------------------------------**/

/**----------------------------------------------------------------------*
 *■executeDeleteMessageメソッド
 *概要　：対象のメッセージデータを削除する
 *引数　：対象のメッセージデータ（MessagesDto型）
 *戻り値：DB操作成功フラグ（true:成功/false:失敗）
 *----------------------------------------------------------------------**/
public class DelMessageBL {
	public boolean executeDeleteMessage(MessagesDto dto) {
		boolean successDelete=false;
		MessagesDao dao = new MessagesDao();
		successDelete=dao.doDeleteMessage(dto.getMessageId());
		
		return successDelete;
	}
}
