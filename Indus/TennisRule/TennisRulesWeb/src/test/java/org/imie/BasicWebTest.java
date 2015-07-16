/**
 * 
 */
package org.imie;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * @author imie
 *
 */
public class BasicWebTest {

	WebDriver driver;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.get("http://localhost:8080/TennisRulesWeb-1.0.0-SNAPSHOT/TennisScore");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void testTitle() {
		String title = driver.getTitle();
		Assert.assertEquals(title, "Score du Tennis");
	}

	@Test
	public void test_15_0() {
		driver.findElement(By.name("reset")).click();
		driver.findElement(By.name("joueur1")).click();
		Assert.assertEquals("15-0", driver.findElement(By.id("score"))
				.getText());
	}

}
