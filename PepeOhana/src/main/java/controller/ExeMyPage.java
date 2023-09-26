package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MyPageBL;
import model.MyPageDto;
import model.UsersInfoDto;

/**
 * Servlet implementation class ExeMyPage
 */
@WebServlet("/ExeMyPage")
public class ExeMyPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExeMyPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html;charset=UTF-8");
		
		HttpSession session           = request.getSession();
		UsersInfoDto userInfoOnSession = (UsersInfoDto)session.getAttribute("LOGIN_INFO");
		
		if (userInfoOnSession == null) {
//			未ログインならトップページへ
			response.sendRedirect("index.jsp");
			
		} else {
//			セッション情報から変数に代入
			int id = userInfoOnSession.getID();
			
			List<MyPageDto> myPageList = new ArrayList<MyPageDto>();
			List<MyPageDto> myPageList2 = new ArrayList<MyPageDto>();
			MyPageBL logic = new MyPageBL();
			myPageList  = logic.executeSelectMyPageLists(id).get(0);
			myPageList2  = logic.executeSelectMyPageLists(id).get(1);
			
	        // MyPageDaoから2つのリストを取得
	        List<MyPageDto> dtoList1 = myPageDao.doSelectMyInfo(userId).get(0); // 1つ目のリスト
	        List<MyPageDto> dtoList2 = myPageDao.doSelectMyInfo(userId).get(1); // 2つ目のリスト

	        // リクエストスコープにリストをセット
	        request.setAttribute("dtoList1", dtoList1);
	        request.setAttribute("dtoList2", dtoList2);
			request.setAttribute("MYPAGE",myPageList);
		
			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/mypage.jsp");
			dispatch.forward(request, response);
			
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
