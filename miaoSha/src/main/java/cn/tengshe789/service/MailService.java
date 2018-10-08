package cn.tengshe789.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @program: miaoSha
 * @description: 发送邮件
 *
 * 因为各种原因，总会有邮件发送失败的情况，比如：邮件发送过于频繁、网络异常等。在出现这种情况的时候，我们一般会考虑重新重试发送邮件，会分为以下几个步骤来实现：
 *
 * 接收到发送邮件请求，首先记录请求并且入库。
 *
 * 调用邮件发送接口发送邮件，并且将发送结果记录入库。
 *
 * 启动定时系统扫描时间段内，未发送成功并且重试次数小于3次的邮件，进行再次发送.
 *
 * 重新发送邮件的时间，建议以 2 的次方间隔时间，比如：2、4、8、16 ...
 *
 * 常见的错误返回码：
 *
 * 421 HL:ICC 该IP同时并发连接数过大，超过了网易的限制，被临时禁止连接。
 *
 * 451 Requested mail action not taken: too much fail authentication 登录失败次数过多，被临时禁止登录。请检查密码与帐号验证设置
 *
 * 553 authentication is required，密码配置不正确
 *
 * 554 DT:SPM 发送的邮件内容包含了未被许可的信息，或被系统识别为垃圾邮件。请检查是否有用户发送病毒或者垃圾邮件；
 *
 * 550 Invalid User 请求的用户不存在
 *
 * 554 MI:STC 发件人当天内累计邮件数量超过限制，当天不再接受该发件人的投信。
 *
 * 如果使用一个邮箱频繁发送相同内容邮件，也会被认定为垃圾邮件，报 554 DT:SPM 错误
 *
 * 如果使用网易邮箱可以查看这里的提示：企业退信的常见问题？
 *
 * @author: tEngSHe789
 * @create: 2018-10-08 10:07
 **/
@Service
@Slf4j
public class MailService{
    @Autowired
    private JavaMailSender sender;

    @Value("$(spring.mail.username)")
    private String from;

    /**
     * 发送简单邮件
     * @param to
     * @param subject
     * @param text
     */
    public void sendMail(String to,String subject,String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        try {
            sender.send(message);
            log.info("发送邮件ing");
        }catch (Exception e){
            log.error("发送邮件fail");
        }
    }

    /**
     * 发送html的邮件
     * @param to
     * @param subject
     * @param text
     */
    public void sendHtmlMail(String to,String subject,String text) {
        MimeMessage message = sender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text,true);//true
            sender.send(message);
            log.info("发送html邮件ing");
        } catch (MessagingException e) {
            log.error("发送html邮件fail");
        }
    }

    /**
     * 发送带附件的邮件
     * @param to
     * @param subject
     * @param text
     */
    public void sendAttachmentsMail(String to,String subject,String text,String filePath) {
        MimeMessage message = sender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text,true);

            FileSystemResource fileSystemResource = new FileSystemResource(new File(filePath));
            String fileName = fileSystemResource.getFilename();
            helper.addAttachment(fileName,fileSystemResource);

            sender.send(message);
            log.info("发送带附件的邮件ing");
        } catch (MessagingException e) {
            log.error("发送带附件的邮件fail");
        }
    }

    /**
     * 发送带静态资源(譬如图片)的邮件
     * @param to
     * @param subject
     * @param text
     * @param filePath 图片路径
     * @param fileId 图片id
     */
    public void sendInlineResourceMail(String to,String subject,String text,String filePath,String fileId) {
        MimeMessage message = sender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text,true);

            FileSystemResource fileSystemResource = new FileSystemResource(new File(filePath));
            helper.addInline(fileId,fileSystemResource);

            sender.send(message);
            log.info("发送嵌入静态资源的邮件ing");
        } catch (MessagingException e) {
            log.error("发送嵌入静态资源的邮件fail");
        }
    }

}

