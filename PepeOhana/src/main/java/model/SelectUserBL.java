package model;

public class SelectUserBL {
	
	public UsersInfoDto executeSelectUserInfo(String inputUserId, String inputPassWord)  {
		
		UsersInfoDao dao = new UsersInfoDao();
		UsersInfoDto dto = dao.doSelect(inputUserId,inputPassWord );
		
		return dto;
	}

}
