package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CatsInfoDao {
	//-------------------------------------------
		//データベースへの接続情報
		//-------------------------------------------

		//JDBCドライバの相対パス
		//※バージョンによって変わる可能性があります（MySQL5系の場合は「com.mysql.jdbc.Driver」）
		String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

		//接続先のデータベース
		//※データベース名が「test_db」でない場合は該当の箇所を変更してください
//		String JDBC_URL    = "jdbc:mysql://localhost/keg_db?characterEncoding=UTF-8&useSSL=false";
		String JDBC_URL    = "jdbc:mysql://192.168.1.35/pepe_db?characterEncoding=UTF-8&useSSL=false";
		//接続するユーザー名
		//※ユーザー名が「test_user」でない場合は該当の箇所を変更してください
//		String USER_ID     = "keg_user";
		String USER_ID     = "ip_user";
		//接続するユーザーのパスワード
		//※パスワードが「test_pass」でない場合は該当の箇所を変更してください
//		String USER_PASS   = "keg_pass";
		String USER_PASS   = "23kkos";
		
		
		//----------------------------------------------------------------
		//メソッド
		//----------------------------------------------------------------
		
		/**----------------------------------------------------------------------*
		 *■doSelectメソッド
		 *概要　：引数のユーザー情報に紐づくユーザーデータを「user_info」テーブルから抽出する
		 *引数①：ユーザーID（ユーザー入力）
		 *引数②：ユーザーパスワード（ユーザー入力）
		 *戻り値：「user_info」テーブルから抽出したユーザーデータ（UserInfoDto型）
		 *----------------------------------------------------------------------**/
		public List<CatsInfoDto> doSelectCatLists(String gender1,String gender2) {
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
					List<CatsInfoDto> dtoList = new ArrayList<CatsInfoDto>();

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
						buf.append("SELECT                ");
						buf.append("  CATID,               ");
						buf.append("  CATNAME,               ");
						buf.append("  KIND,            	  ");
						buf.append("  CONCAT(TIMESTAMPDIFF(YEAR,BIRTH,CURTIME()),'歳',MOD(TIMESTAMPDIFF(MONTH,BIRTH,CURTIME()),12),'ヶ月') AS AGE,                ");
						buf.append("  GENDER,                ");
						buf.append("  IMAGE ");
						buf.append("FROM                  ");
						buf.append("  CATS_INFO              ");
						buf.append("  WHERE GENDER = ?       ");
						if(gender1 != null) {
							buf.append("OR   ?       ;");
						}
						//PreparedStatement（SQL発行用オブジェクト）を生成＆発行するSQLをセット
						ps = con.prepareStatement(buf.toString());
						if(gender1!=null) {
							ps.setString(1, gender1);
							ps.setString(2, gender2);
						}else {
							ps.setString(1, gender2);
						}
						rs = ps.executeQuery();

						//パラメータをセット
						while (rs.next()) {
							CatsInfoDto dto = new CatsInfoDto();
							dto.setCatId(rs.getInt("CATID"));
							dto.setCatName(  rs.getString("CATNAME"));
							dto.setKind(  rs.getString("KIND"));
							dto.setAge( rs.getString( "AGE"  ));
							dto.setGender( rs.getInt( "GENDER"  ));
							dto.setImage( rs.getBytes( "IMAGE"  ));

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
		public CatsInfoDto doSelectCatInfo(int catId) {
			CatsInfoDto dto = new CatsInfoDto();
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
			Connection        con = null ;   // Connection（DB接続情報）格納用変数
			PreparedStatement ps  = null ;   // PreparedStatement（SQL発行用オブジェクト）格納用変数
			ResultSet         rs  = null ;   // ResultSet（SQL抽出結果）格納用変数

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
				buf.append("SELECT                ");
				buf.append("  CI.CATID,               ");
				buf.append("  CI.USERID,               ");
				buf.append("  CI.CATNAME,               ");
				buf.append("  CI.KIND,                ");
				buf.append("  CI.BIRTH,                ");
				buf.append("  CONCAT(TIMESTAMPDIFF(YEAR,CI.BIRTH,CURTIME()),'歳',MOD(TIMESTAMPDIFF(MONTH,CI.BIRTH,CURTIME()),12),'ヶ月') AS AGE,                ");
				buf.append("  CI.GENDER,            	  ");
				buf.append("  CI.WEIGHT, ");
				buf.append("  CI.IMAGE,            ");
				buf.append("  CI.COMMENT,                  ");
				buf.append("  CI.UP_DATE,                  ");
				buf.append("  UI.NAME                  ");
				buf.append("FROM                  ");
				buf.append("  CATS_INFO AS CI              ");
				buf.append("  INNER JOIN               ");
				buf.append("  USERS_INFO AS UI            ");
				buf.append("  ON CI.USERID=UI.ID              ");
				buf.append("  WHERE  CI.CATID =    ?    ;");
//				buf.append("  AND  CI.DEL =   0  ;");
				
				ps = con.prepareStatement(buf.toString());
				ps.setInt(1, catId);
				rs = ps.executeQuery();
				

				//ResultSetオブジェクトからDTOリストに格納
				while (rs.next()) {
					dto.setCatId(rs.getInt("CI.CATID"));
					dto.setUserId(  rs.getInt(    "CI.USERID"   ));
					dto.setCatName(  rs.getString(    "CI.CATNAME"   ));
					dto.setKind(  rs.getString(    "CI.KIND"   ));
					dto.setBirth(  rs.getDate(    "CI.BIRTH"   ));
					dto.setAge(  rs.getString(    "AGE"   ));
					dto.setGender( rs.getInt( "CI.GENDER"  ));
					dto.setWeight( rs.getFloat( "CI.WEIGHT"  ));
					dto.setImage( rs.getBytes( "CI.IMAGE"  ));
					dto.setComment( rs.getString( "CI.COMMENT"  ));
					dto.setUpDate( rs.getTimestamp( "CI.UP_DATE"  ));
					dto.setUserName( rs.getString( "UI.NAME"  ));

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

			//抽出結果を返す
			return dto;
		}
}
