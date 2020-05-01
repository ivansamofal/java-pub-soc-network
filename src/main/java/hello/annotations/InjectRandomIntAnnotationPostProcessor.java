package hello.annotations;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Random;

//@Component
public class InjectRandomIntAnnotationPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        System.out.println("!!!!!THIS IS POST PROCESS COMPONENT FOR RANDOM ANNOTATION!!!!!");
        Field[] fields = o.getClass().getDeclaredFields();
        for (Field field : fields) {
            System.out.println("FIELD: " + field.getName());
            InjectRandomInt annotation = field.getAnnotation(InjectRandomInt.class);
            if (annotation != null) {
                int min = annotation.min();
                int max = annotation.max();
                Random random = new Random();
                int i = min + random.nextInt(max - min);
                System.out.println("TRY TO SET FIELD " + field + " VALUE " + i);
                ReflectionUtils.setField(field, o, i);
            }
        }

        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        return null;
    }
}
