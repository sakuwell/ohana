package model;

import java.util.List;

/**----------------------------------------------------------------------*
 *Filename:SearchCatBL.java
 *
 *Description:
 *	このクラスは、ネコ情報検索機能を提供するためのものです。
 *	対象の性別を引数として受け取り、
 *	該当するネコの情報を抽出する処理を行うメソッドへ渡す。
 *	その後抽出したネコの情報のリストを返す
 *
 *Author:加藤
 *Creation Date:2023-09-04
 *
 *Copyright © 2023 KEG Sakura All rights reserved.
 *----------------------------------------------------------------------**/

public class SearchCatBL {
	/**----------------------------------------------------------------------*
	 *■executeSelectCatListsメソッド
	 *概要　：引数のユーザーIDに紐づくネコデータを「cats_info」テーブルから抽出する
	 *引数①：性別:男の子（String型）
	 *引数②：性別:女の子（String型）
	 *戻り値：「cats_info」テーブルから抽出したユーザーデータ（CatsInfoDto型のリスト）
	 *----------------------------------------------------------------------**/
	public List<CatsInfoDto> executeSelectCatLists(String gender1,String gender2){
		CatsInfoDao dao = new CatsInfoDao();
		List<CatsInfoDto> dtoList=dao.doSelectCatLists(gender1,gender2);
		
		return dtoList;
	}
}
