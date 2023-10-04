package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**----------------------------------------------------------------------*
 *Filename:CatsInfoDao.java
 *
 *Description:
 *	このクラスは、データベースの「cats_info」テーブルに接続し、処理を行うためのものです。
 *	各メソッドに応じた処理を行っています
 *	
 *Author:加藤、大久保
 *Creation Date:2023-09-04
 *
 *Copyright © 2023 KEG Sakura All rights reserved.
 *----------------------------------------------------------------------**/

public class CatsInfoDao {
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
	//※ユーザー名が「test_user」でない場合は該当の箇所を変更してください
	String USER_ID     = "ip_user";
	//接続するユーザーのパスワード
	//※パスワードが「test_pass」でない場合は該当の箇所を変更してください
	//	String USER_PASS   = "keg_pass";
	String USER_PASS   = "23kkos";


	//----------------------------------------------------------------
	//メソッド
	//----------------------------------------------------------------

	/**----------------------------------------------------------------------*
	 *■doSelectCatListsメソッド
	 *概要　：引数のCat情報に紐づくCatデータを「cats_info」テーブルから抽出する
	 *引数①：性別1（ユーザー選択）
	 *引数②：性別2（ユーザー選択）
	 *戻り値：「cats_info」テーブルから抽出したCatデータリスト（CatsInfoDto型）
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
			buf.append("  CATID,              ");
			buf.append("  CATNAME,            ");
			buf.append("  KIND,            	  ");
			buf.append("  CONCAT(TIMESTAMPDIFF(YEAR,BIRTH,CURTIME()),'歳',MOD(TIMESTAMPDIFF(MONTH,BIRTH,CURTIME()),12),'ヶ月') AS AGE,                ");
			buf.append("  GENDER,             ");
			buf.append("  IMAGE 			  ");
			buf.append("FROM                  ");
			buf.append("  CATS_INFO           ");
			buf.append("  WHERE DEL = 0   AND ");
			buf.append("   (GENDER = ?        ");
			if(gender1 != null) {
				buf.append("OR   ?      ) ;	  ");
			}else {
				buf.append("    ) ;			  ");
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
				dto.setCatId(rs.getInt("CATID"));		  //第一パラメータ:(CATID)
				dto.setCatName(  rs.getString("CATNAME"));//第二パラメータ:(CATNAME)
				dto.setKind(  rs.getInt("KIND"));		  //第三パラメータ:(KIND)
				dto.setAge( rs.getString( "AGE"  ));	  //第四パラメータ:(AGE)
				dto.setGender( rs.getInt( "GENDER"  ));	  //第五パラメータ:(GENDER)
				dto.setImage( rs.getBytes( "IMAGE"  ));	  //第六パラメータ:(IMAGE)

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

	/**----------------------------------------------------------------------*
	 *■doSelectCatInfoメソッド
	 *概要　：引数のCat情報に紐づくCatデータを「cats_info」テーブルと「users_info」テーブルから抽出する
	 *引数①：ねこのID（ユーザー選択）
	 *戻り値：「cats_info」テーブルと「users_info」テーブルから抽出したCatデータ（CatsInfoDto型）
	 *----------------------------------------------------------------------**/
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
			buf.append("SELECT                		");
			buf.append("  CI.CATID,           		");
			buf.append("  CI.OWNERID,         		");
			buf.append("  CI.CATNAME,         		");
			buf.append("  CI.KIND,            		");
			buf.append("  CI.BIRTH,           		");
			buf.append("  CONCAT(TIMESTAMPDIFF(YEAR,CI.BIRTH,CURTIME()),'歳',MOD(TIMESTAMPDIFF(MONTH,CI.BIRTH,CURTIME()),12),'ヶ月') AS AGE,                ");
			buf.append("  CI.GENDER,          		");
			buf.append("  CI.WEIGHT, 		  		");
			buf.append("  CI.IMAGE,           		");
			buf.append("  CI.COMMENT,         		");
			buf.append("  CI.REG_DATE,        		");
			buf.append("  UI.NAME             		");
			buf.append("FROM                  		");
			buf.append("  CATS_INFO AS CI     		");
			buf.append("  INNER JOIN          		");
			buf.append("  USERS_INFO AS UI    		");
			buf.append("  ON CI.OWNERID=UI.ID 		");
			buf.append("  WHERE  CI.CATID =    ?    ");
			buf.append("  AND  CI.DEL =   0  ;		");

			ps = con.prepareStatement(buf.toString());
			ps.setInt(1, catId);
			rs = ps.executeQuery();


