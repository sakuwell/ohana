package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyPageDao {
    // データベースへの接続情報

    // JDBCドライバの相対パス
    String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

    // 接続先のデータベース
    String JDBC_URL = "jdbc:mysql://192.168.1.35/pepe_db?characterEncoding=UTF-8&useSSL=false";

    // 接続するユーザー名
    String USER_ID = "ip_user";

    // 接続するユーザーのパスワード
    String USER_PASS = "23kkos";

    // メソッド
    
    
	public List<MyPageDto> doSelectCatInfo(int id) {
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
            buf.append("SELECT ");
            buf.append("    C.USERID AS OWNERID, ");
            buf.append("    C.CATID, ");
            buf.append("    C.CATNAME, ");
            buf.append("    C.KIND, ");
            buf.append("    C.BIRTH, ");
            buf.append("    C.GENDER, ");
            buf.append("    C.WEIGHT, ");
            buf.append("    C.IMAGE, ");
            buf.append("    C.COMMENT, ");
            buf.append("    C.REG_DATE, ");
            buf.append("    CASE ");
            buf.append("        WHEN C.BIRTH IS NOT NULL THEN CONCAT( ");
            buf.append("            TIMESTAMPDIFF(YEAR, C.BIRTH, CURDATE()), '歳', ");
            buf.append("            TIMESTAMPDIFF(MONTH, C.BIRTH, CURDATE()) % 12, 'ヵ月' ");
            buf.append("        ) ");
            buf.append("        ELSE NULL ");
            buf.append("    END AS AGE ");
            buf.append("FROM CATS_INFO AS C ");
            buf.append("WHERE C.USERID = ? AND C.DEL = 0;");

            // PreparedStatement（SQL発行用オブジェクト）を生成＆発行するSQLをセット
            ps = con.prepareStatement(buf.toString());
            // PreparedStatementのSQL文をセット
            ps.setInt(1, id);
            // 1つ目のSQLクエリを実行し、結果を取得
            rs = ps.executeQuery();

			//パラメータをセット
			while (rs.next()) {
                MyPageDto catDto = new MyPageDto();
                catDto.setOwnerId(rs.getInt("OWNERID"));
                catDto.setCatId(rs.getInt("CATID"));
                catDto.setCatName(rs.getString("CATNAME"));
                catDto.setKind(rs.getInt("KIND"));
                catDto.setBirth(rs.getDate("BIRTH"));
                catDto.setAge(rs.getString("AGE"));
                catDto.setGender(rs.getInt("GENDER"));
                catDto.setWeight(rs.getFloat("WEIGHT"));
                catDto.setImage(rs.getBytes("IMAGE"));
                catDto.setComment(rs.getString("COMMENT"));
                catDto.setRegDate(rs.getTimestamp("REG_DATE"));

                dtoList.add(catDto);
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
	
	
	public List<MyPageDto> doSelectMessageInfo(int id) {
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

            buf.append("SELECT ");
            buf.append("    M.MESSAGEID, ");
            buf.append("    M.MESSAGE, ");
            buf.append("    CASE ");
            buf.append("        WHEN M.SENDERID = ? THEN 's' ");
            buf.append("        WHEN M.RECIEVERID = ? THEN 'r' ");
            buf.append("        ELSE NULL ");
            buf.append("    END AS MESSAGETYPE, ");
            buf.append("    CASE ");
            buf.append("        WHEN M.SENDERID = ? THEN M.RECIEVERID ");
            buf.append("        WHEN M.RECIEVERID = ? THEN M.SENDERID ");
            buf.append("        ELSE NULL ");
            buf.append("    END AS TARGETUSERID, ");
            buf.append("    ( ");
            buf.append("        SELECT NAME ");
            buf.append("        FROM USERS_INFO AS U2 ");
            buf.append("        WHERE U2.ID = ( ");
            buf.append("            CASE ");
            buf.append("                WHEN M.SENDERID = ? THEN M.RECIEVERID ");
            buf.append("                WHEN M.RECIEVERID = ? THEN M.SENDERID ");
            buf.append("                ELSE NULL ");
            buf.append("            END ");
            buf.append("        ) ");
            buf.append("    ) AS TARGETUSERNAME, ");
            buf.append("    M.CATID AS TARGETCATID, ");
            buf.append("    C.CATNAME AS TARGETCATNAME, ");
            buf.append("    M.SEND_DATE ");
            buf.append("FROM MESSAGES AS M ");
            buf.append("LEFT JOIN CATS_INFO AS C ON M.CATID = C.CATID ");
            buf.append("WHERE ");
            buf.append("    M.DEL = 0 ");
            buf.append("    AND (M.SENDERID = ? OR M.RECIEVERID = ?) ");
            buf.append("ORDER BY M.SEND_DATE DESC;");

			
			// PreparedStatement（SQL発行用オブジェクト）を生成＆発行するSQLをセット
			ps = con.prepareStatement(buf.toString());
			// PreparedStatementのSQL文をセット
			ps.setInt(1, id);
			ps.setInt(2, id);
			ps.setInt(3, id);
			ps.setInt(4, id);
			ps.setInt(5, id);
			ps.setInt(6, id);
			ps.setInt(7, id);
			ps.setInt(8, id);
			// 1つ目のSQLクエリを実行し、結果を取得
			rs = ps.executeQuery();
			
			//パラメータをセット
            while (rs.next()) {
                MyPageDto messageDto = new MyPageDto();
                messageDto.setMessageId(rs.getInt("MESSAGEID"));
                messageDto.setMessage(rs.getString("MESSAGE"));
                messageDto.setMessageType(rs.getString("MESSAGETYPE"));
                messageDto.setTargetUserId(rs.getInt("TARGETUSERID"));
                messageDto.setTargetUserName(rs.getString("TARGETUSERNAME"));
                messageDto.setTargetCatId(rs.getInt("TARGETCATID"));
                messageDto.setTargetCatName(rs.getString("TARGETCATNAME"));
                messageDto.setSentDate(rs.getTimestamp("SEND_DATE"));

                dtoList.add(messageDto);
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
