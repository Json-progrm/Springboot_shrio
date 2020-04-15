import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.getheart.MySpringBootApplication;
import com.getheart.pojo.Userinfo;
import com.getheart.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Json
 * @date 2020-08-15:10
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MySpringBootApplication.class)
public class test {


    @Autowired
    UserService userService;
    @Test
    public void findbynameandpwd() {

        Userinfo user = userService.findUserByName("张三");
        System.out.println(user);
    }
}
