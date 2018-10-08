package cn.tengshe789.controller;


import cn.tengshe789.result.CodeMsg;
import cn.tengshe789.result.Result;
import cn.tengshe789.service.MailService;
import com.github.pagehelper.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: miaoSha
 * @description: 发送邮件的api接口
 * @author: tEngSHe789
 * @create: 2018-10-08 11:07
 **/

@RestController
@Slf4j
public class MailController {
    @Autowired
    private MailService mailService;

    @RequestMapping("/mail/sendMail")
    public Result<String> sendMail(String to,String subject,String text){
        if (StringUtil.isEmpty(to) || !to.contains("@")){
            return Result.error(CodeMsg.MAIL_FORMAT_ERROR);
        }
        if (StringUtil.isEmpty(text)){
            return Result.error(CodeMsg.MAIL_CONTENET_ISNULL);
        }
        try {
            mailService.sendMail(to, subject, text);
        }catch (Exception e){
            return Result.error(CodeMsg.MAIL_ERROR);
        }
        return Result.success("邮件发送成功！");
    }
}
