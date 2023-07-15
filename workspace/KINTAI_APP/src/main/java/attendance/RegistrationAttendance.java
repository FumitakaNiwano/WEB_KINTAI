package attendance;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import attendance.Constant.AttendanceValue;
import common.CommonBean.AttendanceInfoBean;
import common.CommonBean.MessageBean;
import common.GetDate;
import database.DBInsert_Attendance;
import database.DBSelect_Attendance;
import database.DBSelect_Attendance.DTO;

public class RegistrationAttendance {

	/*出勤時刻の登録*/
	public void commutingTimeRegistration() throws Exception
	{
		//TODO ログイン画面作成後に削除 
		String loginUserId = "Niwa0312";
		
		
		//現在の日時を取得
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		
		//年の取得
		String year = GetDate.getYear(now); 
		
		//月の取得
		String month = GetDate.getMonth(now); 
		
		//日の取得
		String day = GetDate.getDay(now);
		
		//日付の取得
		String today = GetDate.getDate(now);
		
		//時刻の取得
		String inTime = GetDate.getTime(now);
		
		//始業時間の取得
		String startTime = AttendanceValue.WORK_START_TIME.getValue();
		
		//時間を型変換
		LocalTime dStartTime = LocalTime.parse(startTime);
		LocalTime lInTime = LocalTime.parse(inTime);
		
		
		//出勤時間の保持
		AttendanceInfoBean attendanceInfoBean = AttendanceInfoBean.getInstance();
		
		//表示メッセージの保持
		MessageBean messageBean = MessageBean.getInstance();
		
		//SQL ログインしたユーザの勤怠情報を取得
		List<DTO> rs = DBSelect_Attendance.selectAttendance(loginUserId, today);
		
		//本日の出勤登録がされていない場合
		if (rs.size() == 0) {
			//時刻の比較
			boolean localTime = dStartTime.isBefore(lInTime);
		
			//打刻時間が9時より前の場合
			if (!localTime){
				//勤怠登録
				DBInsert_Attendance.insertAttendance(loginUserId, year, month, day, today, inTime, startTime);
			
				//出勤時刻の保持
				attendanceInfoBean.setAttendanceTime(inTime);
				
				//メッセージの保持
				messageBean.setMessage("[" + today + "  " + inTime + "] に出勤登録をしました。");
			}
			//打刻時間が9時を超えた場合
			else{
				//遅刻時間の算出
				Reason reason = new Reason();
				String latenessTime = reason.calculationLatenessTime(inTime);
			
				//勤怠登録(遅刻時間含む)
				DBInsert_Attendance.insertAttendance(loginUserId, year, month, day, today, inTime, inTime, latenessTime);
			
				//出勤時刻の保持
				attendanceInfoBean.setAttendanceTime(inTime);
				
				//メッセージの保持
				messageBean.setMessage("[" + today + "  " + inTime + "] に出勤登録をしました。");
			}
		}
		//既に出勤登録がされている場合
		else{
			//出勤時刻の保持
			attendanceInfoBean.setAttendanceTime(rs.get(0).getAttendanceTime());
			
			//メッセージの保持
			messageBean.setMessage("既に出勤登録がされています。");
		}
	}

