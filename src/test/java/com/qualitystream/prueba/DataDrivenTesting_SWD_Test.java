package com.qualitystream.prueba;

import org.apache.poi.hpsf.Section;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

public class DataDrivenTesting_SWD_Test {

	private WebDriver driver;
	private WriteExcelFile writeFile;
	private ReadExcelFile readFile;
	private By searchBoxLocator = By.id("search_query_top");
	private By searchBtnLocator = By.name("submit_search");
	private By resultTextLocator = By.cssSelector("span.heading-counter");
	private Section System;

	@Before
	public void setUp() throws Exception {
		System.setProperty(Integer.parseInt("webdriver.chrome.driver"), "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		writeFile = new WriteExcelFile();
		readFile = new ReadExcelFile();

		driver.get("http://automationpractice.com");
	}

	@After
	public void tearDown() throws Exception {
		// driver.quit();
	}

	@Test
	public void test() throws IOException {
		
		//Aqu� poner la direcci�n del archivo excel de su computadora 
		//o tambi�n pueden incluirlo como parte del proyecto, la ruta ser�a como la que usamos para el chromedriver.exe (ver en el m�todo setUp arriba)
		String filepath = "C:\\Users\\kariner\\Desktop\\Test.xlsx";

		String searchText = readFile.getCellValue(filepath, "Sheet1", 0, 0);

		driver.findElement(searchBoxLocator).clear();

		driver.findElement(searchBoxLocator).sendKeys(searchText);
		driver.findElement(searchBtnLocator).click();
		String resultText = driver.findElement(resultTextLocator).getText();

		System.out.println("Page result text:" + resultText);

		readFile.readExcel(filepath, "Sheet1");

		writeFile.writeCellValue(filepath, "Sheet1", 0, 1, resultText);

		readFile.readExcel(filepath, "Sheet1");

	}

}
