package com.platform.web.stepdef;

import com.platform.web.page.BasePage;
import io.cucumber.java8.En;

public class ProductStepDef extends BasePage implements En {


    public ProductStepDef() {

            And(
                "^user choose filter \"([^\"]*)\"$" ,
                (String dropdownText) -> {
                    ProductPage().getPriceBeforeSort();
                    ProductPage().selectDropDownPrice(dropdownText);
                    ProductPage().getPriceAfterSort();
                });

            And(
                "^user add to cart product with price \"([^\"]*)\"$" ,
                (String price) -> {
                    ProductPage().addProductToCart(price);
                });

            And(
                "^user continue checkout by click cart icon$" ,
                () -> {
                    ProductPage().clickCartIcon();
                });

            And(
                "^user input checkout information by input \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$" ,
                (String firstname, String lastname, String postalcode) -> {
                    ProductPage().inputCheckoutInformation(firstname, lastname, postalcode);
                });

            And(
                "^user finish checkout$" ,
                () -> {
                    ProductPage().finishCheckout();
                });

            Then(
                "^user can validate the filter applied correctly by descending order$" ,
                () -> {
                    ProductPage().verifyFilterDescending();
                });


            Then(
                "^checkout should be success$" ,
                () -> {
                    ProductPage().validateSuccessCheckout();
                });

        }
    }
