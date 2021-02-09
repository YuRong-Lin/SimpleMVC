package ioc.bean;

import lombok.extern.slf4j.Slf4j;
import simplemvc.core.annotation.Controller;
import simplemvc.ioc.annotation.Autowired;

/**
 * @Author LinYuRong
 * @Date 2021/2/9 16:50
 * @Version 1.0
 */
@Slf4j
@Controller
public class TestController {

    @Autowired
    private TestService testService;

    public void sayHello() {
        log.info(testService.sayHello());
    }
}
