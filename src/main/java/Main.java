import di.FieldInjectionContainer;
import module.field.ClassA;
import module.field.ClassB;
import module.field.ClassC;

public class Main {
    public static void main(String[] args) {
        FieldInjectionContainer.register(ClassA.class);
        FieldInjectionContainer.register(ClassB.class);
        FieldInjectionContainer.register(ClassC.class);

        var classA = (ClassA) FieldInjectionContainer.getInstance(ClassA.class.getName());
        classA.print();
    }
}