package model;

import java.util.List;

public class MyPageBL {
	
	public List<MyPageDto> executeSelectCatLists(int id){
		
		MyPageDao dao = new MyPageDao();
		List<MyPageDto> catDtoList = dao.doSelectCatInfo(id);
		
		return catDtoList;
	}
	
	public List<MyPageDto> executeSelectMessageLists(int id){
		
		MyPageDao dao = new MyPageDao();
		List<MyPageDto> messageDtoList = dao.doSelectMessageInfo(id);
	
		return messageDtoList;
	}
	
}
