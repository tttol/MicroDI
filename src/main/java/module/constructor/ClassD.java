package module.constructor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ClassD {
    ClassE classE;

    public void print() {
        log.info("ClassD is called!");
        classE.print();
    }
}
