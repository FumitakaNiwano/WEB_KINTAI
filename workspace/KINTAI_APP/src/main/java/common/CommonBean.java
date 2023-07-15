package common;

public class CommonBean {
	
	public static class AttendanceInfoBean {
		

		private static final AttendanceInfoBean INSTANCE = new AttendanceInfoBean();
		
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
		
		/**
	     * コンストラクタ
	     */
		public AttendanceInfoBean() {};
		
		public static AttendanceInfoBean getInstance() {
			return INSTANCE;
		}

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
	
	public static class MessageBean {
		private static final MessageBean INSTANCE = new MessageBean();
		private String message;
		
		/**
	     * コンストラクタ
	     */
		public MessageBean() {};
		
		public static MessageBean getInstance() {
			return INSTANCE;
		}

		public void setMessage(String userId) {
			this.message = userId;
		}
		public String getMessage() {
			return this.message;
		}
	}
}
