package pub.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * 
 * 【业务说明】  bean实例工厂
 *
 * @工程   myNovel
 * @类名   BeanFactory 
 * 
 * @作者   韩伟其
 * @创建日期  2018年11月30日
 *
 * @修改记录 （修改时间、作者、原因）：
 * 众创小说
 */

public class BeanFactory {
	private static final Logger LOGGER = LoggerFactory.getLogger(BeanFactory.class);
	private final static Class<BeanFactory> LOCK = BeanFactory.class;
	private static ClassPathXmlApplicationContext ctx = null;
	public BeanFactory(){}
	public static ClassPathXmlApplicationContext getCtx(){
		synchronized (LOCK) {
			if(ctx==null){
				ctx=new ClassPathXmlApplicationContext("spring/applicationContext-dao.xml");
				LOGGER.debug("返回新new的CTX。。。");
				return ctx;
			}else{
				LOGGER.debug("返回已存在的CTX。。。");
				return ctx;
			}
		}
	}
	//根据bean名称获取实例
	public static <E> Object getBeanByName(String beanName){
		return getCtx().getBean(beanName);
	}
	//根据Bean的class获取实例
	public static <E> Object getBeanByClass(Class classType){
		return getCtx().getBean(classType);
	}
	public static void main(String[] args) {
		SqlSessionFactoryUtil ssfu=(SqlSessionFactoryUtil) BeanFactory.getBeanByClass(SqlSessionFactoryUtil.class);
		//USERMapper user=ssfu.getSqlSession().getMapper(USERMapper.class);
		//LOGGER.debug(user.selectByPrimaryKey("24325").toString());
		
		SqlSessionFactoryUtil ssfu2=(SqlSessionFactoryUtil) BeanFactory.getBeanByClass(SqlSessionFactoryUtil.class);
		//USERMapper user2=ssfu2.getSqlSession().getMapper(USERMapper.class);
		//LOGGER.debug(user2.selectByPrimaryKey("24325").toString());
		
	}
}
