package di;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import module.constructor.ClassD;
import module.constructor.ClassE;
import module.constructor.ClassF;

class ConstructorInjectionContainerTest {

    @Test
    @DisplayName("コンストラクタインジェクションでインスタンスを取得できることを確認するテスト")
    void testGetInstance() {
        ConstructorInjectionContainer.register(ClassD.class);
        // ConstructorInjectionContainer.register(ClassE.class);
        
        var classD = ConstructorInjectionContainer.getInstance(ClassD.class);
        var classE = ConstructorInjectionContainer.getInstance(ClassE.class);
        var classF = ConstructorInjectionContainer.getInstance(ClassF.class);

        assertNotNull(classD, "The instance should not be null");
        assertNotNull(classE, "The instance should not be null");
        assertNotNull(classF, "The instance should not be null");
        assertTrue(classD instanceof ClassD, "The instance should be of type ClassD");
        assertTrue(classE instanceof ClassE, "The instance should be of type ClassE");
        assertTrue(classF instanceof ClassF, "The instance should be of type ClassF");
    }
}