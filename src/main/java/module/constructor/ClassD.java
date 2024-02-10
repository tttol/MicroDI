package module.constructor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClassD {
    private final ClassE classE;

    public ClassD(final ClassE classE) {
        this.classE = classE;
    }

    public void print() {
        log.info("ClassD is called!");
        classE.print();
    }
}
