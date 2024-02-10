import di.ConstructorInjectionContainer;
import di.FieldInjectionContainer;
import module.constructor.ClassD;
import module.constructor.ClassE;
import module.constructor.ClassF;
import module.field.ClassA;

public class Main {
    public static void main(String[] args) {
        var classA = (ClassA) FieldInjectionContainer.getInstance(ClassA.class);
        classA.print();

        ConstructorInjectionContainer.register(ClassD.class);
        ConstructorInjectionContainer.register(ClassE.class);
        ConstructorInjectionContainer.register(ClassF.class);

        var classD = (ClassD) ConstructorInjectionContainer.getInstance(ClassD.class);
        classD.print();
    }
}