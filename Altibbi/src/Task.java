import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Task extends taskURL {
	WebDriver driver = new ChromeDriver();
	SoftAssert myassert = new SoftAssert();
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	Actions actions = new Actions(driver);

	@BeforeTest
	public void befor() throws InterruptedException {
		driver.get(pageURLString);
		Thread.sleep(1000);
		driver.manage().window().maximize();
	}

	@Test(priority = 1)
	public void testquestionFrom() {

		driver.findElement(By.xpath(questionContainer));
		String actualQuestion = driver.findElement(By.xpath(questionFrom)).getText();
//		System.out.println("This is testquestionFrom ->" + actualQuestion);
		myassert.assertNotNull(actualQuestion);
		myassert.assertAll();
	}

	@Test(priority = 2) // it's not a button it's a <a></a>
	public void testAnswerButton() {
		String actualAnswerButton = driver.findElement(By.xpath(A_Button)).getText();
//		System.out.println("this is testAnswerButton ->" + actualAnswerButton);
		myassert.assertEquals(actualAnswerButton, expectedAnswerButton, "TestUrl&syntax");
		String actualURL = driver.findElement(By.xpath(A_Button)).getAttribute("href");
		myassert.assertNotNull(actualURL, "The URL is null");
		myassert.assertAll();

	}

	// // not important Test !
//	
//	@Test()
//	public void test() {
//		String l = driver.findElement(By.cssSelector(classtime)).findElement(By.tagName("time")).getText().trim();
//		System.out.println(l + "***********No Time*************");
//		myassert.assertNotNull(l);
//		myassert.assertAll();
//
//	}

	@Test(priority = 3)
	public void testCategory() throws InterruptedException {
		driver.findElement(By.xpath(categoryName)).click();
		List<WebElement> elements = driver.findElements(By.xpath(contentOfList));
		for (int i = 0; i < elements.size(); i++) {
			elements.get(i).click();
			Thread.sleep(200);// it doesn't work when i add implicitlyWait i don't know why
			driver.findElement(By.xpath(E_Category)).click();
			Thread.sleep(200);
			elements = driver.findElements(By.xpath(contentOfList));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
			String categoryString = driver.findElement(By.className("category-info")).getText();
			String expectedString = elements.get(i).getText();
			myassert.assertEquals(categoryString, expectedString, "Category Test!!!");
//			System.out.println("this is testCategory ->" + "\n Actual: " + categoryString + " \n Expect: " + expectedString);

			if (i == 1) {
				break;
			}

		}
		myassert.assertAll();

	}

	@Test(priority = 4)
	public void testDoctorName() {
		String name = driver.findElement(By.className(docName)).getText();
		// myassert.assertNull(name);
//		System.out.println("this is testDoctorName ->" + name);
		myassert.assertNotNull(name);
		myassert.assertAll();

	}

	//

	@Test(priority = 5) // not important test!
	public void testquestion() {
//		String question = driver.findElement(By.xpath(questionContainer))
//				.findElement(By.xpath("//*[@id=\"right-side\"]/div[3]/article[1]/div/div[2]"))
//				.findElement(By.tagName("h2")).getText();
		String question = driver.findElement(By.className(questionText)).getText();

//		System.out.println("This is testquestion ->" + question);
		// myassert.assertNull(question);
		myassert.assertNotNull(question);
		myassert.assertAll();
	}

	@Test(priority = 6) // when i try to getAttribute("src") it always return null !
	public void testimg() {
		String img = driver.findElement(By.className(imgLink)).findElement(By.tagName("a"))
				.findElement(By.tagName("img")).getAttribute("class");
//		System.out.println("this is testimg ->" + img);
		// myassert.assertNull(img);
		myassert.assertNotNull(img);
		myassert.assertAll();
	}

	@Test(priority = 7) // this is a problem any change of the tags will destroy everything
	public void testHaveQuestionBox() {
		WebElement box_article = driver.findElement(By.id(haveQbox)).findElement(By.tagName("article"));
		WebElement message_img = driver.findElement(By.id(haveQbox)).findElement(By.tagName("article"))
				.findElement(By.tagName("img"));
		WebElement haveQuestion_h3 = driver.findElement(By.id(haveQbox)).findElement(By.tagName("article"))
				.findElement(By.tagName("h3"));
		WebElement tesxt_p = driver.findElement(By.id(haveQbox)).findElement(By.tagName("article"))
				.findElement(By.tagName("p"));
		WebElement insertbutton_a = driver.findElement(By.id(haveQbox)).findElement(By.tagName("article"))
				.findElement(By.tagName("a"));

//		myassert.assertNull(box_article);
//		myassert.assertNull(message_img);		
//		myassert.assertNull(haveQuestion_h3);
//		myassert.assertNull(tesxt_p);
//		myassert.assertNull(insertbutton_a);

		myassert.assertNotNull(box_article);
		myassert.assertNotNull(message_img);
		myassert.assertNotNull(haveQuestion_h3);
		myassert.assertNotNull(tesxt_p);
		myassert.assertNotNull(insertbutton_a);
		myassert.assertAll();

	}

	@Test(priority = 8)
	public void testSeinaBox() {
		WebElement sinaText = driver.findElement(By.xpath(sinaBox)).findElement(By.tagName("div"));
		WebElement sinaButton = driver.findElement(By.xpath(sinaBox)).findElement(By.tagName("a"));

//		myassert.assertNull(sinaButton);
//		myassert.assertNull(sinaText);

		myassert.assertNotNull(sinaButton);
		myassert.assertNotNull(sinaText);

		myassert.assertAll();

	}

	@Test(priority = 9)
	public void testSuggestedArticles() {
		WebElement upperText = driver.findElement(By.id(suggestedarticles)).findElement(By.tagName("div"))
				.findElement(By.className("d-flex"));
		WebElement article = driver.findElement(By.id(suggestedarticles)).findElement(By.tagName("div"))
				.findElement(By.className("row"));
		WebElement link = driver.findElement(By.id(suggestedarticles)).findElement(By.tagName("div"))
				.findElement(By.tagName("a"));

//		myassert.assertNull(upperText);
//		myassert.assertNull(article);
//		myassert.assertNull(link);

		myassert.assertNotNull(upperText);
		myassert.assertNotNull(article);
		myassert.assertNotNull(link);

		myassert.assertAll();

	}

	@Test(priority = 10)
	public void testVideoContainer() {
		WebElement title = driver.findElement(By.id(videosContainer)).findElement(By.tagName("div"))
				.findElement(By.tagName("header"));
		WebElement videos = driver.findElement(By.id(videosContainer)).findElement(By.tagName("div"))
				.findElement(By.className("row"));
		WebElement viewAll = driver.findElement(By.id(videosContainer)).findElement(By.tagName("div"))
				.findElement(By.className("visible-xs"));

//		myassert.assertNull(title);
//		myassert.assertNull(videos);
//		myassert.assertNull(viewAll);

		myassert.assertNotNull(title);
		myassert.assertNotNull(videos);
		myassert.assertNotNull(viewAll);

		myassert.assertAll();

	}

	@Test(priority = 11) // how to know that these questions are related?
	public void testquestionanswer() throws InterruptedException {

		String expectedquestion = driver.findElement(By.className(questionText)).getText();
//		System.out.println(expectedquestion + "**************");

		WebElement element = driver.findElement(By.xpath(A_Button)); // actualAnswerButton
		actions.moveToElement(element).click().perform();

		WebElement actualQuestionElement = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(actualQuestion)));

		String actualquestion = driver.findElement(By.xpath(actualQuestion)).getText();
//		System.out.println(actualquestion + "##############");
		myassert.assertEquals(actualquestion, expectedquestion);
//		System.out.println("testquestionanswer ->" + "\n Actual : " + actualquestion + "\n Expect : " + expectedquestion);

		myassert.assertAll();

	}

	@AfterTest
	public void after() {
		driver.quit();
	}

}
