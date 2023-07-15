package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBInsert_Attendance {
	
	// <summary>
    /// 勤怠登録
    /// </summary>
    /// <param name="loginUserId">ユーザID</param>
    /// <param name="year">年</param>
    /// <param name="month">月</param>
    /// <param name="day">日</param>
    /// <param name="today">日付</param>
    /// <param name="inTime">打刻時刻</param>
    /// <param name="startTime">業務開始時間</param>
    /// <param name="latenessTime">遅刻時間</param>
    public static void insertAttendance(String loginUserId, String year, String month, String day, String today, String inTime, String startTime, String latenessTime)
    {
    	Connection conn = null;
    	
    	try {
    		conn = ConnectDataBase.connectDB();
    		
    		//勤怠登録
    		String sql = "INSERT INTO attendance_info_managements (user_id,year,month,day,date,attendance_time,opening_time,fix_attendance_time,lateness_time,approval) VALUE (?,?,?,?,?,?,?,?,?,?);";
    		
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setString(1,loginUserId);
    		ps.setString(2,year);
    		ps.setString(3,month);
    		ps.setString(4,day);
    		ps.setString(5,today);
    		ps.setString(6,inTime);
    		ps.setString(7,startTime);
    		ps.setString(8,inTime);
    		ps.setString(9,latenessTime);
    		ps.setString(10,"申請");
    		
    		// SQL文の実行
    		ps.executeUpdate();
    		
    	} catch(Exception e) {
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
    }

    /// <summary>
    /// 上記メソッドのオーバーロード
    /// </summary>
    /// <param name="loginUserId">ユーザID</param>
    /// <param name="year">年</param>
    /// <param name="month">月</param>
    /// <param name="day">日</param>
    /// <param name="today">日付</param>
    /// <param name="inTime">打刻時刻</param>
    /// <param name="startTime">業務開始時間</param>
    public static void insertAttendance(String loginUserId, String year, String month, String day, String today, String inTime, String startTime)
    {
    	String latenessTime = ""; //遅刻時間

        insertAttendance(loginUserId, year, month, day, today, inTime, startTime, latenessTime);
    }


    /*
    /// <summary>
    /// 勤怠登録(勤務実績画面から)
    /// </summary>
    /// <param name="loginUserId">ユーザID</param>
    /// <param name="year">年</param>
    /// <param name="month">月</param>
    /// <param name="day">日</param>
    /// <param name="date">日付</param>
    /// <param name="aFix">修正出勤時刻</param>
    /// <param name="lFix">修正退勤時刻</param>
    /// <param name="latenessReason">遅刻理由</param>
    /// <param name="latenessTime">遅刻時間</param>
    /// <param name="leaveEarlyReason">早退理由</param>
    /// <param name="leaveEarlyTime">早退時間</param>
    /// <param name="comment">コメント</param>
    /// <param name="holiday">休日区分</param>
    public static void insertServiceRecord(string loginUserId, string year, string month, string day, string date, string aFix, string lFix, string latenessReason, 
                                           string latenessTime, string leaveEarlyReason, string leaveEarlyTime, string comment, string holiday)
    {
        using (var conn = new SqlConnection(
        ConfigurationManager.ConnectionStrings["KINTAITask"].ConnectionString))
        {
            conn.Open();
            var cmd = conn.CreateCommand();

            cmd.CommandText = @"INSERT INTO attendance_info_managements (user_id,year,month,day,date,fix_attendance_time,fix_leaving_work_time,lateness_reason,lateness_time,leave_early_reason,leave_early_time,comment,holiday_classification,approval) 
                                                                      VALUES(@UserId,@Year,@Month,@Day,@Date,@FixAttendanceTime,@FixLeavingWorkTime,@LatenessReason,@LatenessTime,@LeaveEarlyReason,@LeaveEarlyTime,@Comment,@HolidayClassification,@Approval)";
            cmd.Parameters.Add(new SqlParameter("@UserId", loginUserId));
            cmd.Parameters.Add(new SqlParameter("@Year", year));
            cmd.Parameters.Add(new SqlParameter("@Month", month));
            cmd.Parameters.Add(new SqlParameter("@Day", day));
            cmd.Parameters.Add(new SqlParameter("@Date", date));
            cmd.Parameters.Add(new SqlParameter("@FixAttendanceTime", aFix));
            cmd.Parameters.Add(new SqlParameter("@FixLeavingWorkTime", lFix));
            cmd.Parameters.Add(new SqlParameter("@LatenessReason", latenessReason));
            cmd.Parameters.Add(new SqlParameter("@LatenessTime", latenessTime));
            cmd.Parameters.Add(new SqlParameter("@LeaveEarlyReason", leaveEarlyReason));
            cmd.Parameters.Add(new SqlParameter("@LeaveEarlyTime", leaveEarlyTime));
            cmd.Parameters.Add(new SqlParameter("@Comment", comment));
            cmd.Parameters.Add(new SqlParameter("@HolidayClassification", holiday));
            cmd.Parameters.Add(new SqlParameter("@Approval", "申請"));

            cmd.ExecuteNonQuery(); //DBに格納

            conn.Close(); //DB接続終了
        }
    }


    /// <summary>
    /// 有給登録
    /// </summary>
    /// <param name="loginUserId">ユーザID</param>
    /// <param name="year">年</param>
    /// <param name="month">月</param>
    /// <param name="day">日</param>
    /// <param name="date">日付</param>
    /// <param name="attendanceTime">出勤時刻</param>
    /// <param name="leavingTime">退勤時刻</param>
    /// <param name="workRecord">勤務実績</param>
    /// <param name="zeroTime">休憩、残業時間</param>
    /// <param name="comment">コメント</param>
    /// <param name="holiday">休日区分</param>
    public static void insertHoliday(string loginUserId, string year, string month, string day, string date, string attendanceTime, string leavingTime, string workRecord,
                                     string zeroTime, string comment, string holiday)
    {
        using (var conn = new SqlConnection(
        ConfigurationManager.ConnectionStrings["KINTAITask"].ConnectionString))
        {
            conn.Open();
            var cmd = conn.CreateCommand();

            cmd.CommandText = @"INSERT INTO attendance_info_managements (user_id,year,month,day,date,attendance_time,opening_time,leaving_work_time,work_record,rest_time,over_time,holiday_classification,comment,approval) 
                                                              VALUES(@UserId,@Year,@Month,@Day,@Date,@AttendanceTime,@OpeningTime,@LeavingTime,@WorkRecord,@RestTime,@OverTime,@Holiday,@Comment,@Approval)";
            cmd.Parameters.Add(new SqlParameter("@UserId", loginUserId));
            cmd.Parameters.Add(new SqlParameter("@Year", year));
            cmd.Parameters.Add(new SqlParameter("@Month", month));
            cmd.Parameters.Add(new SqlParameter("@Day", day));
            cmd.Parameters.Add(new SqlParameter("@Date", date));
            cmd.Parameters.Add(new SqlParameter("@AttendanceTime", attendanceTime));
            cmd.Parameters.Add(new SqlParameter("@OpeningTime", attendanceTime));
            cmd.Parameters.Add(new SqlParameter("@LeavingTime", leavingTime));
            cmd.Parameters.Add(new SqlParameter("@WorkRecord", workRecord));
            cmd.Parameters.Add(new SqlParameter("@RestTime", zeroTime));
            cmd.Parameters.Add(new SqlParameter("@OverTime", zeroTime));
            cmd.Parameters.Add(new SqlParameter("@Holiday", holiday));
            cmd.Parameters.Add(new SqlParameter("@Comment", comment));
            cmd.Parameters.Add(new SqlParameter("@Approval", "申請"));

            cmd.ExecuteNonQuery(); //DBに格納

            conn.Close(); //DB接続終了
        }
    }*/
}
