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
 * Servlet implementation class EditUser
 */
@WebServlet("/EditUser")
public class EditUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session           = request.getSession();
		UsersInfoDto userInfoOnSession = (UsersInfoDto)session.getAttribute("LOGIN_INFO");
		
		if (userInfoOnSession != null) {
			
//			String id = request.getParameter("ID");
//			String userId   = request.getParameter("USERID");      
//			String passWord = request.getParameter("PASS");
			
//			SelectUserBL logic = new SelectUserBL();
//			
//			UsersInfoDto updateUser = logic.executeSelectUserInfo(Integer.parseInt(id)); 
//			
//			
//			
//			request.setAttribute("user" , updateUser );
			
			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/editUser.jsp");
			dispatch.forward(request, response);
			
		}else{
			response.sendRedirect("/WEB-INF/view/index.jsp");
			
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
		
	}


