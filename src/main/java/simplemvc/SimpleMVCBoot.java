package simplemvc;

import simplemvc.core.BeanContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SimpleMVC 启动器
 *
 * @Author LinYuRong
 * @Date 2021/2/9 15:08
 * @Version 1.0
 */
public final class SimpleMVCBoot {
    private static final Logger log = LoggerFactory.getLogger(SimpleMVCBoot.class);

    /**
     * 全局配置
     */
    private static Configuration configuration = Configuration.builder().build();

    public static void run(Class<?> bootClass) {
        run(Configuration.builder().bootClass(bootClass).build());
    }

    public static void run(Configuration configuration) {
        new SimpleMVCBoot().start(configuration);
    }

    /**
     * 启动
     *
     * @param configuration
     */
    private void start(Configuration configuration) {
        try {
            SimpleMVCBoot.configuration = configuration;
            String basePackage = configuration.getBootClass().getPackage().getName();
            BeanContainer.getInstance().loadBeans(basePackage);

            System.out.println("beans size: " + BeanContainer.getBeanSize());

        } catch (Exception e) {
            log.error("SimpleMVCBoot start failed!", e);
        }
    }

    public static void main(String[] args) {
        SimpleMVCBoot.run(SimpleMVCBoot.class);
    }
}
