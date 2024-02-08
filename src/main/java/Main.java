import di.Container;
import module.ClassA;
import module.ClassB;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, MicroDI!");

        Container.register(ClassA.class);
        Container.register(ClassB.class);

        var classA = (ClassA) Container.getInstance(ClassA.class.getName());
        classA.print();
    }
}