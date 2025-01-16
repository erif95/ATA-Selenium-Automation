package com.platform.web.page;

import com.platform.web.page.saucedemo.LoginPage;
import com.platform.web.page.saucedemo.ProductPage;
import com.platform.web.webactions.BaseTests;


public class BasePage extends BaseTests {

    public LoginPage LoginPage() { return new LoginPage(driver);}

    public ProductPage ProductPage() { return new ProductPage(driver);}


}
