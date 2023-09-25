package controller;

import java.io.IOException;
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

import model.CatsInfoDto;
import model.EditCatOneBL;
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
			
			String kind =request.getParameter("KIND");
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

			
			
			
			
			
			
			
			String message 		=request.getParameter("MESSAGE");
//			(MESSAGE)
			String[] foods 		=request.getParameterValues("FOOD");
			
			String food =String.join(",", foods);
			
			//パラメータをセット
			ps.setInt(1, dto.getUserId()); //第1パラメータ：更新データ（飼い主ID）
			ps.setString(2, dto.getCatName()); //第2パラメータ：更新データ（ネコ名）
			ps.setString(3, dto.getKind()); //第3パラメータ：更新データ（種類）
			ps.setDate(4, dto.getBirth()); //第4パラメータ：更新データ（誕生日）
			ps.setInt(5, dto.getGender()); //第5パラメータ：更新データ（性別）
			ps.setFloat(6, dto.getWeight()); //第6パラメータ：更新データ（体重）
			ps.setBytes(7, dto.getImage()); //第7パラメータ：更新データ（写真）
			ps.setString(8, dto.getComment()); //第8パラメータ：更新データ（コメント）
			ps.setTimestamp(9, dto.getUp_Date());//第9パラメータ：更新データ（更新日）
			ps.setInt(10, dto.getCatId());//第10パラメータ：更新データ（CATID）

			//アンケートデータ（SurveyDto型）の作成
			SurveyDto dto = new SurveyDto();
			dto.setId(id);
			dto.setName( name );
			dto.setAge( age );
			dto.setSex( sex );
			dto.setSatisfactionLevel( satisfactionLevel );
			dto.setMessage( message );
			dto.setTime( new Timestamp(System.currentTimeMillis()) );   //現在時刻を更新時刻として設定
			dto.setFood( food );  
			
			
			
			
			
//		アンケートデータをDBに登録
			UpdateOneBL logic = new UpdateOneBL();
			boolean succesInsert = logic.executeUpdateSurvey(dto);
			
			//DB操作の成功/失敗に応じて表示させる画面を振り分ける
			
			if (succesInsert) {
//				DBに成功した場合、回答完了画面(finish.html)を表示する
				response.sendRedirect("htmls/finish.html");
			} else {
//				DBに失敗した場合、エラー画面(erro.html)を表示する
				response.sendRedirect("htmls/error.html");
			}
					
	}
		
		
		
		
		
		
	}

}
