package pub.util;
import org.apache.commons.beanutils.ConvertUtils;
public class ConvertUtil {

	public static Integer createInteger(Object obj) {
		return (Integer) ConvertUtils.convert(obj, Integer.class);
	}
	public static Long createlong(Object obj) {
		return (Long) ConvertUtils.convert(obj, Long.class);
	}
	public static String createString(Object obj) {
		return ConvertUtils.convert(obj);
	}
}
