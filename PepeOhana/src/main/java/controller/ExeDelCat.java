package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UpdateDelCatBL;

/**
 * Servlet implementation class ExeDelCat
 */
@WebServlet("/ExeDelCat")
public class ExeDelCat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExeDelCat() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html;charset=UTF-8");
		
//		リクエスト（受信データ）の文字コードを設定
		request.setCharacterEncoding("UTF-8");
		
		System.out.println("doPostUTF-8の下");
		
//		パラメータ取得
		
		String catIdStr = request.getParameter("CATID");		
		System.out.println(catIdStr);		
		int catId = Integer.parseInt(catIdStr);
		
		//削除のためDELに１を登録
		UpdateDelCatBL logic = new UpdateDelCatBL();
		boolean succesUpdate = logic.exeUpdateDelCat(catId); 
		
		if (succesUpdate) {
		    // 更新に成功した場合Mypage画面へ転送
			response.sendRedirect("ExeMyPage");
		    
		} else {
		    // 更新に失敗した場合、editCat.jspへ戻す
		    request.setAttribute("message", "更新に失敗しました。入力された内容をご確認ください。");
//		    request.getRequestDispatcher("EditCat").forward(request, response);
		    request.getRequestDispatcher(request.getContextPath() + "/EditCat").forward(request, response);
		}
		
	}
		
	}

