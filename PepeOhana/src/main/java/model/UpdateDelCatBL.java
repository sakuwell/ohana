package model;

public class UpdateDelCatBL {
	/**----------------------------------------------------------------------*
	 *■exeUpdateDelCatメソッド
	 *概要　：対象のネコデータを削除する
	 *引数　：対象のネコid（int型）
	 *戻り値：DB操作成功フラグ（true:成功/false:失敗）
	 *----------------------------------------------------------------------**/
	public boolean exeUpdateDelCat(int catId) {
		
		boolean   succesUpdate = false ; 
		
		CatsInfoDao dao = new CatsInfoDao();
		succesUpdate = dao.doUpdateDelCat(catId);
		
		return succesUpdate;

	}
}

