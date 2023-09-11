package model;

public class UpdateUserBL {
	
	
	public boolean executeUpdatetSurvey(UsersInfoDto dto) {
		
		boolean   succesUpdate = false ; 
		
		UsersInfoDao dao = new UsersInfoDao();
		succesUpdate  = dao.doUpdate(dto);
		
		return succesUpdate;
	}

}
