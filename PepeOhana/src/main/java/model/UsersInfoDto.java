package model;



/**----------------------------------------------------------------------*
 *■■■UserInfoDtoクラス■■■
 *概要：DTO（「user_info」テーブル）
 *----------------------------------------------------------------------**/
public class UsersInfoDto {

	//----------------------------------------------------------------
	//フィールド
	//----------------------------------------------------------------
//	private int ID;
	private String userId;         //ユーザーID
	private String Name;       //ユーザー名
	private String passWord;       //ユーザーパスワード


	//----------------------------------------------------------------
	//getter/setter
	//----------------------------------------------------------------

	//getter/setter（対象フィールド：userId）
	public String getUserId() { return userId; }
	public void setUserId(String userId) { this.userId = userId; }

	//getter/setter（対象フィールド：userName）
	public String getName() { return Name; }
	public void setName(String userName) { this.Name = userName; }

	//getter/setter（対象フィールド：passWord）
	public String getPassWord() { return passWord; }
	public void setPassWord(String passWord) { this.passWord = passWord; }
	
//	public int getID() {return ID;}
//	public void setID(int iD) {ID = iD;}

}

