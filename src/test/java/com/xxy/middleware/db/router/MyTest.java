package com.xxy.middleware.db.router;

import com.xxy.middleware.db.router.annotation.DBRouter;
import com.xxy.middleware.db.router.po.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyTest {
    @DBRouter(key = "name")
    void insertUser(User req){
        System.out.println("req = " + req);
    }

    @Test
    public void test_aop(){
        User user = new User();
        user.setName("zhangsan");
        user.setAge(11);
        insertUser(user);
    }
}
