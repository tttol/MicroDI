import di.ConstructorInjectionContainer;
import di.FieldInjectionContainer;
import module.constructor.ClassD;
import module.constructor.ClassE;
import module.constructor.ClassF;
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

        ConstructorInjectionContainer.register(ClassD.class);
        ConstructorInjectionContainer.register(ClassE.class);
        ConstructorInjectionContainer.register(ClassF.class);

        var classD = (ClassD) ConstructorInjectionContainer.getInstance(ClassD.class);
        classD.print();
    }
}