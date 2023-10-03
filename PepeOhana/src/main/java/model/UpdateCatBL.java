package model;

public class UpdateCatBL {
	/**----------------------------------------------------------------------*
	 *■exeUpdateCatメソッド
	 *概要　：対象のネコデータを更新する
	 *引数　：対象のネコデータ（CatsInfoDto型）
	 *戻り値：DB操作成功フラグ（true:成功/false:失敗）
	 *----------------------------------------------------------------------**/
	public boolean exeUpdateCat(CatsInfoDto dto) {
		
		boolean   succesUpdate = false ; 
		
		CatsInfoDao dao = new CatsInfoDao();
		succesUpdate = dao.doUpdateCat(dto);
		
		return succesUpdate;
	}
}


