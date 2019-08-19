import com.qf.pojo.MenuInfo;
import com.qf.pojo.UserInfo;
import com.qf.service.MenuInfoService;
import com.qf.service.RoleInfoService;
import com.qf.service.UserInfoService;
import com.qf.vo.MenuInfoVO;
import com.qf.vo.RoleInfoVO;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

/**
 * Created by DELL on 2019/7/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)//junit整合spring的测试//立马开启了spring的注解
@ContextConfiguration(locations={"classpath:spring-mybatis.xml","classpath:spring-service.xml"})//加载核心配置文件，自动构建spring容器
public class JunitDemo {

    @Autowired
    UserInfoService userInfoService;
    @Autowired
    RoleInfoService roleInfoService;
    @Autowired
    MenuInfoService menuInfoService;

    @Ignore
    @Before
    public void initApplicationContext(){
        System.out.println("aaa");
        //加载spring容器，初始化其中对象
//        context = new ClassPathXmlApplicationContext("spring-mybatis.xml","spring-service.xml");
    }

//    @After
//    public void destoryApplicationContext(){
//        context = null;
//    }

//    @Test
//    @Ignore
//    public void sayHello() throws Exception {
//        System.out.println("helloWorld");
//        throw new Exception("人为制造异常");
//    }

    @Test
    public void loginCheckTest(){
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("王涛");
        userInfo.setPassword("123456");
        UserInfo userInfo1 = userInfoService.loginCheck(userInfo);

//        Assert.assertEquals(b,true);
        System.out.println(userInfo1);
    }
    @Test
    public void listAllUserInfoTest(){
        List<UserInfo> userInfos = userInfoService.getAllUserInfo(null);
        System.out.println(userInfos);
    }

    @Test
    public void listRoleByUserId(){
        List<RoleInfoVO> roleInfoVOs = roleInfoService.listRoleByUserId(1);
        System.out.println(roleInfoVOs);
    }

    @Test
    public void listMenuByRoleId(){
        List<MenuInfoVO> menuInfoVOS = menuInfoService.listMenuByRoleId(1);
        System.out.println(menuInfoVOS);
    }

    @Test
    public void longinInit(){
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("王涛");
        userInfo.setPassword("123456");
        List<MenuInfo> menuInfoList = userInfoService.userLoginInit(userInfo);
        System.out.println(menuInfoList);
    }

    @Test
    public void getMD5Password() throws NoSuchAlgorithmException {

        String s = DigestUtils.md5Hex("xuke1994");
        //2 32次方-1*一亿次
        //超算(超级计算机)
        for(int i=0;i<10;i++){
            s = DigestUtils.md5Hex(s.substring(15));
        }
        System.out.println(s);
    }
}