			//ResultSetオブジェクトからDTOリストに格納
			while (rs.next()) {
				dto.setCatId(rs.getInt("CI.CATID"));				//第1パラメータ:ネコID
				dto.setOwnerId(  rs.getInt(    "CI.OWNERID"   ));	//第2パラメータ:飼い主ID
				dto.setCatName(  rs.getString(    "CI.CATNAME"   ));//第3パラメータ:ネコ名前
				dto.setKind(  rs.getInt(    "CI.KIND"   ));			//第4パラメータ:猫種
				dto.setBirth(  rs.getDate(    "CI.BIRTH"   ));		//第5パラメータ:誕生日
				dto.setAge(  rs.getString(    "AGE"   ));			//第6パラメータ:年齢
				dto.setGender( rs.getInt( "CI.GENDER"  ));			//第7パラメータ:性別
				dto.setWeight( rs.getFloat( "CI.WEIGHT"  ));		//第8パラメータ:体重
				dto.setImage( rs.getBytes( "CI.IMAGE"  ));			//第9パラメータ:画像
				dto.setComment( rs.getString( "CI.COMMENT"  ));		//第10パラメータ:飼い主コメント
				dto.setReg_Date( rs.getTimestamp( "CI.REG_DATE"  ));//第11パラメータ:登録日
				dto.setUserName( rs.getString( "UI.NAME"  ));		//第12パラメータ:飼い主名前

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


	/**----------------------------------------------------------------------*
	 *■doInsertメソッド
	 *概要　：「cats_info」テーブルに対象のネコちゃん情報を挿入する
	 *引数　：対象のネコちゃん情報（CatsInfoDto型）
	 *戻り値：実行結果（真：成功、偽：例外発生）
	 *----------------------------------------------------------------------**/

	public boolean doInsert(CatsInfoDto dto) {

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

		//実行結果（真：成功、偽：例外発生）格納用変数
		//※最終的にreturnするため、tryブロック内で宣言してはいけないことに注意
		boolean isSuccess = true ;

		try {

			//-------------------------------------------
			//接続の確立（Connectionオブジェクトの取得）
			//-------------------------------------------
			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

			//-------------------------------------------
			//トランザクションの開始
			//-------------------------------------------
			//オートコミットをオフにする（トランザクション開始）
			con.setAutoCommit(false);

			//-------------------------------------------
			//SQL文の送信 ＆ 結果の取得
			//-------------------------------------------

			//発行するSQL文の生成（INSERT）
			StringBuffer buf = new StringBuffer();
			buf.append("INSERT INTO CATS_INFO (  ");
			buf.append("  OWNERID,              ");
			buf.append("  CATNAME,                ");
			buf.append("  KIND,                ");
			buf.append("  BIRTH,                ");
			buf.append("  GENDER,                ");
			buf.append("  WEIGHT,                ");
			buf.append("  IMAGE,                ");
			buf.append("  COMMENT,                ");
			buf.append("  REG_DATE                ");
			buf.append(") VALUES (            ");
			buf.append("  ?,                  ");
			buf.append("  ?,                  ");
			buf.append("  ?,                  ");
			buf.append("  ?,                  ");
			buf.append("  ?,                  ");
			buf.append("  ?,                  ");
			buf.append("  ?,                  ");
			buf.append("  ?,                  ");
			buf.append("  ?                 ");
			buf.append(")                     ");

			//PreparedStatementオブジェクトを生成＆発行するSQLをセット
			ps = con.prepareStatement(buf.toString());


			//パラメータをセット
			ps.setInt(    1, dto.getOwnerId()              ); //第1パラメータ：追加データ（ユーザーID）
			ps.setString(       2, dto.getCatName()        ); //第2パラメータ：追加データ（名前）
			ps.setInt(       3, dto.getKind()              ); //第3パラメータ：追加データ（種類）
			ps.setDate(       4, dto.getBirth()            ); //第4パラメータ：追加データ（誕生日）
			ps.setInt(       5, dto.getGender()            ); //第5パラメータ：追加データ（性別）
			ps.setFloat(       6, dto.getWeight()          ); //第6パラメータ：追加データ（体重）
			ps.setBytes(       7, dto.getImage()           ); //第7パラメータ：追加データ（写真）
			ps.setString(       8, dto.getComment()        ); //第8パラメータ：追加データ（コメント）
			ps.setTimestamp(       9, dto.getReg_Date()    ); //第9パラメータ：追加データ（追加日）				

			//SQL文の実行
			ps.executeUpdate();

			//	int rowsAffected = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

			//実行結果を例外発生として更新
			isSuccess = false ;

		} finally {
			//-------------------------------------------
			//トランザクションの終了
			//-------------------------------------------
			if(isSuccess){
				//明示的にコミットを実施
				try {
					con.commit();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}else{
				//明示的にロールバックを実施
				try {
					con.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			//-------------------------------------------
			//接続の解除
			//-------------------------------------------

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

		//実行結果を返す
		return isSuccess;
	}

	/**----------------------------------------------------------------------*
	 *■doselectoneメソッド
	 *概要　：「cats_info」テーブルから対象のネコちゃん情報を抽出する
	 *引数　：対象のネコちゃん情報（CatsInfoDto型）
	 *戻り値：Cats_Infoから抽出したネコ情報
	 *----------------------------------------------------------------------**/

	public CatsInfoDto doSelectCatOne(int catId) {

		//JDBCの接続に使用するオブジェクトを宣言
		//※finallyブロックでも扱うためtryブロック内で宣言してはいけないことに注意
		CatsInfoDto dto = new CatsInfoDto();;
		Connection        con = null ;   // Connection（DB接続情報）格納用変数
		PreparedStatement ps  = null ;   // PreparedStatement（SQL発行用オブジェクト）格納用変数
		ResultSet         rs  = null ;   // ResultSet（SQL抽出結果）格納用変数
		
		//抽出結果格納用DTOリスト

		try {
			Class.forName(DRIVER_NAME);
			//-------------------------------------------
			//接続の確立（Connectionオブジェクトの取得）
			//-------------------------------------------
			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);
			//-------------------------------------------
			//SQL発行
			//-------------------------------------------


			//発行するSQL文の生成（SELECT）
			StringBuffer buf = new StringBuffer();
			buf.append("SELECT                 ");
			buf.append("  CATID,               ");
			buf.append("  OWNERID,             ");
			buf.append("  CATNAME,             ");
			buf.append("  KIND,                ");
			buf.append("  BIRTH,               ");
			buf.append("  GENDER,              ");
			buf.append("  WEIGHT, 			   ");
			buf.append("  IMAGE,               ");
			buf.append("  COMMENT,             ");
			buf.append("  UP_DATE              ");
			buf.append("FROM                   ");
			buf.append("  CATS_INFO            ");
			buf.append("  WHERE  CATID =   ?  ;");


			ps = con.prepareStatement(buf.toString());
			
			ps.setInt(1, catId);
			rs = ps.executeQuery();


			//ResultSetオブジェクトからDTOリストに格納 
			while (rs.next()) {
			dto.setCatId(rs.getInt("CATID")					);//第1パラメータ:ネコID
			dto.setOwnerId(  rs.getInt(    "OWNERID"   )	);//第2パラメータ:飼い主ID
			dto.setCatName(  rs.getString(    "CATNAME"   )	);//第3パラメータ:ネコ名前
			dto.setKind(  rs.getInt(    "KIND"   )			);//第4パラメータ:猫種
			dto.setBirth(  rs.getDate(    "BIRTH"   )		);//第5パラメータ:誕生日
			dto.setGender( rs.getInt( "GENDER"  )			);//第6パラメータ:性別
			dto.setWeight( rs.getFloat( "WEIGHT"  )			);//第7パラメータ:体重
			dto.setImage( rs.getBytes( "IMAGE"  )			);//第8パラメータ:画像
			dto.setComment( rs.getString( "COMMENT"  )		);//第9パラメータ:飼い主コメント
			dto.setUp_Date( rs.getTimestamp( "UP_DATE"  )	);//第10パラメータ:更新日
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e){
			e.printStackTrace();
		} finally {
			//-------------------------------------------
			//接続の解除
			//-------------------------------------------
		}


		//抽出結果を返す
		return dto;
	}

	/**----------------------------------------------------------------------*
	 *■doUpdateCatメソッド
	 *概要　：「CatsInfo」テーブルに対象のネコちゃん情報をアップデートする
	 *引数　：対象のネコちゃん情報（CatsInfoDto型）
	 *戻り値：実行結果（真：成功、偽：例外発生）
	 *----------------------------------------------------------------------**/

	public boolean doUpdateCat(CatsInfoDto dto) {


		Connection        con = null ;   // Connection（DB接続情報）格納用変数
		PreparedStatement ps  = null ;   // PreparedStatement（SQL発行用オブジェクト）格納用変数

		boolean isSuccess = true;

		try {
			Class.forName(DRIVER_NAME);
			//-------------------------------------------
			//接続の確立（Connectionオブジェクトの取得）
			//-------------------------------------------
			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

			//				オートコミットをオフにする
			con.setAutoCommit(false);

			//発行するSQL文の生成（SELECT）
			StringBuffer buf = new StringBuffer();
			buf.append("UPDATE Cats_Info SET  	");
			buf.append("  OWNERID = ? ,         ");
			buf.append("  CATNAME = ? ,         ");
			buf.append("  KIND = ? ,            ");
			buf.append("  BIRTH = ? ,           ");
			buf.append("  GENDER = ? ,          ");
			buf.append("  WEIGHT = ? ,          ");
			buf.append("  IMAGE = ? ,           ");
			buf.append("  COMMENT = ? ,         ");
			buf.append("  UP_DATE = ?  			");
			buf.append("  WHERE CATID = ?       ");


			//PreparedStatementオブジェクトを生成＆発行するSQLをセット
			ps = con.prepareStatement(buf.toString());

			//パラメータをセット
			ps.setInt(1, dto.getOwnerId()	   ); //第1パラメータ：更新データ（飼い主ID）
			ps.setString(2, dto.getCatName()   ); //第2パラメータ：更新データ（ネコ名）
			ps.setInt(3, dto.getKind()		   ); //第3パラメータ：更新データ（種類）
			ps.setDate(4, dto.getBirth()	   ); //第4パラメータ：更新データ（誕生日）
			ps.setInt(5, dto.getGender()	   ); //第5パラメータ：更新データ（性別）
			ps.setFloat(6, dto.getWeight()	   ); //第6パラメータ：更新データ（体重）
			ps.setBytes(7, dto.getImage()	   ); //第7パラメータ：更新データ（写真）
			ps.setString(8, dto.getComment()   ); //第8パラメータ：更新データ（コメント）
			ps.setTimestamp(9, dto.getUp_Date());//第9パラメータ：更新データ（更新日）
			ps.setInt(10, dto.getCatId()	   );//第10パラメータ：更新データ（CATID）

			ps.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			// 例外処理を行う（エラーメッセージを表示したり、アプリケーションを終了したり、適切な処理を行う）
		} catch (SQLException e) {
			e.printStackTrace();
			// 例外処理を行う（SQL実行時のエラー処理を行う）

		} finally {

			//-------------------------------------------
			//トランザクションの終了
			//-------------------------------------------
			if (isSuccess) {
				//明示的にコミットを実施
				try {
					con.commit();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			} else {
				//明示的にロールバックを実施
				try {
					con.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			//-------------------------------------------
			//接続の解除
			//-------------------------------------------

			//PreparedStatementオブジェクトの接続解除
			if (ps != null) { //接続が確認できている場合のみ実施
				try {
					ps.close(); //接続の解除
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			//Connectionオブジェクトの接続解除
			if (con != null) { //接続が確認できている場合のみ実施
				try {
					con.close(); //接続の解除
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return isSuccess;

	}



	/**----------------------------------------------------------------------*
	 *■doUpdateDelCatメソッド
	 *概要　：「CatsInfo」テーブルから対象のネコちゃん情報を削除する
	 *引数　：対象のネコちゃんId（int型）
	 *戻り値：実行結果（真：成功、偽：例外発生）
	 *----------------------------------------------------------------------**/


	public boolean doUpdateDelCat(int catId) {


		Connection        con = null ;   // Connection（DB接続情報）格納用変数
		PreparedStatement ps  = null ;   // PreparedStatement（SQL発行用オブジェクト）格納用変数

		boolean isSuccess = true;

		try {
			Class.forName(DRIVER_NAME);
			//-------------------------------------------
			//接続の確立（Connectionオブジェクトの取得）
			//-------------------------------------------
			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

			//			オートコミットをオフにする
			con.setAutoCommit(false);

			//発行するSQL文の生成（SELECT）
			StringBuffer buf = new StringBuffer();
			buf.append("UPDATE Cats_Info SET   ");
			buf.append("  DEL = 1  			   ");
			buf.append("  WHERE CATID = ?      ");


			//PreparedStatementオブジェクトを生成＆発行するSQLをセット
			ps = con.prepareStatement(buf.toString());


			//パラメータをセット
			ps.setInt(1, catId);//第10パラメータ：更新データ（更新日）

			ps.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			// 例外処理を行う（エラーメッセージを表示したり、アプリケーションを終了したり、適切な処理を行う）
		} catch (SQLException e) {
			e.printStackTrace();
			// 例外処理を行う（SQL実行時のエラー処理を行う）

		} finally {

			//-------------------------------------------
			//トランザクションの終了
			//-------------------------------------------
			if (isSuccess) {
				//明示的にコミットを実施
				try {
					con.commit();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			} else {
				//明示的にロールバックを実施
				try {
					con.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			//-------------------------------------------
			//接続の解除
			//-------------------------------------------

			//PreparedStatementオブジェクトの接続解除
			if (ps != null) { //接続が確認できている場合のみ実施
				try {
					ps.close(); //接続の解除
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			//Connectionオブジェクトの接続解除
			if (con != null) { //接続が確認できている場合のみ実施
				try {
					con.close(); //接続の解除
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return isSuccess;

	}

}
