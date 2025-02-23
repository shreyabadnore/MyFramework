package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil =new ElementUtil(driver);

	}

	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");

	
	
	
	
	@Step("get login page title")
	public String getLoginPageTitle() {
		
		String title=eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.DEFAULT_TIME_OUT);
	
		System.out.println("Login Page Title is" + title);
		
		ChainTestListener.log("loginPage Title"+title);
		return title;
	}
	
	@Step("get loginpage url")

	public String getLoginPageURL() {
		

		String url=eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, AppConstants.DEFAULT_TIME_OUT);

		System.out.println("Login Page url is" + url);
		return url;
	}
	@Step("forgot pwd link is displayed")
	public boolean isForgotPwdLinkExist() {

		return eleUtil.doIsElementDisplayed(forgotPwdLink);

	}
	
	@Step("Login with username:{0} and password: {1}")

	public HomePage doLogin(String username, String pwd) {//next landing page 
		System.out.println("Your app credentials are" + username + " :" + pwd);
		eleUtil.waitForElementVisible(emailId, AppConstants.SHORT_TIME_OUT).sendKeys(username);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		

		return new HomePage(driver);//next landing page obj

	}

}
