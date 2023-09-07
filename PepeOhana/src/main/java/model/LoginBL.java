package model;

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
