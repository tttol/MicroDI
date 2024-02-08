import di.Container;
import module.ClassA;
import module.ClassB;
import module.ClassC;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, MicroDI!");

        Container.register(ClassA.class);
        Container.register(ClassB.class);
        Container.register(ClassC.class);

        var classA = (ClassA) Container.getInstance(ClassA.class.getName());
        classA.print();
    }
}