package model;

/**----------------------------------------------------------------------*
 *Filename:EditCatOneBL.java
 *
 *Description:
 *	このクラスは、ネコ情報編集機能を提供するためのものです。
 *	対象のネコIDを引数として受け取り、
 *	削除する処理を行うメソッドへ渡す。
 *	その後対象のIDに基づくネコ情報を返す
 *
 *Author:大久保
 *Creation Date:2023-09-26
 *
 *Copyright © 2023 KEG Sakura All rights reserved.
 *----------------------------------------------------------------------**/

public class EditCatOneBL {
	
	/**----------------------------------------------------------------------*
	 *■exeSelectOneCatInfoメソッド
	 *概要　：引数のcatId情報に紐づくネコちゃん情報を「cats_info」テーブルから抽出する
	 *引数①：ユーザーID
	 *戻り値：「cats_info」テーブルから抽出したネコちゃん（CatsInfoDto型）
	 *----------------------------------------------------------------------**/
	
	public CatsInfoDto exeSelectOneCatInfo(int catId) {
		CatsInfoDao dao = new CatsInfoDao();
		CatsInfoDto dto = dao.doSelectCatOne(catId);
		
		// 抽出したネコちゃん情報を戻す
		return dto;
	}

}
