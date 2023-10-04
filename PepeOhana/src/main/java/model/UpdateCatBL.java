package model;

/**----------------------------------------------------------------------*
 *Filename:UpdateCatBL.java
 *
 *Description:
 *	このクラスは、ネコ情報更新機能を提供するためのものです。
 *	対象のネコ情報を引数として受け取り、
 *	対象のネコの情報を更新する処理を行うメソッドへ渡す。
 *	その後処理の成功フラグを返す
 *
 *Author:大久保
 *Creation Date:2023-09-26
 *
 *Copyright © 2023 KEG Sakura All rights reserved.
 *----------------------------------------------------------------------**/

public class UpdateCatBL {
	/**----------------------------------------------------------------------*
	 *■exeUpdateCatメソッド
	 *概要　：対象のネコデータを更新する
	 *引数　：対象のネコデータ（CatsInfoDto型）
	 *戻り値：DB操作成功フラグ（true:成功/false:失敗）
	 *----------------------------------------------------------------------**/
	public boolean exeUpdateCat(CatsInfoDto dto) {
		
		boolean   succesUpdate = false ; 
		
		CatsInfoDao dao = new CatsInfoDao();
		succesUpdate = dao.doUpdateCat(dto);
		
		return succesUpdate;
	}
}


