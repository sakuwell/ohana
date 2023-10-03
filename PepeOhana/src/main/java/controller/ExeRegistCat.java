package controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.CatsInfoDto;
import model.RegistCatBL;
import model.UsersInfoDto;

/**----------------------------------------------------------------------*
 *Filename:ExeRegistCat.java
 *
 *Description:
 *	このクラスは、ネコ情報の新規登録機能を提供するためのものです。
 *	入力された内容をリクエストパラメータで抽出し、
 *	データをデータベースに登録し、マイページ画面へ遷移する
 *	登録が失敗した場合は、失敗時メッセージをリクエストスコープにセットし、登録画面へ戻る
 *	
 *Author:大久保
 *Creation Date:2023-09-19
 *
 *Copyright © 2023 KEG Sakura All rights reserved.
 *----------------------------------------------------------------------**/
/**
 * Servlet implementation class ExeRegistCat
 */
@WebServlet("/ExeRegistCat")
@MultipartConfig
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
		
		
		response.setContentType("text/html;charset=UTF-8");
		
//		リクエスト（受信データ）の文字コードを設定
		request.setCharacterEncoding("UTF-8");
			
		HttpSession session           = request.getSession();
		UsersInfoDto userInfoOnSession = (UsersInfoDto)session.getAttribute("LOGIN_INFO");
		
//		リクエストパラメータを取得

			
		String catName = request.getParameter("CATNAME");
//		(CATNAME)
		String catKindstr = request.getParameter("KIND");
		int catKind = Integer.parseInt(catKindstr);
//		(CATKIND)
			
		String catBirth = request.getParameter("BIRTH");
		if (catBirth == "yyyy/mm/dd") {
			catBirth = null;
		}
		java.sql.Date sqlDate = null; 
			
		if (catBirth != null) {
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    // String を LocalDate に変換
		    LocalDate birthDate = LocalDate.parse(catBirth, formatter);
		    // LocalDate を java.sql.Date に変換
		   sqlDate = java.sql.Date.valueOf(birthDate);
		} else {
			sqlDate = null;
		
		}
			
						
//		(Birth)
			
		String weightParameter = request.getParameter("WEIGHT");
		float catWeight = 0.0f; // デフォルト値を設定

		if (weightParameter != null && !weightParameter.isEmpty()) {
		    catWeight = Float.parseFloat(weightParameter);
		} 			
//		(Weight)
		
		int catGender = Integer.parseInt(request.getParameter("GENDER"));
//		(Gender)

			
		// 画像ファイルの受け取り
		Part filePart = request.getPart("IMAGE");
		byte[] catImage = null;

		if (filePart != null) {
		    InputStream fileContent = filePart.getInputStream();
		    catImage = fileContent.readAllBytes();
		}			
//		(Image)
		
		String catComment = request.getParameter("COMMENT");			
		int ownerId = userInfoOnSession.getID(); 
			
//		(COMMENT)	

			
		//ユーザーデータ（CatInfoDto型）の作成
		CatsInfoDto dto = new CatsInfoDto();
		dto.setOwnerId(ownerId);
		dto.setCatName( catName );
		dto.setKind( catKind );
		dto.setBirth( sqlDate );
		dto.setGender( catGender );
		dto.setWeight( catWeight );
		dto.setImage( catImage );
		dto.setComment( catComment );
		dto.setReg_Date( new Timestamp(System.currentTimeMillis()) ); 
		
		
		
//		データをDBに登録
		RegistCatBL logic = new RegistCatBL();
		boolean succesInsert = logic.exeInsertCatInfo(dto);
			
			
			
		if (succesInsert) {
//			DBに成功した場合、マイページを表示する
			response.sendRedirect("ExeMyPage");
		} else {
			// リクエストスコープにデータを設定
			request.setAttribute("message", "新規登録ができませんでした。やり直してください。");
			//トップページ画面へ転送
		    request.getRequestDispatcher("RegistCat").forward(request, response);
		}
	}	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		
//		レスポンス（出力データ）の文字コードを設定
		response.setContentType("text/html;charset=UTF-8");
		
//		リクエスト（受信データ）の文字コードを設定
		request.setCharacterEncoding("UTF-8");
			
		HttpSession session           = request.getSession();
		UsersInfoDto userInfoOnSession = (UsersInfoDto)session.getAttribute("LOGIN_INFO");
		
//		リクエストパラメータを取得

			
		String catName = request.getParameter("CATNAME");
//		(CATNAME)
		
		int catKind =Integer.parseInt(request.getParameter("KIND"));
//		(CATKIND)
			
		
			
		String catBirth = request.getParameter("BIRTH");	
		java.sql.Date sqlDate = null; 

		if (catBirth != null && !catBirth.isEmpty()) {
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    // String を LocalDate に変換
		    LocalDate birthDate = LocalDate.parse(catBirth, formatter);
		    // LocalDate を java.sql.Date に変換
		   sqlDate = java.sql.Date.valueOf(birthDate);
		} else {
			sqlDate = null;
		}
//			(Birth)
			
		String weightParameter = request.getParameter("WEIGHT");
		float catWeight = 0.0f; // デフォルト値を設定

		if (weightParameter != null && !weightParameter.isEmpty()) {
		    catWeight = Float.parseFloat(weightParameter);
		}
//		(Weight)
			
		int catGender = Integer.parseInt(request.getParameter("GENDER"));
//		(Gender)
			
		// 画像ファイルの受け取り
		Part filePart = request.getPart("IMAGE");
		byte[] catImage = null;

		if (filePart != null) {
		    InputStream fileContent = filePart.getInputStream();
		    catImage = fileContent.readAllBytes();
		}			
//		(Image)
			
		String catComment = request.getParameter("COMMENT");
//		(Comment)
			
			
		int loginId = userInfoOnSession.getID(); 	
			
			
		//ユーザーデータ（CatInfoDto型）の作成
		CatsInfoDto dto = new CatsInfoDto();
		dto.setOwnerId(loginId);
		dto.setCatName( catName );
		dto.setKind( catKind );
		dto.setBirth( sqlDate );
		dto.setGender( catGender );
		dto.setWeight( catWeight );
		dto.setImage( catImage );
		dto.setComment( catComment );
		dto.setReg_Date( new Timestamp(System.currentTimeMillis()) ); 
			
			
//		データをDBに登録
		RegistCatBL logic = new RegistCatBL();
		boolean succesInsert = logic.exeInsertCatInfo(dto);
			
			
			
		if (succesInsert) {
//			DBに成功した場合、マイページ画面を表示する
				
			response.sendRedirect("ExeMyPage");
		} else {
//			DBに失敗した場合、失敗時メッセージをリクエストスコープにセットし、登録画面へ戻る
			request.setAttribute("message", "登録に失敗しました。入力された内容をご確認ください。");
			request.getRequestDispatcher("/WEB-INF/view/registCat.jsp").forward(request,response);
		}
		
	}
		
		
		
}

