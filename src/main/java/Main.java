import di.Container;
import module.ClassA;

public class Main {
    
    public static void main(String[] args) {
        System.out.println("Hello, MicroDI!");

        Container.register(ClassA.class);

        var classA = (ClassA) Container.getInstance("classA");
        classA.print();
    }
}