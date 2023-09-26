package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyPageDao {
	//-------------------------------------------
	//データベースへの接続情報
	//-------------------------------------------

	//JDBCドライバの相対パス
	//※バージョンによって変わる可能性があります（MySQL5系の場合は「com.mysql.jdbc.Driver」）
	String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

	//接続先のデータベース
	//※データベース名が「test_db」でない場合は該当の箇所を変更してください
	String JDBC_URL    = "jdbc:mysql://192.168.1.35/pepe_db?characterEncoding=UTF-8&useSSL=false";

	//接続するユーザー名
	//※ユーザー名が「test_user」ない場合は該当の箇所を変更してください
	String USER_ID     = "ip_user";

	//接続するユーザーのパスワード
	//※パスワードが「test_pass」でない場合は該当の箇所を変更してください
	String USER_PASS   = "23kkos";
	
	
	
	//----------------------------------------------------------------
	//メソッド
	//----------------------------------------------------------------
	
	/**----------------------------------------------------------------------*
	 *■doSelectメソッド
	 *概要　：引数のユーザー情報に紐づくユーザーデータを「usersinfo」「catsinfo」「messages」テーブルから抽出する
	 *引数：ユーザーID（session）
	 *戻り値：「usersinfo」「catsinfo」「messages」テーブルから抽出したマイページデータ（MyPageDto型）
	 *----------------------------------------------------------------------**/
	public List<MyPageDto> doSelectMyInfo(int id) {
		//-------------------------------------------
				//JDBCドライバのロード
				//-------------------------------------------
				try {
					Class.forName(DRIVER_NAME);       //JDBCドライバをロード＆接続先として指定
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}

				//-------------------------------------------
				//SQL発行
				//-------------------------------------------

				//JDBCの接続に使用するオブジェクトを宣言
				//※finallyブロックでも扱うためtryブロック内で宣言してはいけないことに注意
				Connection        con = null ;   // Connection（DB接続情報）格納用変数
				PreparedStatement ps  = null ;   // PreparedStatement（SQL発行用オブジェクト）格納用変数
				ResultSet         rs  = null ;   // ResultSet（SQL抽出結果）格納用変数
				
				//抽出結果格納用DTOリスト
				List<MyPageDto> dtoList = new ArrayList<MyPageDto>();

				try {

					//-------------------------------------------
					//接続の確立（Connectionオブジェクトの取得）
					//-------------------------------------------
					con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

					//-------------------------------------------
					//SQL文の送信 ＆ 結果の取得
					//-------------------------------------------

					//発行するSQL文の生成（SELECT）
					StringBuffer buf = new StringBuffer();
					buf.append(" SELECT DISTINCT");
					buf.append(" 	C.USERID AS OWNERID, ");
					buf.append(" CASE ");
					buf.append("	WHEN C.USERID = ? THEN C.CATID ELSE NULL ");
					buf.append(" END AS CATID, ");
					buf.append(" CASE ");
					buf.append(" 	WHEN C.USERID = ? THEN C.CATNAME ELSE NULL ");
					buf.append(" END AS CATNAME, ");
					buf.append(" CASE ");
					buf.append(" 	WHEN C.USERID = ? THEN C.KIND ELSE NULL ");
					buf.append(" END AS KIND, ");
					buf.append(" CASE ");
					buf.append(" 	WHEN C.USERID = ? THEN C.BIRTH ELSE NULL ");
					buf.append(" END AS BIRTH, ");
					buf.append(" CASE ");
					buf.append(" 	WHEN C.USERID = ? THEN C.GENDER ELSE NULL ");
					buf.append(" END AS GENDER,");
					buf.append(" CASE ");
					buf.append(" 	WHEN C.USERID = ? THEN C.WEIGHT ELSE NULL ");
					buf.append(" END AS WEIGHT, ");
					buf.append(" CASE ");
					buf.append("  	WHEN C.USERID = ? THEN C.IMAGE ELSE NULL ");
					buf.append(" END AS IMAGE, ");
					buf.append(" CASE ");
					buf.append(" 	WHEN C.USERID = ? THEN C.COMMENT ELSE NULL ");
					buf.append(" END AS COMMENT, ");
					buf.append(" CASE ");
					buf.append(" 	WHEN C.USERID = ? THEN C.REG_DATE ELSE NULL ");
					buf.append(" END AS REG_DATE, ");
					buf.append(" M.MESSAGEID, M.MESSAGE, M.SEND_DATE, ");
					buf.append(" CASE ");
					buf.append(" 	WHEN M.SENDERID = ? THEN 's' ");
					buf.append(" 	WHEN M.RECIEVERID = ? THEN 'r' ");
					buf.append(" END AS MESSAGETYPE, ");
					buf.append(" CASE ");
					buf.append(" 	WHEN M.SENDERID = ? THEN M.RECIEVERID ");
					buf.append(" 	WHEN M.RECIEVERID = ? THEN M.SENDERID ");
					buf.append(" END AS TARGETUSERID, ");
					buf.append(" (SELECT NAME FROM USERS_INFO WHERE ID = TARGETUSERID) AS TARGETUSERNAME, ");
					buf.append(" M.CATID AS TARGETCATID, ");
					buf.append(" (SELECT CATNAME FROM CATS_INFO WHERE CATID = M.CATID) AS TARGETCATNAME, ");
					buf.append("  	CASE ");
					buf.append("  	 	WHEN C.USERID = ? AND C.BIRTH IS NOT NULL THEN CONCAT( ");
					buf.append("  	 		TIMESTAMPDIFF(YEAR, C.BIRTH, CURDATE()), '歳', ");
					buf.append("  	 		TIMESTAMPDIFF(MONTH, C.BIRTH, CURDATE())%12, 'ヵ月') ");
					buf.append("  	 	ELSE NULL");
					buf.append("  	END AS AGE");
					buf.append(" FROM CATS_INFO AS C ");
					buf.append(" LEFT JOIN ");
					buf.append(" 	MESSAGES AS M ON ( ");
					buf.append(" 		C.CATID = M.CATID AND (M.SENDERID = ? OR M.RECIEVERID = ?) ");
					buf.append(" 	OR (C.USERID = M.SENDERID AND C.USERID = M.RECIEVERID)) ");
					buf.append(" LEFT JOIN ");
					buf.append(" 	USERS_INFO AS U ON U.USERID = ");
					buf.append("    CASE ");
					buf.append("        WHEN M.SENDERID = ? THEN M.RECIEVERID ");
					buf.append("        WHEN M.RECIEVERID = ? THEN M.SENDERID ");
					buf.append("    END ");
					buf.append(" WHERE ");
					buf.append(" 	M.DEL = 0 AND (C.USERID = ? OR (M.SENDERID = ? OR M.RECIEVERID = ?)) ");
					buf.append(" ORDER BY SEND_DATE DESC	; ");
     

					//PreparedStatement（SQL発行用オブジェクト）を生成＆発行するSQLをセット
					ps = con.prepareStatement(buf.toString());

					// PreparedStatementのSQL文をセットしたコード
					for (int i = 1; i <= 21; i++) {
					    ps.setInt(i, id);
					}
					rs = ps.executeQuery();
					

					//パラメータをセット
					while (rs.next()) {
						MyPageDto dto = new MyPageDto();
						
						dto.setOwnerId(rs.getInt("OWNERID"));
						dto.setCatId(  rs.getInt("CATID"));
						dto.setCatName(  rs.getString("CATNAME"));
						dto.setKind( rs.getInt( "KIND"  ));
						dto.setBirth( rs.getDate( "BIRTH"  ));
						dto.setAge( rs.getString( "AGE"  ));
						dto.setGender( rs.getInt( "GENDER"  ));
						dto.setWeight( rs.getFloat( "WEIGHT"  ));
						dto.setImage( rs.getBytes( "IMAGE"  ));
						dto.setComment( rs.getString( "COMMENT"  ));
						dto.setRegDate( rs.getTimestamp( "REG_DATE"  ));
						dto.setMessageId( rs.getInt( "MESSAGEID"  ));
						dto.setMessage( rs.getString( "MESSAGE"  ));
						dto.setSentDate( rs.getTimestamp( "SEND_DATE"  ));
						dto.setMessageType( rs.getString( "MESSAGETYPE"  ));
						dto.setTargetUserId( rs.getInt( "TARGETUSERID"  ));
						dto.setTargetUserName( rs.getString( "TARGETUSERNAME"  ));
						dto.setTargetCatId( rs.getInt( "TARGETCATID"  ));
						dto.setTargetCatName( rs.getString( "TARGETCATNAME"  ));

						dtoList.add(dto);
						
					}


				} catch (SQLException e) {
					e.printStackTrace();

				} finally {
					//-------------------------------------------
					//接続の解除
					//-------------------------------------------

					//ResultSetオブジェクトの接続解除
					if (rs != null) {    //接続が確認できている場合のみ実施
						try {
							rs.close();  //接続の解除
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}

					//PreparedStatementオブジェクトの接続解除
					if (ps != null) {    //接続が確認できている場合のみ実施
						try {
							ps.close();  //接続の解除
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}

					//Connectionオブジェクトの接続解除
					if (con != null) {    //接続が確認できている場合のみ実施
						try {
							con.close();  //接続の解除
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}

				//抽出したユーザーデータを戻す
				return dtoList;
			}
}
