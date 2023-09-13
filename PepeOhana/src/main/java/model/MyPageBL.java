package model;

import java.util.List;

public class MyPageBL {
	public List<MyPageDto> executeSelectMyPageLists(int id){
		
		MyPageDao dao = new MyPageDao();
		List<MyPageDto> dtoList=dao.doSelectMyInfo(id);
		
		return dtoList;
	}

	
	
}
