package hello.annotations;

import hello.components.profiling.ProfilingComponent;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

//@Component
public class ProfilingHandlerBeanPostProcessor implements BeanPostProcessor {
    private Map<String, Class> map = new HashMap<>();
    private ProfilingComponent profilingComponent = new ProfilingComponent();

    public ProfilingHandlerBeanPostProcessor() throws Exception {
//        MBeanServer mBeans = ManagementFactory.getPlatformMBeanServer();
//        mBeans.registerMBean(profilingComponent, new ObjectName("hello.profiling", "name", "profilingMy3"));
    }

    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        Class<?> beanClass = o.getClass();
        if (beanClass.isAnnotationPresent(Profiling.class)) {
            map.put(beanClass.getName(), beanClass);
        }

        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        Class<?> beanClass = map.get(s);

        if (beanClass != null) {
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("PROFILING MY METHOD!!!");
                    Object returnValue = method.invoke(o, args);
                    System.out.println("END PROFILING MY METHOD!!!");

                    return returnValue;
                }
            });
        }

        return o;
    }
}
