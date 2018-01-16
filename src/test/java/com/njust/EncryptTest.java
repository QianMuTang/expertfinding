package com.njust;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class EncryptTest {
    private final static Logger logger = LoggerFactory.getLogger(EncryptTest.class);

    @Autowired
    StringEncryptor stringEncryptor;//密码解码器自动注入

//    @Value("${spring.datasource.password}")
//    private String password;
//
//    @Autowired
//    private UserMapper userMapper;

    @Test
    public void test() {
//        此处查看自己数据密码加密后的结果，并填充到yml文件的spring.datasource.password中
        logger.info("加密结果：{}", stringEncryptor.encrypt("Zwq18362961518."));
//        logger.info("连接数据库密码:{}", password);
//        logger.info("查询到的信息：{}", userMapper.selectByPrimaryKey(1).getUserName());
    }
}
