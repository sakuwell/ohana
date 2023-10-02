package model;

/**----------------------------------------------------------------------*
 *■executeSelectShowCatInfoメソッド
 *概要　：引数のユーザーIDに紐づくネコデータと飼い主データを「cats_info」テーブルと「users_info」テーブルから抽出する
 *引数　：ネコID
 *戻り値 :「cats_info」テーブルと「users_info」テーブルから抽出したデータ(CatsInfoDto型)
 *----------------------------------------------------------------------**/
public class ShowCatInfoBL {
	public CatsInfoDto executeSelectShowCatInfo(int catId) {
		CatsInfoDao dao = new CatsInfoDao();
		CatsInfoDto dto = dao.doSelectCatInfo(catId);
		return dto;
	}
}
