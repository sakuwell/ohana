package model;

public class SelectUserBL {
	
	public UsersInfoDto executeSelectUserInfo(int id)  {
		
		UsersInfoDao dao = new UsersInfoDao();
		UsersInfoDto dto = dao.doSelectOne(id);
		
		return dto;
	}

}
