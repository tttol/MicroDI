package module.constructor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ClassE {
    ClassF classF;

    public void print() {
        log.info("ClassE is called!");
        classF.print();
    }
}
