package model;

/**----------------------------------------------------------------------*
 *■executeSelectCatListsメソッド
 *概要　：引数の性別に紐づくネコ情報を「cats_info」テーブルから全て抽出する
 *引数①　：ネコの性別(男の子)
 *引数②　:ネコの性別(女の子)
 *戻り値　:「cats_info」テーブルから抽出したネコデータ(CatsInfoDto型のリスト)
 *----------------------------------------------------------------------**/
import java.util.List;

public class SearchCatBL {
	/**----------------------------------------------------------------------*
	 *■executeSelectCatListsメソッド
	 *概要　：引数のユーザーIDに紐づくネコデータを「cats_info」テーブルから抽出する
	 *引数①：性別:男の子（String型）
	 *引数②：性別:女の子（String型）
	 *戻り値：「cats_info」テーブルから抽出したユーザーデータ（CatsInfoDto型のリスト）
	 *----------------------------------------------------------------------**/
	public List<CatsInfoDto> executeSelectCatLists(String gender1,String gender2){
		CatsInfoDao dao = new CatsInfoDao();
		List<CatsInfoDto> dtoList=dao.doSelectCatLists(gender1,gender2);
		
		return dtoList;
	}
}
