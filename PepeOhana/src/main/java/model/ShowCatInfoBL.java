package model;

public class ShowCatInfoBL {
	public CatsInfoDto executeSelectShowCatInfo(int catId) {
		CatsInfoDao dao = new CatsInfoDao();
		CatsInfoDto dto = dao.doSelectCatInfo(catId);
		return dto;
	}
}
