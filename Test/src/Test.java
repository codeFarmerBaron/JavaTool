import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import baron.util.date.DateTool;

public class Test {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		String format="yyyy-MM-dd hh:mm:ss";
		String format1="yyyy-MM-dd HH:mm:ss";
		LocalDateTime l=DateTool.toLocalDateTime(System.currentTimeMillis());
		System.out.println(DateTool.toDateStr(System.currentTimeMillis(), DateTool.FORMAT_DATETIME_STR));
	}

}
