package com.tcps.oauth2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component//被spring容器管理
//@Order(Ordered.HIGHEST_PRECEDENCE)//如果多个自定义ApplicationRunner，用来标明执行顺序
@Order(2)
public class RunAtFirst implements ApplicationRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        int result = jdbcTemplate.queryForObject("select count(1) from t_user where username = 'root' ", Integer.class);
//        if (result == 0) {
//            String username = "root";
//            String password = new BCryptPasswordEncoder().encode("root");
//            jdbcTemplate.update("insert into t_user (id,username,password,create_date,create_user)" +
//                    "values(?,?,?,?,?)", new Object[]{1, username, password, new Date(), "管理员"});
//            log.info("t_user表没有root用户，系统将自动创建");
//        }
//        //使数据库中的数据通过Bcry加密，否者发送 http://127.0.0.1:8081/oauth/token 请求，报错。
//        log.info("bcry:secret is" + new BCryptPasswordEncoder().encode("secret"));
//        String bcrySecret = "update OAUTH_CLIENT_DETAILS set client_secret = '$2a$10$SZbJZnuNXUs97Q7SZGrkQe5ckBatLQzGL5gPYfH8ME7cwbXJfoyCa'";
//        jdbcTemplate.update(bcrySecret);
    }
}
