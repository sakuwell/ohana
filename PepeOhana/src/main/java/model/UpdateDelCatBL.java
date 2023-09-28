package model;

public class UpdateDelCatBL {
	
	public boolean exeUpdateDelCat(int catId) {
		
		boolean   succesUpdate = false ; 
		
		System.out.println("BBBB");
		
		CatsInfoDao dao = new CatsInfoDao();
		succesUpdate = dao.doUpdateDelCat(catId);
		
		System.out.println("AAAA");
		
		return succesUpdate;

	}
}

