package module.constructor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
// @RequiredArgsConstructor
public class ClassD {
    ClassE classE;

    public ClassD(ClassE classE) {
        this.classE = classE;
    }

    public void print() {
        log.info("ClassD is called!");
        classE.print();
    }
}
