package model;

public class EditCatOneBL {
	
	/**----------------------------------------------------------------------*
	 *■exeSelectOneCatInfoメソッド
	 *概要　：引数のcatId情報に紐づくネコちゃん情報を「cats_info」テーブルから抽出する
	 *引数①：ユーザーID
	 *戻り値：「cats_info」テーブルから抽出したネコちゃん（CatsInfoDto型）
	 *----------------------------------------------------------------------**/
	
	public CatsInfoDto exeSelectOneCatInfo(int catId) {
		CatsInfoDao dao = new CatsInfoDao();
		CatsInfoDto dto = dao.doSelectCatOne(catId);


		    // 抽出したネコちゃん情報を戻す
		    return dto;
		    
		    
		
	}

}
