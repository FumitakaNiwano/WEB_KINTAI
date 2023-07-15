package servlet.attendance;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import attendance.RegistrationAttendance;
import common.CommonBean.AttendanceInfoBean;
import common.CommonBean.MessageBean;

/**
 * Servlet implementation class sample
 */
@WebServlet("/AttendanceServlet")
public class AttendanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AttendanceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//JSPで使用するパラメータの設定
		String name = request.getParameter("message"); //テキストボックスの値を取得
		
		//出勤ボタン押下
		if(request.getParameter("attndance_submit") != null) { 
			//出勤登録
			RegistrationAttendance registrationAttendance = new RegistrationAttendance();
			
			try {
				registrationAttendance.commutingTimeRegistration();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//勤怠情報の取得
			AttendanceInfoBean attendanceInfoBean = AttendanceInfoBean.getInstance();
			
			//表示メッセージの取得
			MessageBean messageBean = MessageBean.getInstance(); 
			
			// 呼び出し元Jspからデータ受け取り
			request.setCharacterEncoding("UTF8");
			
			// 呼び出し先Jspに渡すデータセット
			request.setAttribute("msg", messageBean.getMessage());
			request.setAttribute("attendanceTime", attendanceInfoBean.getAttendanceTime());
		}
		
		//退勤ボタン押下
		if(request.getParameter("leaving_work_submit") != null) {
					
		}
		
		//ボタン押下
		if(request.getParameter("no") != null) {
					
		}
		
		// AttendanceView.jsp にページ遷移
		request.getRequestDispatcher("jsp/AttendanceView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
