package pub.util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SqlSessionFactoryUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(SqlSessionFactoryUtil.class);
	private static SqlSessionTemplate sqlSessionTemplate;
	private static SqlSessionFactory sqlSessionFactory;
	private static SqlSession sqlSession;
	private final static Class<SqlSessionFactoryUtil> LOCK = SqlSessionFactoryUtil.class;
	public SqlSessionFactoryUtil(){}
	public static SqlSessionTemplate  getSqlSession() {
		synchronized (LOCK) {
			if(sqlSessionTemplate==null){
				sqlSessionTemplate=getSqlSessionTemplate();
				//LOGGER.debug("返回新的sqlSessionTemplate");
				return getSqlSessionTemplate();
			}else{
				//LOGGER.debug("返回已存在的sqlSessionTemplate");
				return sqlSessionTemplate;
			}
		}
	}
	

	public static SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}
	public static void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		SqlSessionFactoryUtil.sqlSessionTemplate = sqlSessionTemplate;
	}

	public static void setSqlSession(SqlSession sqlSession) {
		SqlSessionFactoryUtil.sqlSession = sqlSession;
	}

	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public static void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		SqlSessionFactoryUtil.sqlSessionFactory = sqlSessionFactory;
	}




	
	
}
