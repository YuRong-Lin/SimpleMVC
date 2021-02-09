package ioc;

import ioc.bean.TestController;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import simplemvc.core.BeanContainer;
import simplemvc.ioc.Ioc;

/**
 * @Author LinYuRong
 * @Date 2021/2/9 16:53
 * @Version 1.0
 */
@Slf4j
public class IocTest {

    @Test
    public void doIoc() {
        BeanContainer beanContainer = BeanContainer.getInstance();
        beanContainer.loadBeans("ioc");
        new Ioc().doIoc();

        TestController testController = (TestController) beanContainer.getBean(TestController.class);
        testController.sayHello();
    }
}
