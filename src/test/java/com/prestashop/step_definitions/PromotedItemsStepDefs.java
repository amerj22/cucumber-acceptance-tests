package com.prestashop.step_definitions;

import java.util.List;
import java.util.Map;

import org.junit.Assert;

import com.prestashop.pages.HomePage;
import com.prestashop.pages.ItemPage;
import com.prestashop.utilities.Driver;
import com.prestashop.utilities.ExcelUtil;

import cucumber.api.java.en.Then;

public class PromotedItemsStepDefs {
	
	 HomePage homePage = new HomePage();
	 ItemPage itemPage = new ItemPage();

	@Then("the system should display the promted items")
	public void the_system_should_display_the_promted_items() {
		// read excel file
		ExcelUtil excelObject = new ExcelUtil("Products.xlsx", "Sheet1");

		// get value of execute, if yes

		List<Map<String, String>> allRows = excelObject.getDataList();
		
		for (int i = 0; i < allRows.size(); i++) {
			
		
		Map<String, String> row = allRows.get(i);

			String product = row.get("Product");
			if (row.get("Execute").equalsIgnoreCase("y")) {
				System.out.println("Testing " + product);
				homePage.item(product).click();
				
				//getting the expected value from the excel
				String price = row.get("Price");
				String color = row.get("Color");
				String style = row.get("Styles");
				System.out.println("Price: " + price);
				System.out.println("Color: " + color);
				System.out.println("Style: " + style);
				// actual values from UI
				String actualPrice = itemPage.price.getText();
				
				// color element has no text, the color is saved in the name attribute.
				
				String actualColor = itemPage.selectedColor.getAttribute("Name");
				String actualStyle = itemPage.style.getText();
				if(price.endsWith(".0")) {
					price = "$" + price + "0";
				}else {
					price = "$" + price;
				}
				
				Assert.assertEquals(price, actualPrice);
				Assert.assertEquals(color, actualColor);
				Assert.assertEquals(style, actualStyle);
				
				//go back to home page
				Driver.getDriver().navigate().back();
				
				excelObject.setCellData("pass", "Status", i+1);
				
				
				
				// get the product name and verify it is displayed on the home page
			//	Assert.assertTrue(homePage.item(product).isDisplayed());

			} else {
				System.out.println("Skip " + product);
				excelObject.setCellData("Skipped", "Status", i+1);
			}
		}
		
	}

	@Then("the item details should be correct")
	public void the_item_details_should_be_correct() {

	}

}
