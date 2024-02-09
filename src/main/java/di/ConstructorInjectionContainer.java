package di;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConstructorInjectionContainer {
    static Map<String, Class<?>> classMap = new HashMap<>();

    public static void register(Class<?> clazz) {
        classMap.put(clazz.getName(), clazz);
    }

    public static Object getInstance(String className) {
        // classnameを元にclassMapからクラスを取得し、そのクラスのインスタンスを返す
        try {
            return createInstance(classMap.get(className));
        } catch (Exception e) {
            log.warn("Failed to getInstance. message={}, cause={}", e.getMessage(), e.getCause());
            return null;
        }
    }

    private static <T> T createInstance(Class<T> clazz) {
        try {
            log.info("[START]Creating instance of type {}", clazz.getName());
            for (var constructor : clazz.getConstructors()) {
                if (constructor.getParameterCount() == 0) {
                    log.debug("Skip default constructor");
                    continue;
                }

                for (var param : constructor.getParameters()) {
                    log.info("Injecting {} into {}", param.getName(), clazz.getName());
                    getInstance(param.getType().getName());
                }
            }
            return clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            log.warn("Failed to createInstance", e);
            return null;
        } finally {
            log.info("[END  ]Creating instance of type {}", clazz.getName());
        }
    }
}