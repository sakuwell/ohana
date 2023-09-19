package controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MessagesDto;
import model.SendMessageBL;
import model.UsersInfoDto;
/**
 * Servlet implementation class SendMessage
 */
@WebServlet("/ExeSendMessage")
public class ExeSendMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExeSendMessage() {
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
//		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session           = request.getSession();
		UsersInfoDto userInfoOnSession = (UsersInfoDto)session.getAttribute("LOGIN_INFO");
		
		int senderId=userInfoOnSession.getID();
		
		String catId=request.getParameter("CATID");
		String recieverId=request.getParameter("RECIEVERID");
		String message=request.getParameter("COMMENT");
		
		MessagesDto dto = new MessagesDto();
		dto.setSenderId(senderId);
		dto.setRecieverId(Integer.parseInt(recieverId));
		dto.setCatId(Integer.parseInt(catId));
		dto.setMessage(message);
		dto.setSend_Date(new Timestamp(System.currentTimeMillis()));
		
		SendMessageBL logic = new SendMessageBL();
		boolean succesInsert = logic.executeInsertMessage(dto);
		
		if (succesInsert) {
			response.sendRedirect("ExeMyPage");
		} else {
			String recieverName = request.getParameter("RECIEVERNAME"); //リクエストパラメータ（RECIEVERID)
			String catName = request.getParameter("CATNAME"); //リクエストパラメータ（RECIEVERID)
			response.sendRedirect("Message?CATID="+catId+"&RECIEVERID="+recieverId+"&CATNAME="+catName+"&RECIEVERNAME="+recieverName);
			// リクエストスコープにデータを設定
			request.setAttribute("message", "ログインできませんでした。ユーザーIDかパスワードが間違っています。");
			//トップページ画面へ転送
	        request.getRequestDispatcher("Login.jsp").forward(request, response);
		}
					
//		doGet(request, response);
	}
	

}
