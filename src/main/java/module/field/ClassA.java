package module.field;

import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClassA {
    @Inject
    ClassB classB;

    public void print() {
        log.info("ClassA is called!");
        classB.print();
    }
}
