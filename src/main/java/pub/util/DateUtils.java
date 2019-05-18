package pub.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

/**
 * 
 * 【业务说明】日期工具类  
 *
 * @工程   myNovel
 * @类名   DateUtils 
 * 
 * @作者   韩伟其
 * @创建日期  2018年11月11日
 *
 * @修改记录 （修改时间、作者、原因）：
 * 众创小说
 */
public class DateUtils {
	/**
	 * 
	 * 功能逻辑  根据日期央视返回日期
	 *
	 * @方法名称  formateCalendar
	 * @作者  韩伟其
	 * @创建日期  2018年11月11日
	 * @返回值  String
	 *
	 * @修改记录 （修改时间、作者、原因）：
	 */
	public static String formateCalendar(Calendar calendar, String format) {
		if (calendar == null)
			return null;
		SimpleDateFormat dateFormate = new SimpleDateFormat(format);
		return dateFormate.format(calendar.getTime());
	}
	/**
	 * 
	 * 功能逻辑14位日期数字  
	 *
	 * @方法名称  getLongCurrDateTime14
	 * @作者  韩伟其
	 * @创建日期  2018年11月11日
	 * @返回值  Long
	 *
	 * @修改记录 （修改时间、作者、原因）：
	 */
	public static Long getLongCurrDateTime14() {
		return Long.valueOf(formateCalendar(Calendar.getInstance(), "yyyyMMddHHmmss"));
	}
	//数字转日期格式
	public static String ConvertDatetime14(Object data,int digits) {
		//先把数字时间转为时间戳，再转为时间格式。
		//例如：20191012121213数字时间转为时间戳=1570853533000，然后时间戳格式化日期。
		//sdf.format(date)它格式化的是时间戳。
		SimpleDateFormat sjcsdf=null,sdf=null;
		String time=null;
		if(digits==14) {
			sjcsdf=new SimpleDateFormat("yyyyMMddHHmmss");//时间戳数字格式
			sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		}else if(digits==8) {
			sjcsdf=new SimpleDateFormat("yyyyMMdd");
			sdf=new SimpleDateFormat("yyyy-MM-dd");
		}
		try {
			long sjc = sjcsdf.parse(data.toString()).getTime();//数字时间转为时间戳
			Date date =new Date(sjc);
            time=sdf.format(date);//时间戳转为日期格式。
		} catch (ParseException e) {
			e.printStackTrace();
		}
        return time;
	}
	@Test
	public void test() throws ParseException {
		System.out.println(DateUtils.ConvertDatetime14("20191012121213", 14));
	}
}
