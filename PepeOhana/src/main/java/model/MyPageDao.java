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

    /**
     * ----------------------------------------------------------------------
     * ■doSelectメソッド 概要：引数のユーザー情報に紐づくユーザーデータを「usersinfo」「catsinfo」「messages」テーブルから抽出する 引数：ユーザーID（session） 戻り値：「usersinfo」「catsinfo」「messages」テーブルから抽出したマイページデータ（MyPageDto型）
     * ----------------------------------------------------------------------
     **/
    public List<MyPageDto> doSelectMyInfo(int id) {
        // JDBCドライバのロード
        try {
            Class.forName(DRIVER_NAME); // JDBCドライバをロード＆接続先として指定
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // SQL発行
        Connection con = null; // Connection（DB接続情報）格納用変数

        // 抽出結果格納用DTOリスト
        List<MyPageDto> dtoList = new ArrayList<MyPageDto>();

        try {
            // 接続の確立（Connectionオブジェクトの取得）
            con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

            PreparedStatement ps1 = null;
            ResultSet rs1 = null;

            // 1つ目のSQLクエリの生成
            StringBuffer buf1 = new StringBuffer();
            buf1.append("SELECT ");
            buf1.append("    C.USERID AS OWNERID, ");
            buf1.append("    C.CATID, ");
            buf1.append("    C.CATNAME, ");
            buf1.append("    C.KIND, ");
            buf1.append("    C.BIRTH, ");
            buf1.append("    C.GENDER, ");
            buf1.append("    C.WEIGHT, ");
            buf1.append("    C.IMAGE, ");
            buf1.append("    C.COMMENT, ");
            buf1.append("    C.REG_DATE, ");
            buf1.append("    CASE ");
            buf1.append("        WHEN C.BIRTH IS NOT NULL THEN CONCAT( ");
            buf1.append("            TIMESTAMPDIFF(YEAR, C.BIRTH, CURDATE()), '歳', ");
            buf1.append("            TIMESTAMPDIFF(MONTH, C.BIRTH, CURDATE()) % 12, 'ヵ月' ");
            buf1.append("        ) ");
            buf1.append("        ELSE NULL ");
            buf1.append("    END AS AGE ");
            buf1.append("FROM CATS_INFO AS C ");
            buf1.append("WHERE C.USERID = ? AND C.DEL = 0;");

            try {
                // PreparedStatement（SQL発行用オブジェクト）を生成＆発行するSQLをセット
                ps1 = con.prepareStatement(buf1.toString());

                // PreparedStatementのSQL文をセット
                ps1.setInt(1, id);

                // 1つ目のSQLクエリを実行し、結果を取得
                rs1 = ps1.executeQuery();

                // パラメータをセット
                while (rs1.next()) {
                    MyPageDto catDto = new MyPageDto();
                    catDto.setOwnerId(rs1.getInt("OWNERID"));
                    catDto.setCatId(rs1.getInt("CATID"));
                    catDto.setCatName(rs1.getString("CATNAME"));
                    catDto.setKind(rs1.getInt("KIND"));
                    catDto.setBirth(rs1.getDate("BIRTH"));
                    catDto.setAge(rs1.getString("AGE"));
                    catDto.setGender(rs1.getInt("GENDER"));
                    catDto.setWeight(rs1.getFloat("WEIGHT"));
                    catDto.setImage(rs1.getBytes("IMAGE"));
                    catDto.setComment(rs1.getString("COMMENT"));
                    catDto.setRegDate(rs1.getTimestamp("REG_DATE"));

                    dtoList.add(catDto);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                // ResultSetオブジェクトとPreparedStatementオブジェクトの接続解除
                if (rs1 != null) {
                    try {
                        rs1.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                if (ps1 != null) {
                    try {
                        ps1.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

            // 2つ目のSQLクエリの生成
            PreparedStatement ps2 = null;
            ResultSet rs2 = null;

            StringBuffer buf2 = new StringBuffer();
            buf2.append("SELECT ");
            buf2.append("    M.MESSAGEID, ");
            buf2.append("    M.MESSAGE, ");
            buf2.append("    CASE ");
            buf2.append("        WHEN M.SENDERID = 18 THEN 's' ");
            buf2.append("        WHEN M.RECIEVERID = 18 THEN 'r' ");
            buf2.append("        ELSE NULL ");
            buf2.append("    END AS MESSAGETYPE, ");
            buf2.append("    CASE ");
            buf2.append("        WHEN M.SENDERID = 18 THEN M.RECIEVERID ");
            buf2.append("        WHEN M.RECIEVERID = 18 THEN M.SENDERID ");
            buf2.append("        ELSE NULL ");
            buf2.append("    END AS TARGETUSERID, ");
            buf2.append("    ( ");
            buf2.append("        SELECT NAME ");
            buf2.append("        FROM USERS_INFO U2 ");
            buf2.append("        WHERE U2.ID = ( ");
            buf2.append("            CASE ");
            buf2.append("                WHEN M.SENDERID = 18 THEN M.RECIEVERID ");
            buf2.append("                WHEN M.RECIEVERID = 18 THEN M.SENDERID ");
            buf2.append("                ELSE NULL ");
            buf2.append("            END ");
            buf2.append("        ) ");
            buf2.append("    ) AS TARGETUSERNAME, ");
            buf2.append("    M.CATID AS TARGETCATID, ");
            buf2.append("    C.CATNAME AS TARGETCATNAME, ");
            buf2.append("    M.SEND_DATE ");
            buf2.append("FROM MESSAGES AS M ");
            buf2.append("LEFT JOIN CATS_INFO AS C ON M.CATID = C.CATID ");
            buf2.append("WHERE ");
            buf2.append("    M.DEL = 0 ");
            buf2.append("    AND (M.SENDERID = 18 OR M.RECIEVERID = 18) ");
            buf2.append("ORDER BY M.SEND_DATE DESC;");


            try {
                // PreparedStatement（SQL発行用オブジェクト）を生成＆発行するSQLをセット
                ps2 = con.prepareStatement(buf2.toString());

                // PreparedStatementのSQL文をセット（必要に応じてパラメータをセット）
                // ps2.setInt(1, ...);
                // ps2.setString(2, ...);

                // 2つ目のSQLクエリを実行し、結果を取得
                rs2 = ps2.executeQuery();

                // 2つ目のSQLクエリの結果を処理
                while (rs2.next()) {
                    MyPageDto messageDto = new MyPageDto();
                    messageDto.setMessageId(rs2.getInt("MESSAGEID"));
                    messageDto.setMessage(rs2.getString("MESSAGE"));
                    messageDto.setMessageType(rs2.getString("MESSAGETYPE"));
                    messageDto.setTargetUserId(rs2.getInt("TARGETUSERID"));
                    messageDto.setTargetUserName(rs2.getString("TARGETUSERNAME"));
                    messageDto.setCatId(rs2.getInt("TARGETCATID"));
                    messageDto.setCatName(rs2.getString("TARGETCATNAME"));
                    messageDto.setSentDate(rs2.getTimestamp("SENTDATE"));

                    dtoList.add(messageDto);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                // ResultSetオブジェクトとPreparedStatementオブジェクトの接続解除
                if (rs2 != null) {
                    try {
                        rs2.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                if (ps2 != null) {
                    try {
                        ps2.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Connectionオブジェクトの接続解除
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        // 1つ目と2つ目のSQLクエリの結果を処理した後のコードを書くことができます

        // 抽出したユーザーデータを戻す
        return dtoList;
    }
}
