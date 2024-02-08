package di;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Container {
    private static Logger logger = Logger.getLogger(Container.class.getName());

    static Map<String, Class<?>> classMap = new HashMap<>();

    public static void register(Class<?> clazz) {
        classMap.put(clazz.getName(), clazz);
    }

    public static Object getInstance(String className) {
        // classnameを元にclassMapからクラスを取得し、そのクラスのインスタンスを返す
        try {
            return classMap.get(className).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            logger.warning("Failed to getInstance. message=%s, cause=%s".formatted(e.getCause(), e.getMessage()));
            return null;
        }
    }
}
