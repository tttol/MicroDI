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
        // classnameを元にclassMapからクラスを取得し、そのクラスのインスタンスを返す
        try {
            // return classMap.get(className).getDeclaredConstructor().newInstance();
            return createObject(classMap.get(className));
        } catch (Exception e) {
            log.warn("Failed to getInstance. message=%s, cause=%s".formatted(e.getMessage(), e.getCause()));
            return null;
        }
    }

    private static <T> T createObject(Class<T> clazz) {
        try {
            log.info("[START]Creating object of type %s".formatted(clazz.getName()));
            T object = clazz.getDeclaredConstructor().newInstance();
            for (var field : clazz.getDeclaredFields()) {
                if (!field.isAnnotationPresent(Inject.class)) {
                    log.info("Skip injecting %s. Field %s is not annotated with @Inject.".formatted(field.getName(), field.getName()));
                    continue;
                }

                field.setAccessible(true);
                field.set(object, getInstance(field.getType().getName())); //getInstanceを再帰的に呼び出す

                log.info("Injected %s into %s".formatted(field.getName(), clazz.getName()));
            }
            return object;
        } catch (Exception e) {
            log.warn("Failed to createObject. message=%s, cause=%s".formatted(e.getMessage(), e.getCause()));
            return null;
        } finally {
            log.info("[END  ]Creating object of type %s".formatted(clazz.getName()));
        }
    }
}
