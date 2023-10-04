package model;


/**----------------------------------------------------------------------*
 *Filename:UsersInfoDto.java
 *
 *Description:
 *	このクラスは、データベースの「users_info」テーブルに対応した
 *	データ受け渡し用のクラスです。
 *	
 *	
 *Author:大久保、上月
 *Creation Date:2023-09-04
 *
 *Copyright © 2023 KEG Sakura All rights reserved.
 *----------------------------------------------------------------------**/
/**----------------------------------------------------------------------*
 *■■■UserInfoDtoクラス■■■
 *概要：DTO（「user_info」テーブル）teisei
 *----------------------------------------------------------------------**/
public class UsersInfoDto {

	//----------------------------------------------------------------
	//フィールド
	//----------------------------------------------------------------
	private int ID;
	private String userId;         //ユーザーID
	private String userName;       //ユーザー名
	private String passWord;       //ユーザーパスワード


	//----------------------------------------------------------------
	//getter/setter
	//----------------------------------------------------------------

	//getter/setter（対象フィールド：userId）
	public String getUserId() { return userId; }
	public void setUserId(String userId) { this.userId = userId; }

	//getter/setter（対象フィールド：userName）
	public String getUserName() { return userName; }
	public void setUserName(String userName) { this.userName = userName; }

	//getter/setter（対象フィールド：passWord）
	public String getPassWord() { return passWord; }
	public void setPassWord(String passWord) { this.passWord = passWord; }

	public int getID() {return ID;}
	public void setID(int iD) {ID = iD;}

}

