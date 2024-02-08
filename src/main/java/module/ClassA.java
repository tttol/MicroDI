package module;

import jakarta.inject.Inject;

public class ClassA {
    @Inject
    ClassB classB;

    public void print() {
        classB.print();
        System.out.println("ClassA is called!");
    }
}
