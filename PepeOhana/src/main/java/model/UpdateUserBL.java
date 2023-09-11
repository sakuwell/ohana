package model;

public class UpdateUserBL {
	
	
	public boolean executeUpdate(UsersInfoDto dto) {
		
		boolean   succesUpdate = false ; 
		
		UsersInfoDao dao = new UsersInfoDao();
		succesUpdate = dao.doUpdate(dto);
		
		return succesUpdate;
	}

}
