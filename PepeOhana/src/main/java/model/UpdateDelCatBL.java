package model;

/**----------------------------------------------------------------------*
 *Filename:UpdateDelCatBL.java
 *
 *Description:
 *	このクラスは、ネコ情報削除機能を提供するためのものです。
 *	対象のネコIDを引数として受け取り、
 *	対象のネコの情報を削除する処理を行うメソッドへ渡す。
 *	その後処理の成功フラグを返す
 *
 *Author:大久保
 *Creation Date:2023-09-26
 *
 *Copyright © 2023 KEG Sakura All rights reserved.
 *----------------------------------------------------------------------**/
public class UpdateDelCatBL {
	/**----------------------------------------------------------------------*
	 *■exeUpdateDelCatメソッド
	 *概要　：対象のネコデータを削除する
	 *引数　：対象のネコid（int型）
	 *戻り値：DB操作成功フラグ（true:成功/false:失敗）
	 *----------------------------------------------------------------------**/
	public boolean exeUpdateDelCat(int catId) {
		
		boolean   succesUpdate = false ; 
		
		CatsInfoDao dao = new CatsInfoDao();
		succesUpdate = dao.doUpdateDelCat(catId);
		
		return succesUpdate;

	}
}

