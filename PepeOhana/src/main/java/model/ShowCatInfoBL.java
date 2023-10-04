package model;

/**----------------------------------------------------------------------*
 *Filename:ShowCatInfoBL.java
 *
 *Description:
 *	このクラスは、ネコ詳細機能を提供するためのものです。
 *	対象のネコIDを引数として受け取り、
 *	「catts_info」テーブルから該当するネコ情報の抽出を行うメソッドへ渡す。
 *	その後抽出したネコの情報を返す
 *
 *Author:加藤
 *Creation Date:2023-09-11
 *
 *Copyright © 2023 KEG Sakura All rights reserved.
 *----------------------------------------------------------------------**/

/**----------------------------------------------------------------------*
 *■executeSelectShowCatInfoメソッド
 *概要　：引数のユーザーIDに紐づくネコデータと飼い主データを「cats_info」テーブルと「users_info」テーブルから抽出する
 *引数　：ネコID
 *戻り値 :「cats_info」テーブルと「users_info」テーブルから抽出したデータ(CatsInfoDto型)
 *----------------------------------------------------------------------**/
public class ShowCatInfoBL {
	public CatsInfoDto executeSelectShowCatInfo(int catId) {
		CatsInfoDao dao = new CatsInfoDao();
		CatsInfoDto dto = dao.doSelectCatInfo(catId);
		return dto;
	}
}
