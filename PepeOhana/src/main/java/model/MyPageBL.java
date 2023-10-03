package model;

import java.util.List;


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
