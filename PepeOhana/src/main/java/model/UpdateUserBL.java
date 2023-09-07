package model;

public class UpdateUserBL {
	
	public UsersInfoDto executeSelectSurvey (int id) {
		
		UsersInfoDao dao = new UsersInfoDao();
		UsersInfoDto dto = dao.doSelectOne(id);
		
		return dto;
	}

}
