package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CatsInfoDto;
import model.ShowCatInfoBL;

/**
 * Servlet implementation class ShowCatInfo
 */
@WebServlet("/ExeShowCatInfo")
public class ExeShowCatInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExeShowCatInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//レスポンス（出力データ）の文字コードを設定
		response.setContentType("text/html;charset=UTF-8");
		
		String catId = request.getParameter("CATID"); //リクエストパラメータ（CATID)
		
		//「cats_info」テーブルから取得したIDと合致するネコ情報を抽出
		ShowCatInfoBL logic = new ShowCatInfoBL();
		CatsInfoDto ShowCatInfo = logic.executeSelectShowCatInfo(Integer.parseInt(catId));
		
		//ネコ情報の抽出成功/失敗に応じて表示させる画面を切り替える
		if (ShowCatInfo.getCatName() != null) {
			//ネコ情報の抽出成功:リクエストスコープにネコ情報をセット
			request.setAttribute("showCatInfo",ShowCatInfo);
			//showCatInfo.jspを表示
			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/showCatInfo.jsp");
			dispatch.forward(request, response);

		} else {
			//失敗時:
			// リクエストスコープにデータを設定
			request.setAttribute("message", "ご指定のIDのねこが見つかりませんでした");
			
			RequestDispatcher dispatch = request.getRequestDispatcher("index.jsp");
			dispatch.forward(request, response);
			//トップページ画面へ転送
//	        request.getRequestDispatcher("Login.jsp").forward(request, response);
		}
		

		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
