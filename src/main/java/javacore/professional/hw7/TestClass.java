package javacore.professional.hw7;

import javacore.professional.hw7.annotations.AfterSuite;
import javacore.professional.hw7.annotations.BeforeSuite;
import javacore.professional.hw7.annotations.Test;

class TestClass {

    public TestClass() {
    }

    @BeforeSuite
    public void before() {
        System.out.println("Before");
    }

    @AfterSuite
    public void after() {
        System.out.println("After");
    }

    @Test(priority = 10)
    public void test() {
        System.out.println("Test1");
    }

    @Test()
    public void test2() throws Exception {
        System.out.println("Test2");
        throw new Exception();
    }

    @Test(priority = 1)
    public void test3() {
        System.out.println("Test3");
    }
}
