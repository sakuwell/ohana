package controller;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.UsersInfoDto;

/**
 * Servlet implementation class ExeRegistCat
 */
@WebServlet("/ExeRegistCat")
public class ExeRegistCat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExeRegistCat() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		
//		レスポンス（出力データ）の文字コードを設定
			response.setContentType("text/html;charset=UTF-8");
		
//		リクエスト（受信データ）の文字コードを設定
			request.setCharacterEncoding("UTF-8");
			
		
//			リクエストパラメータを取得

			
			String catName = request.getParameter("CATNAME");
//			(CATNAME)
			String catKind = request.getParameter("KIND");
//			(ID--Users_Info)
			String catBirth = request.getParameter("BIRTH");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			// String を LocalDate に変換
			LocalDate birthDate = LocalDate.parse(catBirth, formatter);
			// LocalDate を java.sql.Date に変換
			java.sql.Date sqlDate = java.sql.Date.valueOf(birthDate);
						
//			(Birth)
			int catWeight = Integer.parseInt(request.getParameter("WEIGHT"));
//			(Weight)
			int catGender = Integer.parseInt(request.getParameter("GENDER"));
//			(Gender)

//			画像ファイルの受け取り方
			Part filePart = request.getPart("IMAGE");
			InputStream fileContent = filePart.getInputStream();
			byte[] image = fileContent.readAllBytes();
			
//			
			String catComment = request.getParameter("COMMENT");
//			(COMMENT)	
			
			System.out.println(sqlDate);
			System.out.println(catName);
			
			
			
			
			
			
			
			//ユーザーデータ（UserInfoDto型）の作成
			UsersInfoDto dto = new UsersInfoDto();
			dto.setUserId( userId );
			dto.setUserName( userName );
			dto.setPassWord( userPass );
		
		
		
		
		
		
	}

}
