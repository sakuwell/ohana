package model;

public class UpdateCatBL {
	
	public boolean exeUpdateCat(CatsInfoDto dto) {
		
		boolean   succesUpdate = false ; 
		
		CatsInfoDao dao = new CatsInfoDao();
		succesUpdate = dao.doUpdateCat(dto);
		
		return succesUpdate;
	}
}
