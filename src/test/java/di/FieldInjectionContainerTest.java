package di;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import module.field.ClassA;
import module.field.ClassB;
import module.field.ClassC;

class FieldInjectionContainerTest {

    @Test
    @DisplayName("フィールドインジェクションでインスタンスを取得できることを確認するテスト")
    void testGetInstance() {
        var classA = FieldInjectionContainer.getInstance(ClassA.class);
        var classB = FieldInjectionContainer.getInstance(ClassB.class);
        var classC = FieldInjectionContainer.getInstance(ClassC.class);

        assertNotNull(classA, "The instance should not be null");
        assertNotNull(classB, "The instance should not be null");
        assertNotNull(classC, "The instance should not be null");
        assertTrue(classA instanceof ClassA, "The instance should be of type ClassA");
        assertTrue(classB instanceof ClassB, "The instance should be of type ClassB");
        assertTrue(classC instanceof ClassC, "The instance should be of type ClassC");
    }
}