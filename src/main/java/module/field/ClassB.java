package module.field;

import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClassB {
    @Inject
    private ClassC classC;

    public void print() {
        log.info("ClassB is called!");
        classC.print();
    }    
}
