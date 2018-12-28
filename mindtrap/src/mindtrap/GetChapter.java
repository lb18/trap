package mindtrap;
import org.openqa.selenium.By;

public class GetChapter extends Initialize{
	void openFolder()
	{
		int tot_folders = driver.findElements(By.xpath("//div[@class='topic-level-1']")).size();
		for (int i = 1; i <= tot_folders; i++)
		{
			String cur_folder_xpath = "//div[@class='topic-level-1']["+i+"]";
		}
	}
}
