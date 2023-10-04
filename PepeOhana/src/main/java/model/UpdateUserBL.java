package model;

/**----------------------------------------------------------------------*
 *Filename:UpdateUserBL.java
 *
 *Description:
 *	このクラスは、ユーザー情報更新機能を提供するためのものです。
 *	対象のユーザー情報を引数として受け取り、
 *	ユーザー情報を更新する処理を行うメソッドへ渡す。
 *	その後処理の成功フラグを返す
 *
 *Author:大久保
 *Creation Date:2023-09-26
 *
 *Copyright © 2023 KEG Sakura All rights reserved.
 *----------------------------------------------------------------------**/

public class UpdateUserBL {
	
	/**----------------------------------------------------------------------*
	 *■executeUpdateメソッド
	 *概要　：対象のユーザーデータを更新する
	 *引数　：対象のユーザーデータ（UsersInfoDto型）
	 *戻り値：DB操作成功フラグ（true:成功/false:失敗）
	 *----------------------------------------------------------------------**/
	public boolean executeUpdate(UsersInfoDto dto) {
		
		boolean   succesUpdate = false ; 
		
		UsersInfoDao dao = new UsersInfoDao();
		succesUpdate = dao.doUpdate(dto);
		
		return succesUpdate;
	}

}
