package attendance;

public class LetterConversion {
	public String conversionTime(String time) {
		//1文字の場合
		if(time.length() == 1) {
			//文字の先頭に0を付与
			String time1 = "0" + time;
			return time1;
		}
		return time;
	}
	
	/*
	/// <summary>
    /// 勤務実績入力画面の表示日付の文字変換
    /// </summary>
    /// <param name="date"></param>
    /// <returns>日付</returns>
    public DateTime ConversionDateLabel(string date)
    {
        //取得文字列の指定文字を削除
        string latter = date.Replace("年 ", "/").Replace("月 ", "/").Replace("日 ", "");

        //文字列の日付を作成
        string conversionDate = latter.Remove(10, 5);

        //DateTime型への変換
        DateTime dConversionDate = DateTime.Parse(conversionDate);

        return dConversionDate;
    }

    /// <summary>
    /// 有給の「日」の付与位置
    /// </summary>
    /// <param name="paidInfoList">有給情報</param>
    /// <returns></returns>
    public string[] conversionPaidHoliday(string[] paidInfoList)
    {
        string paidTotalNumber = paidInfoList[0];         //有給残数
        string paidDigestionNumber = paidInfoList[1];     //有給消化数
        string paidGrantNumberTwoyear = paidInfoList[2]; //有給消失予定数

        //有給残数の文字変換
        if (paidTotalNumber.Length == 1)
        {
            paidInfoList[0] = paidTotalNumber + "    日";
        }
        if (paidTotalNumber.Length == 2)
        {
            paidInfoList[0] = paidTotalNumber + "   日";
        }
        if (paidTotalNumber.Length == 3)
        {
            paidInfoList[0] = paidTotalNumber + "  日";
        }
        if (paidTotalNumber.Length == 4)
        {
            paidInfoList[0] = paidTotalNumber + " 日";
        }

        //有給消化数の文字変換
        if (paidDigestionNumber.Length == 1)
        {
            paidInfoList[1] = paidDigestionNumber + "    日";
        }
        if (paidDigestionNumber.Length == 2)
        {
            paidInfoList[1] = paidDigestionNumber + "   日";
        }
        if (paidDigestionNumber.Length == 3)
        {
            paidInfoList[1] = paidDigestionNumber + "  日";
        }
        if (paidDigestionNumber.Length == 4)
        {
            paidInfoList[1] = paidDigestionNumber + " 日";
        }

        //有給消失予定数の文字変換
        if (paidGrantNumberTwoyear.Length == 1)
        {
            paidInfoList[2] = paidGrantNumberTwoyear + "    日";
        }
        if (paidGrantNumberTwoyear.Length == 2)
        {
            paidInfoList[2] = paidGrantNumberTwoyear + "   日";
        }
        if (paidGrantNumberTwoyear.Length == 3)
        {
            paidInfoList[2] = paidGrantNumberTwoyear + "  日";
        }
        if (paidGrantNumberTwoyear.Length == 4)
        {
            paidInfoList[2] = paidGrantNumberTwoyear + " 日";
        }

        return paidInfoList;
    }

    public string[] conversionTime(string time)
    {
        string[] timeList = new string[2];

        string firstTime = time.Remove(2,6);

        //1文字目が"0"の場合
        if ("0" == firstTime.Substring(0,1))
        {
            firstTime = firstTime.Remove(0, 1);
        }

        string secoundTime = time.Remove(0, 3);
        secoundTime = secoundTime.Remove(2, 3);

        timeList[0] = firstTime;
        timeList[1] = secoundTime;

        return timeList;
    }*/
}