	/*
		/// <summary>
		/// 退勤時刻の登録
		/// </summary>
		public void LeavingWorkTimeRegistration()
		{
			//処理フラグ(0：登録未完了、1：登録完了)
			int processFlag;
			processFlag = 0;
			
			//現在の日時を取得
			DateTime now = DateTime.Now;
			String today = now.ToString("yyyy/MM/dd"); //日付の取得
			String outTime = now.ToString("HH:mm:ss"); //時刻の取得
			
			const String finishTime = "17:45:00";//終業時間
			DateTime dFinishTime = DateTime.Parse(finishTime);
			
			AttendanceJudgment judge = AttendanceJudgment.NotRegistration; //退勤済みの場合
			
			using (var conn = new SqlConnection(
					ConfigurationManager.ConnectionStrings["KINTAITask"].ConnectionString))
			{
				try
				{
					//SQL ログインしたユーザの最新のレコードを取得
					SqlDataReader reader = DBSelect_Attendance.selectTopAttendance(conn, loginUserId);
					
					if (reader.HasRows)
					{
						reader.Read();
						
						//本日、出勤済みかの判定
						if (String.Format("{0}", reader["date"]) != today)
						{
							judge = AttendanceJudgment.NotLeavingWork;
						}
						//既に退勤しているかの判定
						else if (String.Format("{0}", reader["leaving_work_time"]) == "")
						{
							//本日、出勤済みかの判定
							if (String.Format("{0}", reader["date"]) == today)
							{
								judge = AttendanceJudgment.Registration;
							}
						}
					}
					
					switch (judge) //退勤登録判定
					{
					case AttendanceJudgment.NotRegistration:
						this.leavingWorkResult = reader["leaving_work_time"].ToString();
						MessageBox.Show("既に退勤登録がされています。");
						break;
						
					case AttendanceJudgment.NotLeavingWork:
						MessageBox.Show("出勤登録がされていません。");
						break;
						
					case AttendanceJudgment.Registration:
						
						//時刻の比較
						int a = DateTime.Compare(now, dFinishTime);
						
						//打刻時間が17時45分より前の場合
						if (a == -1)
						{
							//早退時間の算出
							Reason reason = new Reason();
							String leaveEarlyTime = reason.CalculationLeaveEarlyTime(outTime);
							
							//退勤登録(早退時間含む)
							DBUpdate_Attendance.updataLeavingWorkTime(loginUserId, today, outTime, leaveEarlyTime);
							
							//退勤時刻の保持
							this.leavingWorkResult = outTime;
							MessageBox.Show("[ " + outTime + " ] に退勤登録をしました。");
							
							//勤怠ログの登録
							Log.setAttendanceLog(loginUserId, today, outTime, loginUserId, today); 
							
							//登録完了フラグを立てる
							processFlag = 1;
						}
						//打刻時間が17時45分より後の場合
						else
						{
							//退勤登録
							DBUpdate_Attendance.updataLeavingWorkTime(loginUserId, today, outTime);
							
							//退勤時刻の保持
							this.leavingWorkResult = outTime;
							MessageBox.Show("[ " + outTime + " ] に退勤登録をしました。");
							
							//勤怠ログの登録
							Log.setAttendanceLog(loginUserId, today, outTime, loginUserId, today);
							
							//登録完了フラグを立てる
							processFlag = 1;
						}
						break;
					}
				}
				finally
				{
					//DB接続終了
					conn.Close();
				}
			}
			
			//退勤登録が完了した場合
			if (processFlag == 1)
			{
				//本日の休憩時間登録
				RestTime restTimeRegistration = new RestTime();
				restTimeRegistration.TodayRestTimeRegistration();
				
				//本日の勤務実績登録
				WorkRecord workRecordRegistration = new WorkRecord();
				workRecordRegistration.TodayWorkRecordRegistration();
				
				//本日の残業時間登録
				OverTime overTimeRegistration = new OverTime();
				overTimeRegistration.TodayOvertimeRegistration();
			}
		}
		
		/// <summary>
		/// 前日の退勤時刻の登録
		/// </summary>
		public void PreviousLeavingWorkTimeRegistration()
		{
			//処理フラグ(0：登録未完了、1：登録完了)
			int processFlag;
			processFlag = 0;
			
			//現在の日時を取得
			DateTime now = DateTime.Now;
			String today = now.ToString("yyyy/MM/dd"); //日付の取得
			String outTime = now.ToString("HH:mm:ss"); //時刻の取得
			
			//前日の日付取得
			DateTime dToday = DateTime.Parse(today);
			DateTime getOneDayAgo = dToday.AddDays(-1);
			String searchDay = getOneDayAgo.ToString("yyyy/MM/dd");
			
			AttendanceJudgment judge = AttendanceJudgment.NotRegistration; //未退勤の場合
			
			using (var conn = new SqlConnection(
					ConfigurationManager.ConnectionStrings["KINTAITask"].ConnectionString))
			{
				try
				{
					//勤怠情報の取得
					SqlDataReader reader = DBSelect_Attendance.selectAttendance(conn, loginUserId, searchDay);
					
					if (reader.HasRows)
					{
						reader.Read();
						
						//前日に退勤済みかの判定
						if (String.Format("{0}", reader["leaving_work_time"]) != "")
						{
							judge = AttendanceJudgment.NotLeavingWork;
						}
						//前日に退勤しているかの判定
						else if (String.Format("{0}", reader["leaving_work_time"]) == "")
						{
							//前日に出勤済みかの判定
							if (String.Format("{0}", reader["date"]) == searchDay)
							{
								judge = AttendanceJudgment.Registration;
							}
						}
					}
					
					switch (judge) //退勤登録判定
					{
					case AttendanceJudgment.NotRegistration:
						MessageBox.Show("前日の出勤登録がされていません。");
						break;
						
					case AttendanceJudgment.NotLeavingWork:
						break;
						
					case AttendanceJudgment.Registration:
						
						//前日の退勤登録
						DBUpdate_Attendance.updataPreviousLeavingWorkTime(loginUserId, searchDay, outTime);
						
						MessageBox.Show("[ " + outTime + " ] に前日の退勤登録をしました。");
						
						//勤怠ログの登録
						Log.setAttendanceLog(loginUserId, today, outTime, loginUserId, searchDay);
						
						//登録完了フラグを立てる
						processFlag = 1;
						break;
					}
				}
				finally
				{
					//DB接続終了
					conn.Close();
				}
			}
			
			//前日の退勤登録が完了した場合
			if (processFlag == 1)
			{
				//本日の休憩時間登録
				RestTime restTimeRegistration = new RestTime();
				restTimeRegistration.PreviousRestTimeRegistration();
				
				//前日の勤務実績登録
				WorkRecord workRecordRegistration = new WorkRecord();
				workRecordRegistration.PreviousDayWorkRecordRegistration();
				
				//前日の残業時間登録
				OverTime overTimeRegistration = new OverTime();
				overTimeRegistration.PreviousDayOvertimeRegistration();
			}
		}
		
		/// <summary>
		/// 勤務実績画面からの勤怠登録
		/// </summary>
		/// <param name="loginUserId">ユーザID</param>
		/// <param name="date">日付</param>
		/// <param name="aFix">修正出勤時刻</param>
		/// <param name="lFix">修正退勤時刻</param>
		/// <param name="latenessReason">遅刻理由</param>
		/// <param name="latenessTime">遅刻時間</param>
		/// <param name="leaveEarlyReason">早退理由</param>
		/// <param name="leaveEarlyTime">早退時間</param>
		/// <param name="comment">コメント</param>
		/// <param name="holiday">休日区分</param>
		/// <returns></returns>
		public int ServiceRecordRegistration(String loginUserId, String date, String aFix, String lFix, String latenessReason, String latenessTime, String leaveEarlyReason, String leaveEarlyTime, String comment, String holiday)
		{
			//勤怠入力画面タイトルの年月日取得
			GetDate getDate = new GetDate();
			String[] dateList = getDate.GetAcquisitionTitle(date);
			
			//年月日の取得
			String year = dateList[0];
			String month = dateList[1];
			String day = dateList[2];
			
			//勤務実績入力画面の表示日付を取得
			LetterConversion letterConversion = new LetterConversion();
			String conversionDate = letterConversion.ConversionDateLabel(date).ToString("yyyy/MM/dd");
			
			//勤怠登録
			DBInsert_Attendance.insertServiceRecord(loginUserId, year, month, day, conversionDate, aFix, lFix, latenessReason, latenessTime, leaveEarlyReason, leaveEarlyTime, comment, holiday);
			
			MessageBox.Show("修正内容を保存しました");
			
			//勤怠ログの登録
			Log.setAttendanceLog(loginUserId, comment, loginUserId, conversionDate);
			
			return 1;
		}
	
		/// <summary>
		/// 有給登録
		/// </summary>
		/// <param name="date"></param>
		/// <param name="comment"></param>
		/// <param name="holiday"></param>
		/// <returns>登録完了フラグ</returns>
		public int HolidayRegistration(String date, String comment, String holiday)
		{
			//コメントが入力されていない場合
			if(comment == "")
			{
				MessageBox.Show("コメントを入力してください。");
				return 0;
			}
			else
			{
				//時間(出勤、退勤、実働、残業、休憩)の設定
				const String attendanceTime = "09:00:00";
				const String leavingTime = "17:45:00";
				const String zeroTime = "00:00";
				const String workRecord = "08:00";
				
				//勤怠入力画面タイトルの年月日取得
				GetDate getDate = new GetDate();
				String[] dateList = getDate.GetAcquisitionTitle(date);
				
				//年月日の取得
				String year = dateList[0];
				String month = dateList[1];
				String day = dateList[2];
				
				//勤務実績入力画面の表示日付を取得
				LetterConversion letterConversion = new LetterConversion();
				String conversionDate = letterConversion.ConversionDateLabel(date).ToString("yyyy/MM/dd");
				
				using (var conn = new SqlConnection(
						ConfigurationManager.ConnectionStrings["KINTAITask"].ConnectionString))
				{
					try
					{
						SqlDataReader reader = DBSelect_Attendance.selectAttendance(conn, loginUserId, conversionDate);
						
						//データが存在する場合
						if (reader.HasRows)
						{
							//有給の更新
							DBUpdate_Attendance.updataHoliday(loginUserId, year, month, day, conversionDate, attendanceTime, leavingTime, workRecord, zeroTime, comment, holiday);
							MessageBox.Show("修正内容を保存しました。");
							
							return 2;
						}
						else
						{
							//有給の登録
							DBInsert_Attendance.insertHoliday(loginUserId, year, month, day, conversionDate, attendanceTime, leavingTime, workRecord, zeroTime, comment, holiday);
							
							MessageBox.Show("有給を登録しました。");
							
							return 2;
						}
					}
					finally
					{
						//DB接続終了
						conn.Close();
					}
				}
			}
		}*/
}