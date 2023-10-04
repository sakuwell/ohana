package model;

import java.util.List;

/**----------------------------------------------------------------------*
 *Filename:MyPageBL.java
 *
 *Description:
 *	このクラスは、マイページ機能を提供するためのものです。
 *	対象のユーザーのIDを引数として受け取り、
 *	マイページ表示に必要な情報の抽出処理を行うメソッドへ渡す。
 *	その後、抽出した対象のそれぞれ情報を返す
 *
 *Author:櫻井
 *Creation Date:2023-09-04
 *
 *Copyright © 2023 KEG Sakura All rights reserved.
 *----------------------------------------------------------------------**/

public class MyPageBL {
	/**----------------------------------------------------------------------*
	 *■executeSelectCatListsメソッド
	 *概要　：引数のユーザーIDに紐づくネコデータを「cats_info」テーブルから抽出する
	 *引数①：ユーザーID（int型）
	 *戻り値：「cats_info」テーブルから抽出したユーザーデータ（MyPageDto型のリスト）
	 *----------------------------------------------------------------------**/
	public List<MyPageDto> executeSelectCatLists(int id){

		MyPageDao dao = new MyPageDao();
		List<MyPageDto> catDtoList = dao.doSelectCatInfo(id);

		return catDtoList;
	}

	/**----------------------------------------------------------------------*
	 *■executeSelectMessageListsメソッド
	 *概要　：引数のユーザーIDに紐づくメッセージデータを「messages_info」テーブルから抽出する
	 *引数①：ユーザーID（int型）
	 *戻り値：「messages_info」テーブルから抽出したユーザーデータ（MyPageDto型のリスト）
	 *----------------------------------------------------------------------**/
	public List<MyPageDto> executeSelectMessageLists(int id){

		MyPageDao dao = new MyPageDao();
		List<MyPageDto> messageDtoList = dao.doSelectMessageInfo(id);

		return messageDtoList;
	}

}
