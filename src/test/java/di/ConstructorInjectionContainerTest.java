package di;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import module.constructor.ClassD;

class ConstructorInjectionContainerTest {

    @Test
    void testGetInstance() {
        ConstructorInjectionContainer.register(ClassD.class);
        var instance = ConstructorInjectionContainer.getInstance(ClassD.class);

        assertNotNull(instance, "The instance should not be null");
        assertTrue(instance instanceof ClassD, "The instance should be of type ClassD");
    }
}