package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersInfoDao {
	
	//-------------------------------------------
		//データベースへの接続情報
		//-------------------------------------------

		//JDBCドライバの相対パス
		//※バージョンによって変わる可能性があります（MySQL5系の場合は「com.mysql.jdbc.Driver」）
		String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

		//接続先のデータベース
		//※データベース名が「test_db」でない場合は該当の箇所を変更してください
		String JDBC_URL    = "jdbc:mysql://localhost/pepe_db?characterEncoding=UTF-8&useSSL=false";

		//接続するユーザー名
		//※ユーザー名が「test_user」でない場合は該当の箇所を変更してください
		String USER_ID     = "pepe_user";

		//接続するユーザーのパスワード
		//※パスワードが「test_pass」でない場合は該当の箇所を変更してください
		String USER_PASS   = "23kkos";
		
		
		/**----------------------------------------------------------------------*
		 *■doInsertメソッド
		 *概要　：「userinfo」テーブルに対象のアンケートデータを挿入する
		 *引数　：対象のアンケートデータ（UserInfoDto型）
		 *戻り値：実行結果（真：成功、偽：例外発生）
		 *----------------------------------------------------------------------**/
		public boolean doInsert(UsersInfoDto dto) {

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
				buf.append("INSERT INTO USERINFO (  ");
				buf.append("  USERID,               ");
				buf.append("  USERNAME,                ");
				buf.append("  PASSWORD                ");
				buf.append(") VALUES (            ");
				buf.append("  ?,                  ");
				buf.append("  ?,                  ");
				buf.append("  ?,                  ");
				buf.append(")                     ");
				
				System.out.println();

				//PreparedStatementオブジェクトを生成＆発行するSQLをセット
				ps = con.prepareStatement(buf.toString());

				//パラメータをセット
				ps.setString(    1, dto.getUserId()              ); //第1パラメータ：更新データ（ユーザーID）
				ps.setString(       2, dto.getUserName()               ); //第2パラメータ：更新データ（名前）
				ps.setString(       3, dto.getPassWord()               ); //第3パラメータ：更新データ（性別）

//			System.out.println(dto.getAge());
				
				//SQL文の実行
				ps.executeUpdate();

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
		
		public UsersInfoDto doSelect(String inputUserId, String inputPassWord) {
			
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
			ResultSet rs = null;
			//実行結果（真：成功、偽：例外発生）格納用変数
			//※最終的にreturnするため、tryブロック内で宣言してはいけないことに注意
			UsersInfoDto dto = new UsersInfoDto();

			try {

				//-------------------------------------------
				//接続の確立（Connectionオブジェクトの取得）
				//-------------------------------------------
				con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

				StringBuffer buf = new StringBuffer();
				buf.append(" SELECT             ");
				buf.append("   USERID  ,       ");
				buf.append("   USERNAME,       ");
				buf.append("   PASSWORD         ");
				
				ps = con.prepareStatement(buf.toString());
				
				ps.setString( 1, inputUserId   );  //第1パラメータ：ユーザーID（ユーザー入力）
				ps.setString( 2, inputPassWord ); 
				
				if (rs.next()) {
					dto.setUserId(rs.getString("USERID"));
					dto.setUserName(rs.getString("USERNAME"));
					dto.setPassWord(rs.getString("PASSWORD"));
				}
				
				


			} catch (SQLException e) {
				e.printStackTrace();

				

			} finally {
				//-------------------------------------------
				//トランザクションの終了
				//-------------------------------------------
				if(rs != null){
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
			return dto;
		}
//--------------------------------------------------		
//		User情報の編集
//--------------------------------------------------
		
		public boolean doUpdate(UsersInfoDto dto) {

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
				buf.append("INSERT INTO USERINFO (  ");
				buf.append("  USERID,               ");
				buf.append("  USERNAME,                ");
				buf.append("  PASSWORD                ");
				buf.append(") VALUES (            ");
				buf.append("  ?,                  ");
				buf.append("  ?,                  ");
				buf.append("  ?,                  ");
				buf.append(")                     ");
				
				System.out.println();

				//PreparedStatementオブジェクトを生成＆発行するSQLをセット
				ps = con.prepareStatement(buf.toString());

				//パラメータをセット
				ps.setString(    1, dto.getUserId()              ); //第1パラメータ：更新データ（ユーザーID）
				ps.setString(       2, dto.getUserName()               ); //第2パラメータ：更新データ（名前）
				ps.setString(       3, dto.getPassWord()               ); //第3パラメータ：更新データ（性別）

//			System.out.println(dto.getAge());
				
				//SQL文の実行
				ps.executeUpdate();

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

}
