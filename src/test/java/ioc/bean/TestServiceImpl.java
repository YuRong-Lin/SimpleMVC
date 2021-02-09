package ioc.bean;

import simplemvc.core.annotation.Service;

/**
 * @Author LinYuRong
 * @Date 2021/2/9 16:51
 * @Version 1.0
 */
@Service
public class TestServiceImpl implements TestService {

    @Override
    public String sayHello() {
        return "hello, ioc";
    }
}
