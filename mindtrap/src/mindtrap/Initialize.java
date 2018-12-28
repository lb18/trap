package mindtrap;
import java.util.*;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Initialize {

	WebDriver driver = new ChromeDriver();
	void trapLogin()
	{
		driver.manage().window().maximize();
		driver.get("https://www.cengage.com/dashboard/#/login");
		driver.findElement(By.id("emailId")).sendKeys("mt_student_006@swlearning.com");
		driver.findElement(By.id("password")).sendKeys("Password1");
		driver.findElement(By.xpath(("//button[@class='btn btn-primary']"))).submit();
		//driver.quit();
	}
	/*void courseSearch(String str)
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement search = driver.findElement(By.xpath("//input[@name='fieldValue']"));
		wait.until(ExpectedConditions.elementToBeClickable(search)).click();
		search.sendKeys("jhj");
		//driver.findElement(By.id("findButton")).submit();
	}*/
	void findCourse(String str)
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='myCourses']/div/div[2]/ul")));
		//Map<WebElement, WebElement> courses = new HashMap<WebElement, WebElement>();
		int dashboard = driver.findElements(By.xpath("//div[@id='myCourses']/div")).size();
		for (int i = 1; i < dashboard; i++)
		{
			String cur_board_xpath = "//div[@id='myCourses']/div["+i+"]";
			int cur_board_size = driver.findElements(By.xpath(cur_board_xpath)).size();
			cur_board_xpath += "/div[2]/ul";
			int course_list_size = driver.findElements(By.xpath(cur_board_xpath)).size();		
			for (int j = 1; j <= course_list_size; j+=2)
			{
				String list_xpath = cur_board_xpath+"/li["+j+"]";
				String course_name = driver.findElement(By.xpath(list_xpath)).getText();
				//System.out.println(course_name);
				if (matchCourse(str, course_name))
				{
					j++;
					list_xpath = cur_board_xpath+"/li["+j+"]/a";
					driver.findElement(By.xpath(list_xpath)).click();
					break;
				}
			}
		}
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Take me to my course >")));
		driver.findElement(By.linkText("Take me to my course >")).click();
	}
	
	boolean matchCourse(String str, String course_name)
	{
		if (str.equals(course_name)) return true;
		return false;
	}
	
	public static void main(String args[])
	{
		Scanner read_input = new Scanner(System.in);
		String course_name = read_input.next();
		Initialize obj = new Initialize();
		obj.trapLogin();
		obj.findCourse(course_name);
	}
}