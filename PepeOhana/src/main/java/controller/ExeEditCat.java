package controller;

import java.io.ByteArrayOutputStream;
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
import javax.servlet.http.Part;

import model.CatsInfoDto;
import model.UpdateCatBL;

/**----------------------------------------------------------------------*
 *Filename:ExeEditCat.java
 *
 *Description:
 *	このクラスは、ネコ情報の編集機能を提供するためのものです。
 *	リクエストパラメータで所得してきた内容に、
 *	対象のネコ情報の登録内容を更新してマイページ画面に遷移します。
 *	更新に失敗した場合は、失敗時メッセージをリクエストスコープにセットしネコ情報編集画面へ再度遷移します
 *	
 *
 *Author:大久保
 *Creation Date:2023-09-26
 *
 *Copyright © 2023 KEG Sakura All rights reserved.
 *----------------------------------------------------------------------**/
/**
 * Servlet implementation class ExeEditCat
 */
@WebServlet("/ExeEditCat")
@MultipartConfig
public class ExeEditCat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExeEditCat() {
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
		
		//レスポンス(送信データ)の文字コードを設定
		response.setContentType("text/html;charset=UTF-8");
		
//		リクエスト（受信データ）の文字コードを設定
		request.setCharacterEncoding("UTF-8");
		
		
//		リクエストパラメータを取得
		String catIdStr = request.getParameter("CATID");
			
			
		int catId = Integer.parseInt(catIdStr);
			
		String ownerIdStr = request.getParameter("OWNERID");
		int ownerId = Integer.parseInt(ownerIdStr);
//		(USERID)
			
		String catName			=request.getParameter("CATNAME");
//		(CATNAME)
			
		int kind =Integer.parseInt(request.getParameter("KIND"));
//		(KIND)
			
		String catBirth = request.getParameter("BIRTH");
		java.sql.Date sqlDate = null;
			if(catBirth != null && !catBirth.isEmpty()) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				// String を LocalDate に変換
				LocalDate birthDate = LocalDate.parse(catBirth, formatter);
				// LocalDate を java.sql.Date に変換
				sqlDate = java.sql.Date.valueOf(birthDate);
			}else {
				 sqlDate=null;
			}
//		(BIRTH)
			
		int gender = Integer.parseInt(request.getParameter("GENDER"));
//		(GENDER)
			
		float weight = Float.parseFloat(request.getParameter("WEIGHT"));
//		(WEIGHT)
			
		// 1. クライアントから送信された画像ファイルを取得
		Part filePart = request.getPart("IMAGE"); // IMAGEはフォームのinput要素のname属性
		InputStream inputStream = filePart.getInputStream();
			
		// 2. 画像ファイルをバイト配列に変換
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int bytesRead;
		while ((bytesRead = inputStream.read(buffer)) != -1) {
		    outputStream.write(buffer, 0, bytesRead);
		}
		byte[] imageBytes = outputStream.toByteArray();
			
		String comment	=request.getParameter("COMMENT");	
//		(comment)
			
		//パラメータをセット
		CatsInfoDto dto = new CatsInfoDto();
		dto.setCatId(catId);
		dto.setOwnerId(ownerId);
		dto.setCatName(catName);
		dto.setKind(kind);
		dto.setBirth(sqlDate);
		dto.setGender(gender);
		dto.setWeight(weight);
		dto.setImage(imageBytes);
		dto.setComment(comment);
		dto.setUp_Date(new Timestamp(System.currentTimeMillis())); 
			
			
		//ネコちゃん情報をDBに登録
		UpdateCatBL logic = new UpdateCatBL();
		boolean succesUpdate = logic.exeUpdateCat(dto); 
			

		if (succesUpdate) {
			// 更新に成功した場合Mypage画面へ転送
			response.sendRedirect("ExeMyPage");
	    
		} else {
			// 更新に失敗した場合、editCat.jspへ戻す
			request.setAttribute("message", "更新に失敗しました。入力された内容をご確認ください。");
			request.getRequestDispatcher(request.getContextPath() + "/ExeEditCat").forward(request, response);
		}
		
	}
}

