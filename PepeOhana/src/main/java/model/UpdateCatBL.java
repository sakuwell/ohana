package model;

public class UpdateCatBL {
	
	public boolean exeUpdateCat(CatsInfoDto dto) {
		
		boolean   succesUpdate = false ; 
		
		System.out.println("BBBB");
		
		CatsInfoDao dao = new CatsInfoDao();
		succesUpdate = dao.doUpdateCat(dto);
		
		System.out.println("AAAA");
		
		return succesUpdate;
	}
}


