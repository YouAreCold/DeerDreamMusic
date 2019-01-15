package com.xky.roll.music_service.util.email;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.xky.roll.music_service.constall.ExceptionLevel;
import com.xky.roll.music_service.pojo.SysExceptionLog;

/**
 * 发送邮箱  工具类
  * @ClassName: EmailUtil 
  * @Description: TODO() 
  * @author wujiaxin
  * @date 2017-9-30 上午10:31:38 
  *
 */
public class EmailUtil {
	private String host = ""; // smtp服务器
    private String from = ""; // 发件人地址
    private String to = ""; // 收件人地址
    private String affix = ""; // 附件地址
    private String affixName = ""; // 附件名称
    private String user = ""; // 用户名
    private String pwd = ""; // 密码
    private String subject = ""; // 邮件标题
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//日期格式化器

    public void setAddress(String from, String to, String subject) {
        this.from = from;
        this.to = to;
        this.subject = subject;
    }

    public void setAffix(String affix, String affixName) {
        this.affix = affix;
        this.affixName = affixName;
    }
    
    /**
     * 设置smtp服务器以及邮箱的帐号和密码
     * 用QQ 邮箱作为发生者不好使 （原因不明）
     * 163 邮箱可以，但是必须开启  POP3/SMTP服务 和 IMAP/SMTP服务
     * 因为程序属于第三方登录，所以登录密码必须使用163的授权码  
     * 注意： [授权码和你平时登录的密码是不一样的
     */
    public void send(String host, String user, String pwd,String content) {
        this.host = host;
        this.user = user;
        this.pwd = pwd;

        Properties props = new Properties();

        // 设置发送邮件的邮件服务器的属性（这里使用网易的smtp服务器）
        props.put("mail.smtp.host", host);
        // 需要经过授权，也就是有户名和密码的校验，这样才能通过验证（一定要有这一条）
        props.put("mail.smtp.auth", "true");

        // 用刚刚设置好的props对象构建一个session
        Session session = Session.getDefaultInstance(props);

        // 有了这句便可以在发送邮件的过程中在console处显示过程信息，供调试使
        // 用（你可以在控制台（console)上看到发送邮件的过程）
        session.setDebug(true);

        // 用session为参数定义消息对象
        MimeMessage message = new MimeMessage(session);
        try {
            // 加载发件人地址
            message.setFrom(new InternetAddress(from));
            // 加载收件人地址
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            // 加载标题
            message.setSubject(subject);

            // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
            Multipart multipart = new MimeMultipart();

            // 设置邮件的文本内容
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setText(content);
            multipart.addBodyPart(contentPart);
            //当添加附件时 在执行
            if(affix != "" &&  affixName != ""){
            	// 添加附件
            	BodyPart messageBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(affix);
                // 添加附件的内容
                messageBodyPart.setDataHandler(new DataHandler(source));
               // 添加附件的标题
               // 这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码
               sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
               messageBodyPart.setFileName("=?GBK?B?" + enc.encode(affixName.getBytes()) + "?=");
               multipart.addBodyPart(messageBodyPart);
            }
            // 将multipart对象放到message中
            message.setContent(multipart);
            // 保存邮件
            message.saveChanges();
            // 发送邮件
            Transport transport = session.getTransport("smtp");
            // 连接服务器的邮箱
            transport.connect(host, user, pwd);
            // 把邮件发送出去
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 调用 异常邮件发送
     * @param toUser : 收件人邮箱
     * @param content ： 邮件内容
     * @param erroCode : 错误码
     * @param fileUrlAndName ： 附件路径和附件名称 （无的话 可直接为null）    例   "fileUrl","fileName"  
     */
    public static List<SysExceptionLog> sendExceptionEmail(String toUser,String message,String erroCode,String content,String... fileUrlAndName){
		
    	//存放错误信息
    	List<SysExceptionLog> exceptionList = new ArrayList<SysExceptionLog>();
    	
    	//邮箱标题
		String title;
		//错误严重程度
		String exceptionLevel = null;
		//判断错误码
		switch(erroCode){
			case "500":
				title = "院内云平台出现了500异常！";
				exceptionLevel = ExceptionLevel.SERIOUS.toString();
				break;
			case "404":
				title = "院内云平台出现了404异常！";
				exceptionLevel = ExceptionLevel.REMIND.toString();
				break;
			case "405":
				title = "院内云平台出现了405异常！";
				exceptionLevel = ExceptionLevel.REMIND.toString();
				break;
			case "415":
				title = "院内云平台出现了415异常！";
				exceptionLevel = ExceptionLevel.NOTICE.toString();
				break;
			case "DB":
				title = "院内云平台出现了数据库操作异常！";
				exceptionLevel = ExceptionLevel.SERIOUS.toString();
				break;
		 	default:
		 		title = "院内云平台出现了未知异常！";
		     	break;
		}
		//实例化邮箱类
		EmailUtil cn = new EmailUtil();
		// 设置发件人地址、收件人地址和邮件标题
		cn.setAddress("wujiaxin_gl@163.com", toUser, title);
		// 设置要发送附件的位置和标题
		if(fileUrlAndName != null){
		    cn.setAffix(fileUrlAndName[0], fileUrlAndName[1]);
		}
		/**
		 * 发件人暂时固定
		 * 设置smtp服务器以及邮箱的帐号和密码
		 * 用QQ 邮箱作为发生者不好使 （原因不明）
		 * 163 邮箱可以，但是必须开启  POP3/SMTP服务 和 IMAP/SMTP服务
		 * 因为程序属于第三方登录，所以登录密码必须使用163的授权码  
		 * 注意： [授权码和你平时登录的密码是不一样的
		 */
		cn.send("smtp.163.com", "wujiaxin_gl@163.com", "jiaxin4568521234",content);
		
		exceptionList.add(new SysExceptionLog(title,erroCode,content,exceptionLevel,sdf.format(new Date())));
		
		return exceptionList;
    }
    
}
