import java.io.File;
import java.text.SimpleDateFormat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
	public static void main(String[] args) {
		//final String PATH = "(chromedriverの格納されている場所)";
        //System.setProperty("webdriver.chrome.driver", PATH);

		//いったん　きまぐれクック
		final String URL = "https://www.youtube.com/watch?v=luPejFMfRBU";

		//ファイルパス指定
		String filename = "C:\\work\\data\\hoge.txt";

		//ログ出力用データフォーマット
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSSSSSSSS");

		//ファイルアクセス
		File fstart = new File(filename);

		//ファイルの最終更新日を取得して格納
		Long lastPushedTime = fstart.lastModified();
		System.out.println(sdf.format(lastPushedTime));

		WebDriver driver = new ChromeDriver();
        driver.get(URL);

        //WebElement element = driver.findElement(By.name("search_query"));
        //element.sendKeys("きまぐれクック");
        //element.submit();


        while(true) {
        	//File f = new File(filename);
        	if(fstart.lastModified() > lastPushedTime) {
        		WebElement btn = driver.findElement(By.className("ytp-play-button"));
        		lastPushedTime = fstart.lastModified();
        		if(btn.getAttribute("aria-label").equals("一時停止")) {
            		btn.click();
            		System.out.println(sdf.format(lastPushedTime) + " :（■）動画を停止しました。");
            	}else {
            		btn.click();
            		System.out.println(sdf.format(lastPushedTime) + " :（▷） 動画を再生しました。");
            	}

        	}

        	try {
    			Thread.sleep(100);
    		} catch (InterruptedException e) {
    			// TODO 自動生成された catch ブロック
    			e.printStackTrace();
    		}
        }

	}
}
