package h11;

import org.tudalgo.algoutils.reflect.ClassTester;
import org.tudalgo.algoutils.reflect.MethodTester;
import spoon.reflect.code.CtFor;
import spoon.reflect.code.CtWhile;

import java.util.List;

public class NoLoopAssertions {

    private NoLoopAssertions() {}

    public static void assertNoLoopsUsed(Class<?> clazz, String methodName) {
        var classTester = new ClassTester<>(clazz);
        classTester.resolveClass();
        classTester.assertClassResolved();

        var methodTester = new MethodTester(classTester, methodName);
        methodTester.resolveMethod();
        methodTester.assertMethodResolved();

        methodTester.assertConstructsNotUsed(List.of(
            CtWhile.class,
            CtFor.class
        ));
    }
}
