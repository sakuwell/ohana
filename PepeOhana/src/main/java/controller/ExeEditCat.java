package controller;

import java.io.IOException;
import java.sql.Timestamp;

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
//		(飼い主ID:userId)
		
		String catName	=request.getParameter("CATNAME");
//		(CATNAME)
		
		String kind	=request.getParameter("KIND");
//		(KIND)
		
		
		
		int gender = Integer.parseInt( request.getParameter("GENDER"));
//		(GENDER)
		
		float weight = Float.parseFloat(request.getParameter("WEIGHT"));
//		(WEIGHT)
				
		
		
		
		
		String comment 	=request.getParameter("COMMENT");
//		(MESSAGE)
		
//		private Timestamp 	up_Date;	//更新日
		
		//アンケートデータ（SurveyDto型）の作成
		CatsInfoDto dto = new CatsInfoDto();
		dto.setCatId(catId);
		dto.setUserId(userId);
		dto.setCatName(catName);
		dto.setKind( kind );
		dto.setBirth( birth );
		dto.setGender( gender );
		dto.setWeight( weight );
		
		dto.setComment( comment );
		dto.setUp_Date( new Timestamp(System.currentTimeMillis()) );   //現在時刻を更新時刻として設定
		
		
		
		
		
		
		
		
	}

}
