package pub.util;

import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.mail.util.MailSSLSocketFactory;

import editor.Constants.Constants_xt;

public class EmailUtil {
	static Logger logger = LoggerFactory.getLogger(EmailUtil.class);
	public static String sendEmail(HttpSession session, String ReceiveEmail)
			throws Exception {
		int MAX = 100000;
		int MIN = 10000;
		Random rand = new Random();
		int randNumber = rand.nextInt(MAX - MIN + 1) + MIN;
		// 验证邮箱是否已存在
		String[] arr=new String[3];
		if (ReceiveEmail.indexOf("@qq.com") > 0) {
			arr[0] = Constants_xt.QQ_sendService;
			arr[1] = Constants_xt.QQ_Service_SQM;
			arr[2] = Constants_xt.QQ_ServiceHOST;
		}/*else if(ReceiveEmail.indexOf("@163.com") > 0){
			arr[0] = Constants_xt.WY_sendService;
			arr[1] = Constants_xt.WY_Service_SQM;
			arr[2] = Constants_xt.WY_ServiceHOST;
		}*/
		// 发送邮箱验证码
		String result=email(ReceiveEmail, ConvertUtil.createString(randNumber),arr);
		if(result.equals("ok"))
		session.setAttribute(ReceiveEmail, ConvertUtil.createString(randNumber));
		return result;
	}


	/**
	 * 
	 * 功能逻辑：
	 *
	 * @方法名称：email
	 * @作者:韩伟其
	 * @创建日期： 2019年5月1日
	 * 
	 * @param receiveEmail
	 * @param code
	 * @param arr 发送邮箱的服务器，验证码，邮箱协议
	 * @return String
	 * 
	 * @修改记录（修改时间、作者、原因）：
	 */
	public  static String email(String receiveEmail, String code, String[] arr) {

		try {
			// 1.创建连接对象javax.mail.Session
			// 2.创建邮件对象 javax.mail.Message
			// 3.发送一封激活邮件
			 // 指定发送邮件的主机smtp.qq.com(QQ)|smtp.163.com(网易)
			Properties properties = System.getProperties();// 获取系统属性
			if (receiveEmail.indexOf("@qq.com") > 0) {
				// QQ邮箱需要下面这段代码，163邮箱不需要
				MailSSLSocketFactory sf = new MailSSLSocketFactory();
				sf.setTrustAllHosts(true);
				properties.put("mail.smtp.ssl.enable", "true");
				properties.put("mail.smtp.ssl.socketFactory", sf);
			} 
			properties.setProperty("mail.smtp.host", arr[2]);// 设置邮件服务器
			properties.setProperty("mail.smtp.auth", "true");// 打开认证
			// 1.获取默认session对象
			Session session = Session.getDefaultInstance(properties, new Authenticator() {
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(arr[0], arr[1]); // 发件人邮箱账号、授权码
				}
			});
			// 2.创建邮件对象
			Message message = new MimeMessage(session);
			// 2.1设置发件人
			message.setFrom(new InternetAddress(arr[0]));
			// 2.2设置接收人
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiveEmail));
			// 2.3设置邮件主题
			message.setSubject("网络助教系统注册邮箱验证");
			// 2.4设置邮件内容
			/*
			 * String content =
			 * "<html><head></head><body><h1>这是一封激活邮件,激活请点击以下链接</h1><h3><a href='http://localhost:8080/RegisterDemo/ActiveServlet?code="
			 * + code +
			 * "'>http://localhost:8080/RegisterDemo/ActiveServlet?code=" + code
			 * + "</href></h3></body></html>";
			 */
			String content = "您的邮箱注册验证码为:" + code;
			message.setContent(content, "text/html;charset=UTF-8");
			// 3.发送邮件
			Transport.send(message);
			logger.debug("邮箱验证码成功发送!="+code);
			return "ok";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("邮箱不存在！", e);
			return "550";
		}
	}
	
	/*@Test
	public void sendEmail() {
		String[] emailServer =new String[3];
		emailServer[0] = Constants_xt.QQ_sendService;
		emailServer[1] = Constants_xt.QQ_Service_SQM;
		emailServer[2] = Constants_xt.QQ_ServiceHOST;
		EmailUtil.email("1224722373@qq.com","123456",emailServer);
	}*/
}
