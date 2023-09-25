package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DelMessageBL;
import model.MessagesDto;
/**
 * Servlet implementation class ExeDelMessage
 */
@WebServlet("/ExeDelMessage")
public class ExeDelMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExeDelMessage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String messageId = request.getParameter("MESSAGEID");
		
		MessagesDto dto = new MessagesDto();
		dto.setMessageId(Integer.parseInt(messageId));
		DelMessageBL logic = new DelMessageBL();
		boolean successDelete = logic.executeDeleteMessage(dto);
		if(successDelete) {
			response.sendRedirectDispatcher("ExeMyPage").forward(request, response);
			
		}else {
			request.setAttribute("message", "送信メッセージの削除に失敗しました");
			response.sendRedirectDispatcher("ExeMyPage").forward(request, response);
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
