package pub.util;

public class MapperUtils {

 private final static	SqlSessionFactoryUtil ssfu=(SqlSessionFactoryUtil) BeanFactory.getBeanByClass(SqlSessionFactoryUtil.class);
 /**
  * 
  * 功能逻辑根据class获取mapper  
  *
  * @方法名称  getMapper
  * @作者  韩伟其
  * @创建日期  2018年12月23日
  * @返回值  Object
  *
  * @修改记录 （修改时间、作者、原因）：
  */
	public static Object getMapper(Class c) {
		return  ssfu.getSqlSession().getMapper(c);
	}
}
