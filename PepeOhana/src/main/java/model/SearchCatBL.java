package model;

import java.util.List;

public class SearchCatBL {
	public List<CatsInfoDto> executeSelectCatLists(String gender1,String gender2){
		CatsInfoDao dao = new CatsInfoDao();
		List<CatsInfoDto> dtoList=dao.doSelectCatLists(gender1,gender2);
		
		return dtoList;
	}
}
