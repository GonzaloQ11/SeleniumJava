package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DemoPage;

public class DemoTest extends BaseTest {

    public void demo() throws InterruptedException {
        int[] test = {1,4,5};
        /*
        DemoPage demo =  new DemoPage(driver);
        Assert.assertTrue(demo.isEnableDisabledInputDisplayed(), "Input is not displayed");
        demo.clickOnEnableDisabledButton();
        Assert.assertTrue(demo.isEnabledMessageDisplayed());
        Assert.assertTrue(demo.isInputEnabled());
        demo.clickOnEnableDisabledButton();
        Assert.assertTrue(demo.isDisabledMessageDisplayed());
        Assert.assertFalse(demo.isInputEnabled());
         */
    }
}
