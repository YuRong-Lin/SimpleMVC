package simplemvc.ioc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author LinYuRong
 * @Date 2021/2/9 16:29
 * @Version 1.0
 */
// TODO: 1.构造注入(Constructor Injection) 2.设值方法注入(Setter Injection) 3.接口注入(Interface Injection)
// TODO: 构造注入会出现循环依赖
// 目前采用接口注入
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Autowired {
}
