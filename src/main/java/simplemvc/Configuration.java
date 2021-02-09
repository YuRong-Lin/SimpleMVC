package simplemvc;

import lombok.Builder;
import lombok.Getter;

/**
 * @Author LinYuRong
 * @Date 2021/2/9 15:14
 * @Version 1.0
 */
@Builder
@Getter
public class Configuration {

    /**
     * 启动类
     */
    private Class<?> bootClass;
}
