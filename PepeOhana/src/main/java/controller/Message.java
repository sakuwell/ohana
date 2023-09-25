package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UsersInfoDto;

/**
 * Servlet implementation class Message
 */
@WebServlet("/Message")
public class Message extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Message() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		response.setContentType("text/html;charset=UTF-8");
//		request.setCharacterEncoding("UTF-8");
//		
//		HttpSession session           = request.getSession();
//		UsersInfoDto userInfoOnSession = (UsersInfoDto)session.getAttribute("LOGIN_INFO");
//		
//		if (userInfoOnSession != null) {
//		
//		int catId = Integer.parseInt(request.getParameter("CATID")); //リクエストパラメータ（CATID)
//		int recieverId = Integer.parseInt(request.getParameter("RECIEVERID")); //リクエストパラメータ（RECIEVERID)
//		String recieverName = request.getParameter("RECIEVERNAME"); //リクエストパラメータ（RECIEVERID)
//		String catName = request.getParameter("CATNAME"); //リクエストパラメータ（RECIEVERID)
//		
//		request.setAttribute("catId",catId );
//		request.setAttribute("recieverId",recieverId );
//		request.setAttribute("recieverName",recieverName );
//		request.setAttribute("catName",catName );
//		
//		RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/message.jsp");
//		dispatch.forward(request, response);
//		
//		} else {
//			response.sendRedirect(request.getContextPath()+"/Login.jsp");
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session           = request.getSession();
		UsersInfoDto userInfoOnSession = (UsersInfoDto)session.getAttribute("LOGIN_INFO");
		
		if (userInfoOnSession != null) {
			
			String catId = request.getParameter("CATID"); //リクエストパラメータ（CATID)
			String recieverId = request.getParameter("USERID"); //リクエストパラメータ（RECIEVERID)
			String recieverName = request.getParameter("USERNAME"); //リクエストパラメータ（RECIEVERID)
			String catName = request.getParameter("CATNAME"); //リクエストパラメータ（RECIEVERID)
			String message = request.getParameter("MESSAGE"); //リクエストパラメータ（MESSAGE)
			
			System.out.println("パラメーターを取得");
			
			request.setAttribute("CATID",catId );
			request.setAttribute("RECIEVERID",recieverId );
			request.setAttribute("RECIEVERNAME",recieverName );
			request.setAttribute("CATNAME",catName );
			request.setAttribute("MESSAGE",message );
			
			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/message.jsp");
			dispatch.forward(request, response);
		
		} else {
			response.sendRedirect(request.getContextPath()+"/Login.jsp");
		}
	}

}
