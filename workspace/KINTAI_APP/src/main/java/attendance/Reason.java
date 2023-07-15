package attendance;

import java.time.LocalTime;
import java.util.Date;

import attendance.Constant.AttendanceValue;

/*
 * 事由(遅刻・早退)クラス
 */
public class Reason {
	
	//グローバル変数----------------------------------------------------------------

    //private string loginUserId = LoginInfo.UserID; //保持したユーザIDの取得

    //---------------------------------------------------------------------------------

    /// <summary>
    /// 遅刻時間の算出
    /// </summary>
    /// <param name="attendanceTime">出勤時刻</param>
    /// <returns>遅刻時間</returns>
    public String calculationLatenessTime(String attendanceTime) throws Exception
    {
    	LocalTime dStartTime = LocalTime.parse(AttendanceValue.WORK_START_TIME.getValue());
    	LocalTime dInTime = LocalTime.parse(attendanceTime); //打刻時間
    	
        Date startTime = Constant.TIME_FORMAT.parse(AttendanceValue.WORK_START_TIME.getValue());
        Date inTime = Constant.TIME_FORMAT.parse(attendanceTime);
        
        //遅刻時間の算出
        long difference = inTime.getTime() - startTime.getTime();
        String hours = Long.toString(difference / (60 * 60 * 1000) % 24);   //時
        String minutes = Long.toString(difference / (60 * 1000) % 60);      //分
        
        //文字を2文字に設定
        LetterConversion letterConversion = new LetterConversion();
        String sHours = letterConversion.conversionTime(hours);
        String sMinutes = letterConversion.conversionTime(minutes);
        
        String latenessTime = sHours + ":" + sMinutes;
        return latenessTime;
    }

    /*
    /// <summary>
    /// 早退時間の算出
    /// </summary>
    /// <param name="leavingWorkTime">退勤時間</param>
    /// <returns>早退時間</returns>
    public string CalculationLeaveEarlyTime(string leavingWorkTime)
    {
        const string finishTime = "17:45:00";//始業時間
        DateTime dFinishTime = DateTime.Parse(finishTime);

        DateTime dLeavingWorkTime = DateTime.Parse(leavingWorkTime); //打刻時間

        //早退時間の算出
        string leaveEarlyTime = (dFinishTime - dLeavingWorkTime).ToString().Substring(0, 5);
        return leaveEarlyTime;
    }

    /// <summary>
    /// 遅刻・早退回数の取得
    /// </summary>
    /// <returns>遅刻・早退回数</returns>
    public int[] GetNumberOfReasons()
    {
        const string index = "00:01:00";//遅刻・早退指標
        DateTime dIndex = DateTime.Parse(index);

        DateTime now = DateTime.Now;
        string year = now.ToString("yyyy"); //年の取得
        string month = now.ToString("MM");//月の取得

        
        int[] cntReason = new int[2]; //遅刻・早退回数の配列定義
        cntReason[0] = 0; //遅刻回数の設定
        cntReason[1] = 0; //早退回数の設定

        using (var conn = new SqlConnection(
        ConfigurationManager.ConnectionStrings["KINTAITask"].ConnectionString))
        {
            try
            {
                //勤怠情報の取得
                SqlDataReader reader = DBSelect_Attendance.selectMonthAttendance(conn, loginUserId, year, month);

                if (reader.HasRows)
                {
                    while (reader.Read())
                    {
                        //データの取得
                        var latenessTime = reader["lateness_time"].ToString();         //遅刻時間
                        var leaveEarlyTime = reader["leave_early_time"].ToString();    //早退時間
                        var latenessReason = reader["lateness_reason"].ToString();     //遅刻理由
                        var leaveEarlyReason = reader["leave_early_reason"].ToString(); //早退理由

                        //遅刻時間がnullではない場合
                        if (latenessTime != "")
                        {
                            DateTime dAttendanceTime = DateTime.Parse(latenessTime);

                            //遅刻時間が登録されている場合かつ遅刻理由が未入力の場合
                            if (dAttendanceTime >= dIndex && latenessReason == "")
                            {
                                //遅刻回数の加算
                                cntReason[0] += 1;
                            }
                        }
                        //早退時間がnullではない場合
                        if (leaveEarlyTime != "")
                        {
                            DateTime dLeaveEarlyTime = DateTime.Parse(leaveEarlyTime);

                            //早退時間が登録されている場合かつ早退理由が未入力の場合
                            if (dLeaveEarlyTime >= dIndex && leaveEarlyReason == "")
                            {
                                //早退回数の加算
                                cntReason[1] += 1;
                            }
                        }
                    }

                    //遅刻・早退回数を返す
                    return cntReason;
                }
                else
                {
                    //遅刻・早退回数(0回)を返す
                    return cntReason;
                }
            }
            finally
            {
                //DB接続終了
                conn.Close();
            }
        }
    }*/
}
