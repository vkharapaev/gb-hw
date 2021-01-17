package javacore.professional.hw7;

import javacore.professional.hw7.annotations.AfterSuite;
import javacore.professional.hw7.annotations.BeforeSuite;
import javacore.professional.hw7.annotations.Test;

import java.lang.reflect.Method;
import java.util.*;

public class Tester {

    private static final Comparator<Method> comparator =
            Comparator.comparingInt(m -> m.getAnnotation(Test.class).priority());

    public static void start(Class<?> testClass) {
        try {
            Object o = testClass.getConstructor().newInstance();
            Method before = null, after = null;
            for (Method m : testClass.getMethods()) {
                if (m.getAnnotation(BeforeSuite.class) != null) {
                    if (before != null) {
                        throw new RuntimeException("Found several BeforeSuite methods");
                    }
                    before = m;
                }
                if (m.getAnnotation(AfterSuite.class) != null) {
                    if (after != null) {
                        throw new RuntimeException("Found several AfterSuite methods");
                    }
                    after = m;
                }
            }
            if (before != null) {
                before.invoke(o);
            }
            List<Method> tests = new ArrayList<>();
            for (Method m : testClass.getMethods()) {
                if (m.getAnnotation(Test.class) != null) {
                    tests.add(m);
                }
            }
            tests.sort(Tester.comparator);
            StringBuilder sb = new StringBuilder();
            for (Method test : tests) {
                try {
                    test.invoke(o);
                    sb.append(" Test `").append(test.getName()).append("' executed successfully\n");
                } catch (Exception e) {
                    sb.append(" Test `").append(test.getName()).append("' failed\n");
                }
            }
            if (after != null) {
                after.invoke(o);
            }
            System.out.println("Results:\n"+sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
