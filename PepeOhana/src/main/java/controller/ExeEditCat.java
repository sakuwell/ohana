package controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.CatsInfoDto;
import model.EditCatOneBL;
import model.UpdateCatBL;
import model.UsersInfoDto;

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
		
		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	
		System.out.println("OK");

		response.setContentType("text/html;charset=UTF-8");
	
//	リクエスト（受信データ）の文字コードを設定
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session           = request.getSession();
		UsersInfoDto userInfoOnSession = (UsersInfoDto)session.getAttribute("LOGIN_INFO");
		

//		int userId = userInfoOnSession.getID(); 
//		
//		System.out.println(userId);
		
//		int catId =request.getParameter("CATID");
//		String catId = request.getParameter("ID"); 
//		int catId = Integer.parseInt(catId);
		
		String catIdStr = request.getParameter("ID");
		int catId = Integer.parseInt(catIdStr);
		
		
		System.out.println(catId);
		
		EditCatOneBL logic = new EditCatOneBL();		
		CatsInfoDto editCat = logic.exeSelectOneCatInfo(catId);
		
		if (editCat != null) {
		    request.setAttribute("cat", editCat);
		    RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/editCat.jsp");
		    dispatch.forward(request, response);
		} else {
			System.out.println("ネコ情報はありません");
		    // ネコちゃん情報が見つからない場合の処理を追加
		}
		
		
////		request.setAttribute("cat", editCat);
//		
//		RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/editCat.jsp");
////		RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/editCat.jsp");
//
//		dispatch.forward(request, response);

		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		
		response.setContentType("text/html;charset=UTF-8");
		
//		リクエスト（受信データ）の文字コードを設定
		request.setCharacterEncoding("UTF-8");
		
		
//		リクエストパラメータを取得
			String catIdStr = request.getParameter("ID");
			int catId = Integer.parseInt(catIdStr);
		
			int userId = Integer.parseInt( request.getParameter("USERID"));
//			(USERID)
			
			String catName			=request.getParameter("CATNAME");
//			(CATNAME)
			
			int kind =Integer.parseInt(request.getParameter("KIND"));
//			(KIND)
			
			String catBirth = request.getParameter("BIRTH");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			// String を LocalDate に変換
			LocalDate birthDate = LocalDate.parse(catBirth, formatter);
			// LocalDate を java.sql.Date に変換
			java.sql.Date sqlDate = java.sql.Date.valueOf(birthDate);
//			(BIRTH)
			
			int gender = Integer.parseInt(request.getParameter("GENDER"));
//			(GENDER)
			
			float weight = Float.parseFloat("WEIGHT");
			
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
			
			System.out.println(catId);
			System.out.println(userId);
			System.out.println(catName);
			System.out.println(kind);
			System.out.println(sqlDate);
			System.out.println(gender);
			System.out.println(weight);
			System.out.println(imageBytes);
			System.out.println(comment);
			
//			(comment)
			
			//パラメータをセット
			CatsInfoDto dto = new CatsInfoDto();
			dto.setCatId(catId);
			dto.setUserId(userId);
			dto.setCatName(catName);
			dto.setKind(kind);
			dto.setBirth(sqlDate);
			dto.setGender(gender);
			dto.setWeight(weight);
			dto.setImage(imageBytes);
			dto.setComment(comment);
			dto.setUp_Date(new Timestamp(System.currentTimeMillis())); 
			
			System.out.println(userId);
			
			//ネコちゃん情報をDBに登録
			UpdateCatBL logic = new UpdateCatBL();
			boolean succesUpdate = logic.exeUpdateCat(dto); 
	}
		
		
	}


