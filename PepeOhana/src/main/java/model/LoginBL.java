package model;

/**----------------------------------------------------------------------*
 *Filename:LoginBL.java
 *
 *Description:
 *	このクラスは、ログイン機能を提供するためのものです。
 *	対象のユーザーのIDとパスワードを引数として受け取り、
 *	ユーザーデータの抽出処理を行うメソッドへ渡す。
 *	その後、抽出した対象のユーザー情報を返す
 *
 *Author:上月
 *Creation Date:2023-09-04
 *
 *Copyright © 2023 KEG Sakura All rights reserved.
 *----------------------------------------------------------------------**/

/**----------------------------------------------------------------------*
 *■executeSelectUserInfoメソッド
 *概要　：引数のユーザー情報に紐づくユーザーデータを「users_info」テーブルから抽出する
 *引数①：ユーザーID（ユーザー入力）
 *引数②：ユーザーパスワード（ユーザー入力）
 *戻り値：「users_info」テーブルから抽出したユーザーデータ（UsersInfoDto型）
 *----------------------------------------------------------------------**/
public class LoginBL {
	public UsersInfoDto executeSelectUserInfo(String inputUserId, String inputPassWord) {
		UsersInfoDto dto = new UsersInfoDto();  //ユーザーデータ（UserInfoDto型）
		//DAOクラスをインスタンス化＆「user_info」テーブルからユーザーデータを抽出するよう依頼
		UsersInfoDao dao = new UsersInfoDao();
		dto             = dao.doSelect(inputUserId, inputPassWord);
		
		//抽出したユーザーデータを戻す
		return dto;
	}
	
	

}
