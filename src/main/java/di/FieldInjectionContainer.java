package di;

import java.util.HashMap;
import java.util.Map;

import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FieldInjectionContainer {
    static Map<String, Class<?>> classMap = new HashMap<>();

    public static void register(Class<?> clazz) {
        classMap.put(clazz.getName(), clazz);
    }

    public static Object getInstance(String className) {
        try {
            return createInstance(classMap.get(className));
        } catch (Exception e) {
            log.warn("Failed to getInstance", e);
            return null;
        }
    }

    private static <T> T createInstance(Class<T> clazz) {
        try {
            log.info("[START]Creating object of type {}", clazz.getName());
            T instance = clazz.getDeclaredConstructor().newInstance();
            for (var field : clazz.getDeclaredFields()) {
                if (!field.isAnnotationPresent(Inject.class)) {
                    log.info("Skip injecting {}. Field {} is not annotated with @Inject.", field.getName(), field.getName());
                    continue;
                }

                field.setAccessible(true);
                field.set(instance, getInstance(field.getType().getName())); //getInstanceを再帰的に呼び出す

                log.info("Injected {} into {}", field.getName(), clazz.getName());
            }
            return instance;
        } catch (Exception e) {
            log.warn("Failed to createObject.", e);
            return null;
        } finally {
            log.info("[END  ]Creating object of type {}", clazz.getName());
        }
    }
}
