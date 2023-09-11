package model;

import java.util.List;

public class SearchCatBL {
	public List<CatsInfoDto> executeSelectCatLists(String gender){
		CatsInfoDao dao = new CatsInfoDao();
		List<CatsInfoDto> dtoList=dao.doSelectCatLists(gender);
		
		return dtoList;
	}
}
