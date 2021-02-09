package simplemvc.core;

import simplemvc.core.annotation.Component;
import simplemvc.core.annotation.Controller;
import simplemvc.core.annotation.Repository;
import simplemvc.core.annotation.Service;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import simplemvc.util.ClassUtil;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Bean容器-单例
 *
 * @Author LinYuRong
 * @Date 2021/2/8 18:20
 * @Version 1.0
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BeanContainer {

    /**
     * 存放所有的bean
     */
    private final Map<Class<?>, Object> beanMap = new ConcurrentHashMap<>();

    /**
     * 是否加载Bean
     */
    private boolean isLoadBean = false;

    /**
     * 加载bean的注解列表
     */
    private static final List<Class<? extends Annotation>> BEAN_ANNOTATION
            = Arrays.asList(Component.class, Controller.class, Service.class, Repository.class);

    /**
     * 是否加载Bean
     *
     * @return 是否加载
     */
    public boolean isLoadBean() {
        return isLoadBean;
    }

    public static BeanContainer getInstance() {
        return ContainerHolder.HOLDER.instance;
    }

    public static int getBeanSize() {
        return BeanContainer.getInstance().beanMap.size();
    }

    /**
     * 扫描加载所有Bean
     *
     * @param basePackage 包名
     */
    public void loadBeans(String basePackage) {
        if (isLoadBean()) {
            log.warn("bean已经加载");
            return;
        }

        Set<Class<?>> classSet = ClassUtil.getPackageClass(basePackage);
        classSet.stream()
                .filter(clz -> {
                    for (Class<? extends Annotation> annotation : BEAN_ANNOTATION) {
                        if (clz.isAnnotationPresent(annotation)) {
                            return true;
                        }
                    }
                    return false;
                })
                .forEach(clz -> beanMap.put(clz, ClassUtil.newInstance(clz)));
        isLoadBean = true;
    }

    public Set<Class<?>> getClasses() {
        return beanMap.keySet();
    }

    public Object getBean(Class<?> clz) {
        if (clz == null) {
            return null;
        }
        return beanMap.get(clz);
    }

    /**
     * 通过实现类或者父类获取Bean的Class集合
     *
     * @param interfaceClass 接口Class或者父类Class
     * @return Class集合
     */
    public Set<Class<?>> getClassesBySuper(Class<?> interfaceClass) {
        return beanMap.keySet()
                .stream()
                .filter(interfaceClass::isAssignableFrom)
                .filter(clz -> !clz.equals(interfaceClass))
                .collect(Collectors.toSet());
    }

    private enum ContainerHolder {
        HOLDER;
        private BeanContainer instance;

        ContainerHolder() {
            this.instance = new BeanContainer();
        }
    }
}
