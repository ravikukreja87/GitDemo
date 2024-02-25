package tests;

import tests.BaseTest;

public class SecondTest extends BaseTest {

    public void secondTest(){

        variableFromParent = "Testing";
        System.out.println("baseTest.variableFromParent = " + variableFromParent);

    }
}
