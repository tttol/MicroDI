package di;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConstructorInjectionContainer {
    static Map<String, Class<?>> classMap = new HashMap<>();
    static Map<String, Constructor<?>> constructorMap = new HashMap<>();

    public static void register(Class<?> clazz) {
        classMap.put(clazz.getName(), clazz);

        Stream.of(clazz.getConstructors())
            .filter(constructor -> constructor.getParameterCount() > 0) //デフォルトコンストラクタを除外
            .forEach(nullableConstructor -> {
                constructorMap.put(clazz.getName(), nullableConstructor);
            });
    }

    public static Object getInstance(String className) {
        try {
            return createInstance(classMap.get(className));
        } catch (Exception e) {
            log.warn("Failed to getInstance. message={}, cause={}", e.getMessage(), e.getCause());
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> T createInstance(Class<T> clazz) {
        try {
            var constructor = constructorMap.get(clazz.getName()) != null ? constructorMap.get(clazz.getName()) : clazz.getDeclaredConstructor();
            return (T) constructor
                .newInstance(Stream.of(constructor.getParameterTypes())
                    .map(parameterType -> getInstance(parameterType.getName()))
                    .toArray()
                );
        } catch (Exception e) {
            log.warn("Failed to createInstance", e);
            return null;
        }
    }
}