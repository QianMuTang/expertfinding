package com.njust.utils;

import com.njust.bean.CustomException;
import com.njust.bean.ResponseResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SendMailUtil {
    @Autowired
    private JavaMailSender mailSender;

    public void sendMail() throws Exception{
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("3187492483@qq.com");
            message.setTo("1106368586@qq.com");
            message.setSubject("主题：跟踪专家有新动态啦");
            message.setText("李教授近期发表了一篇SCI，快去看看吧！");
            mailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
            throw new CustomException(ResponseResultEnum.MAIL_ERROR);
        }

    }
}
