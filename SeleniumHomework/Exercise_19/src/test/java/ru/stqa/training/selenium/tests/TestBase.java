package ru.stqa.training.selenium.tests;

import org.junit.After;
import org.junit.Before;
import ru.stqa.training.selenium.app.Application;

import java.io.IOException;
import java.util.Properties;

public class TestBase {

    Application app;
    public static Properties props;


    @Before
    public void initializeWebDriver() throws IOException{
        props = new Properties();
        props.load(TestBase.class.getResourceAsStream("/test.properties"));
        app = new Application();

    }

    @After
    public void closeWebDriverSession(){
        app.quit();
        app = null;
    }
}
