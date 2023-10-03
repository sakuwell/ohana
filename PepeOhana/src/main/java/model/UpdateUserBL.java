package model;

public class UpdateUserBL {
	
	/**----------------------------------------------------------------------*
	 *■executeUpdateメソッド
	 *概要　：対象のユーザーデータを更新する
	 *引数　：対象のユーザーデータ（UsersInfoDto型）
	 *戻り値：DB操作成功フラグ（true:成功/false:失敗）
	 *----------------------------------------------------------------------**/
	public boolean executeUpdate(UsersInfoDto dto) {
		
		boolean   succesUpdate = false ; 
		
		UsersInfoDao dao = new UsersInfoDao();
		succesUpdate = dao.doUpdate(dto);
		
		return succesUpdate;
	}

}
