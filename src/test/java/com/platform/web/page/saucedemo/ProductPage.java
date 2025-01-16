package com.platform.web.page.saucedemo;

import com.platform.web.webactions.BaseActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductPage extends BaseActions {

    public static List<Double> priceBeforeSort = new ArrayList<>();
    public static List<Double> priceAfterSort  = new ArrayList<>();

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void selectDropDownPrice(String text) {
        selectOnDropDownByText("DROPDOWN_PRICE","Price (high to low)", 10);
    }

    public void getPriceBeforeSort() {

        List<WebElement> elements = waitAndGetListElements("LISTPRICE_PRODUCT", 20);
        for (WebElement element : elements) {
            priceBeforeSort.add(Double.parseDouble(element.getText().replaceAll("[^\\d.]", "")));
        }
    }

    public void getPriceAfterSort() {
        List<WebElement> elements = waitAndGetListElements("LISTPRICE_PRODUCT", 20);
        for (WebElement element : elements) {
            priceAfterSort.add(Double.parseDouble(element.getText().replaceAll("[^\\d.]", "")));
        }
    }

    public void addProductToCart(String price) {

        List<WebElement> elements = waitAndGetListElements("LISTPRICE_PRODUCT", 20);

            for(int i=0 ; i < elements.size(); i++) {

                if(elements.get(i).getText().equals(price)) {
                    clickElementWithIndex("BTN_ADDTOCART", 10, i);
                }
            }
      }

      public void clickCartIcon() {
        clickElement("ICON_CART", 10);
        clickElement("BTN_CHECKOUT",10);
      }

      public void inputCheckoutInformation (String firstname, String lastname, String postalcode) {
        inputTextWithValue("INPUT_FIRSTNAME", firstname, 10);
        inputTextWithValue("INPUT_LASTNAME", lastname, 10);
        inputTextWithValue("INPUT_POSTALCODE", postalcode, 10);
      }

      public void finishCheckout() {
        clickElement("BTN_CONTINUE", 10);
        clickElement("BTN_FINISH", 10);
      }

      public void validateSuccessCheckout() {
        validateElementDisplayed("COMPLETE_CHECKOUT_TXT", 10);
        validateElementDisplayed("ICON_COMPLETE_CHECKOUT", 10);
        validateElementWithText("COMPLETE_CHECKOUT_TXT", "Thank you for your order!");

      }

      public void verifyFilterDescending() {

           Collections.sort(priceBeforeSort, Collections.reverseOrder());
           Assert.assertEquals(priceBeforeSort, priceAfterSort);

          }
      }
