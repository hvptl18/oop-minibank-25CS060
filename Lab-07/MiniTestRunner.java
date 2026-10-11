import java.lang.annotation.*;
import java.lang.reflect.Method;

// Define the @Run annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Run {
}

// Class containing test methods
class MyTests {

    @Run
    public void testAddition() {
        System.out.println("Test Addition: PASSED");
    }

    @Run
    public void testSubtraction() {
        System.out.println("Test Subtraction: PASSED");
    }

    public void normalMethod() {
        System.out.println("This method should not run.");
    }

    @Run
    public void testMultiplication() {
        System.out.println("Test Multiplication: PASSED");
    }
}

// Mini test runner
public class MiniTestRunner {

    public static void main(String[] args) {

        MyTests testObject = new MyTests();

        Class<?> cls = testObject.getClass();

        Method[] methods = cls.getDeclaredMethods();

        int count = 0;

        System.out.println("Running tests...\n");

        for (Method method : methods) {

            // Find methods annotated with @Run
            if (method.isAnnotationPresent(Run.class)) {

                // Only invoke no-argument methods
                if (method.getParameterCount() == 0) {

                    try {

                        method.invoke(testObject);
                        count++;

                    } catch (Exception e) {

                        System.out.println(
                            "Test failed: " + method.getName()
                        );
                    }
                }
            }
        }

        System.out.println("\nTotal tests run: " + count);
    }
}
