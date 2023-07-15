package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBSelect_Attendance {
	
	public static class DTO{
		private String userId;
		private String year;
		private String month;
		private String day;
		private String date;
		private String attendanceTime; //出勤時間
		private String openingTime;
		private String leavingWorkTime;
		private String fixAttendanceTime;
		private String fixLeavingWorkTime;
		private String workRecord;
		private String restTime;
		private String overTime;
		private String latenessReason;
		private String latenessTime;
		private String leaveEarlyReason;
		private String leaveEarlyTime;
		private String holidayClassification;
		private String comment;
		private String approval;

		public void setUserId(String userId) {
			this.userId = userId;
		}
		public String getUserId() {
			return this.userId;
		}
		
		public void setYear(String year) {
			this.year = year;
		}
		public String getYear() {
			return this.year;
		}
		
		public void setMonth(String month) {
			this.month = month;
		}
		public String getMonth() {
			return this.month;
		}
		
		public void setDay(String day) {
			this.day = day;
		}
		public String getDay() {
			return this.day;
		}
		
		public void setDate(String date) {
			this.date = date;
		}
		public String getDate() {
			return this.date;
		}
		
		public void setAttendanceTime(String attendanceTime) {
			this.attendanceTime = attendanceTime;
		}
		public String getAttendanceTime() {
			return this.attendanceTime;
		}
		
		public void setOpeningTime(String openingTime) {
			this.openingTime = openingTime;
		}
		public String getOpeningTime() {
			return this.openingTime;
		}
		
		public void setLeavingWorkTime(String leavingWorkTime) {
			this.leavingWorkTime = leavingWorkTime;
		}
		public String getLeavingWorkTime() {
			return this.leavingWorkTime;
		}
		
		public void setFixAttendanceTime(String fixAttendanceTime) {
			this.fixAttendanceTime = fixAttendanceTime;
		}
		public String getFixAttendanceTime() {
			return this.fixAttendanceTime;
		}
		
		public void setFixLeavingWorkTime(String fixLeavingWorkTime) {
			this.fixLeavingWorkTime = fixLeavingWorkTime;
		}
		public String getFixLeavingWorkTime() {
			return this.fixLeavingWorkTime;
		}
		
		public void setWorkRecord(String workRecord) {
			this.workRecord = workRecord;
		}
		public String getWorkRecord() {
			return this.workRecord;
		}
		
		public void setRestTime(String restTime) {
			this.restTime = restTime;
		}
		public String getRestTime() {
			return this.restTime;
		}
		
		public void setOverTime(String overTime) {
			this.overTime = overTime;
		}
		public String getOverTime() {
			return this.overTime;
		}
		
		public void setLatenessReason(String latenessReason) {
			this.latenessReason = latenessReason;
		}
		public String getLatenessReason() {
			return this.latenessReason;
		}
		
		public void setLatenessTime(String latenessTime) {
			this.latenessTime = latenessTime;
		}
		public String getLatenessTime() {
			return this.latenessTime;
		}
		
		public void setLeaveEarlyReason(String leaveEarlyReason) {
			this.leaveEarlyReason = leaveEarlyReason;
		}
		public String getLeaveEarlyReason() {
			return this.leaveEarlyReason;
		}
		
		public void setLeaveEarlyTime(String leaveEarlyTime) {
			this.leaveEarlyTime = leaveEarlyTime;
		}
		public String getLeaveEarlyTime() {
			return this.leaveEarlyTime;
		}
		
		public void setHolidayClassification(String holidayClassification) {
			this.holidayClassification = holidayClassification;
		}
		public String getHolidayClassification() {
			return this.holidayClassification;
		}
		
		public void setComment(String comment) {
			this.comment = comment;
		}
		public String getComment() {
			return this.comment;
		}
		
		public void setApproval(String approval) {
			this.approval = approval;
		}
		public String getApprova() {
			return this.approval;
		}
	}
	
	/*
     * 勤怠情報を取得
     * 
     * @param loginUserId　ユーザID
     * @param date         日付
     * @return　取得結果
     */
    public static List<DTO> selectAttendance(String loginUserId, String date)
    {
    	Connection conn = null;
    	
    	List<DTO> dtoList = new ArrayList<DTO>();
    	
    	try {
    		conn = ConnectDataBase.connectDB();
    		
    		//最新データの取得
    		String sql = "SELECT * FROM attendance_info_managements WHERE user_id = ? and date = ?;";
    		
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setString(1,loginUserId);
    		ps.setString(2,date);
    		
    		// SQL文の実行
    		ResultSet rs = ps.executeQuery();
    		
    		while(rs.next()) {
    			DTO dto = new DTO();
    			dto.setAttendanceTime(rs.getString("attendance_time"));
    			
    			dtoList.add(dto);
    		}
    	} catch(SQLException e) {
    		e.printStackTrace();
    		System.out.println("SQLエラーが発生しました。");
    		
    	} finally {
    		try {
    			// データベース接続を閉じる
    			conn.close();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
    	return dtoList;
    }

	
	/*　
    /// <summary>
    /// 全勤怠情報を取得
    /// </summary>
    /// <param name="conn">DB情報</param>
    /// <param name="userId">ユーザID</param>
    /// <returns>取得結果</returns>
    public static SqlDataReader selectAllAttendance(SqlConnection conn, string userId)
    {
        //DBがopenしている場合
        if (conn.State == ConnectionState.Open)
        {
            conn.Close();
        }

        conn.Open();
        var cmd = conn.CreateCommand();

        cmd.CommandText = @"SELECT * FROM attendance_info_managements WHERE user_id = @UserId ORDER BY date DESC";
        cmd.Parameters.Add(new SqlParameter("@UserId", userId));
        SqlDataReader reader = cmd.ExecuteReader();

        return reader;
    }


    /// <summary>
    /// 当月の勤怠情報をDBから取得
    /// </summary>
    /// <param name="conn">DB情報</param>
    /// <param name="userId">ユーザID</param>
    /// <param name="year">年</param>
    /// <param name="month">月</param>
    /// <returns>取得結果</returns>
    public static SqlDataReader selectMonthAttendance(SqlConnection conn, string userId, string year, string month)
    {
        //DBがopenしている場合
        if (conn.State == ConnectionState.Open)
        {
            conn.Close();
        }

        conn.Open();
        var cmd = conn.CreateCommand();

        //SQL　重複データの取得
        cmd.CommandText = @"SELECT * FROM attendance_info_managements WHERE user_id = @userid AND year = @year AND month = @month";
        cmd.Parameters.Add(new SqlParameter("@userid", userId));
        cmd.Parameters.Add(new SqlParameter("@year", year));
        cmd.Parameters.Add(new SqlParameter("@month", month));
        SqlDataReader reader = cmd.ExecuteReader();

        return reader;
    }


    /// <summary>
    /// 年度の勤怠情報をDBから取得
    /// </summary>
    /// <param name="conn">DB情報</param>
    /// <param name="loginUserId">ユーザID</param>
    /// <param name="startDate">開始範囲</param>
    /// <param name="endDate">終了範囲</param>
    /// <returns>取得結果</returns>
    public static SqlDataReader selectYearAttendance(SqlConnection conn, string loginUserId, string startDate, string endDate)
    {
        //DBがopenしている場合
        if (conn.State == ConnectionState.Open)
        {
            conn.Close();
        }

        conn.Open();
        var cmd = conn.CreateCommand();

        //SQL　指定範囲でデータ取得
        cmd.CommandText = @"SELECT * FROM attendance_info_managements WHERE user_id = @userid AND Date BETWEEN @startDate AND @endDate";
        cmd.Parameters.Add(new SqlParameter("@userid", loginUserId));
        cmd.Parameters.Add(new SqlParameter("@startDate", startDate));
        cmd.Parameters.Add(new SqlParameter("@endDate", endDate));
        SqlDataReader reader = cmd.ExecuteReader();

        return reader;
    }

    /// <summary>
    /// 勤怠検索の結果取得
    /// </summary>
    /// <param name="conn">DB情報</param>
    /// <param name="loginUserId">ユーザID</param>
    /// <param name="year">年</param>
    /// <param name="month">月</param>
    /// <param name="day">日</param>
    /// <returns>検索結果</returns>
    public static SqlDataReader selectSpecifyAttendance(SqlConnection conn, string loginUserId, string year, string month, string day)
    {
        //DBがopenしている場合
        if (conn.State == ConnectionState.Open)
        {
            conn.Close();
        }

        conn.Open();
        var cmd = conn.CreateCommand();

        //年が入力されたとき
        if (year != "")
        {
            //月が入力されたとき
            if (month != "")
            {
                //日が入力されたとき
                if (day != "")
                {
                    //年、月、日での検索(月、日は曖昧検索)
                    cmd.CommandText = @"SELECT * FROM attendance_info_managements WHERE user_id = @UserId AND year = @Year AND month Like '%' + @Month + '%' AND day Like '%' + @Day + '%'";
                    cmd.Parameters.Add(new SqlParameter("@UserId", loginUserId));
                    cmd.Parameters.Add(new SqlParameter("@Year", year));
                    cmd.Parameters.Add(new SqlParameter("@Month", month));
                    cmd.Parameters.Add(new SqlParameter("@Day", day));
                }
                else
                {
                    //年、月での検索(月は曖昧検索)
                    cmd.CommandText = @"SELECT * FROM attendance_info_managements WHERE user_id = @UserId AND year = @Year AND month Like '%' + @Month + '%'";
                    cmd.Parameters.Add(new SqlParameter("@UserId", loginUserId));
                    cmd.Parameters.Add(new SqlParameter("@Year", year));
                    cmd.Parameters.Add(new SqlParameter("@Month", month));
                }
            }

            else if (day != "")
            {
                //年、日での検索(月は曖昧検索)
                cmd.CommandText = @"SELECT * FROM attendance_info_managements WHERE user_id = @UserId AND year = @Year AND day Like '%' + @Day + '%'";
                cmd.Parameters.Add(new SqlParameter("@UserId", loginUserId));
                cmd.Parameters.Add(new SqlParameter("@Year", year));
                cmd.Parameters.Add(new SqlParameter("@Day", day));
            }

            else
            {
                //年のみ検索
                cmd.CommandText = @"SELECT * FROM attendance_info_managements WHERE user_id = @UserId AND year Like @Year";
                cmd.Parameters.Add(new SqlParameter("@UserId", loginUserId));
                cmd.Parameters.Add(new SqlParameter("@Year", year));
            }
        }

        else if (month != "")
        {
            if (day != "")
            {
                //月、日での曖昧検索
                cmd.CommandText = @"SELECT * FROM attendance_info_managements WHERE user_id = @UserId AND month Like '%' + @Month + '%' AND day Like '%' + @Day + '%'";
                cmd.Parameters.Add(new SqlParameter("@UserId", loginUserId));
                cmd.Parameters.Add(new SqlParameter("@Month", month));
                cmd.Parameters.Add(new SqlParameter("@Day", day));
            }
            else
            {
                //月のみ曖昧検索
                cmd.CommandText = @"SELECT * FROM attendance_info_managements WHERE user_id = @UserId AND month Like '%' + @Month + '%'";
                cmd.Parameters.Add(new SqlParameter("@UserId", loginUserId));
                cmd.Parameters.Add(new SqlParameter("@Month", month));
            }
        }

        else if (day != "")
        {
            //日のみ曖昧検索
            cmd.CommandText = @"SELECT * FROM attendance_info_managements WHERE user_id = @UserId AND day Like '%' + @Day + '%'";
            cmd.Parameters.Add(new SqlParameter("@UserId", loginUserId));
            cmd.Parameters.Add(new SqlParameter("@Day", day));
        }

        SqlDataReader reader = cmd.ExecuteReader();

        return reader;
    }*/
}
