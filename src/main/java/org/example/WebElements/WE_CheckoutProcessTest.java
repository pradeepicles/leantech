package org.example.WebElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WE_CheckoutProcessTest {
	public By we_inputUsername = By.xpath("//input[@id='user-name']");
	public By we_inputPassword = By.xpath("//input[@id='password']");
	public By we_btnLogin = By.xpath("//input[@id='login-button']");
	public By we_productList = By.xpath("//div[contains(@class,'inventory_item_name')]");
	public String we_addToCart = "//div[text()='%s']/../../following-sibling::div/button";
	public By we_cartItems = By.xpath("//a[@class='shopping_cart_link']");
	public By we_itemsAddedList = By.xpath("//div[@class='inventory_item_name']");
	public By we_btnCheckout = By.xpath("//button[@id='checkout']");
	public By we_inputFirstName = By.xpath("//input[@id='first-name']");
	public By we_inputLastName = By.xpath("//input[@id='last-name']");
	public By we_inputzipCode = By.xpath("//input[@id='postal-code']");
	public By we_btnContinue = By.xpath("//input[@id='continue']");
	public By we_btnFinish = By.xpath("//button[@id='finish']");
	public By we_txtOderComplete = By.xpath("//h2[text()='Thank you for your order!']");

}
