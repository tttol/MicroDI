package di;

import java.util.HashMap;
import java.util.Map;

import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Container {
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
            log.warn("Failed to getInstance. message=%s, cause=%s".formatted(e.getCause(), e.getMessage()));
            return null;
        }
    }

    private static <T> T createObject(Class<T> type) {
        try {
            log.info("Creating object of type %s".formatted(type.getName()));
            T object = type.getDeclaredConstructor().newInstance();
            for (var field : type.getDeclaredFields()) {
                if (!field.isAnnotationPresent(Inject.class)) {
                    log.info("Skip injecting %s. %sField %s is not annotated with @Inject.".formatted(field.getName()));
                    continue;
                }
                field.setAccessible(true);
                field.set(object, getInstance(field.getName()));

                log.info("Injected %s into %s".formatted(field.getName(), type.getName()));
            }
            return object;
        } catch (Exception e) {
            log.warn("Failed to createObject. message=%s, cause=%s".formatted(e.getCause(), e.getMessage()));
            return null;
        }
    }
}
