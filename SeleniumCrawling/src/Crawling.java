import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

class PlaceVO {
	private String name;
	private String address;
	private String category;

	public PlaceVO(String name, String address, String category) {
		super();
		this.name = name;
		this.address = address;
		this.category = category;
	}

	@Override
	public String toString() {
		return name + " " + address + " " + category;
	}

	public static PlaceVO crawlPlaceInfo(WebDriver driver) {
		String name;
		String address;
		String category;

		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.cssSelector("iframe#entryIframe"))); // iframe 변경

		name = driver.findElement(By.cssSelector("span.Fc1rA")).getText();
		address = driver.findElement(By.cssSelector("span.LDgIH")).getText();
		category = driver.findElement(By.cssSelector("span.DJJvD")).getText();

		driver.switchTo().defaultContent();
		return new PlaceVO(name, address, category);
	}

	public static boolean nextPage(WebDriver driver) {
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.cssSelector("iframe#searchIframe")));

		int currentPage = Integer.parseInt(driver.findElement(By.cssSelector("a.mBN2s.qxokY")).getText());
		System.out.println(currentPage);
		String nextPageSelector="a.mBN2s.qxokY+a.mBN2s";
		try {
			driver.findElement(By.cssSelector(nextPageSelector)).click();
		} catch (NoSuchElementException e) {
			return false;
		} finally {
			driver.switchTo().defaultContent();
		}
		return true;
	}
}

public class Crawling {

	public static void main(String[] args) {
		// 1. WebDriver와 ChromeDriver 설정
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Aiden\\Desktop\\crawl\\chromedriver-win64\\chromedriver.exe"); // chromedriver.exe 경로
		WebDriver driver = new ChromeDriver(); // 브라우저 열기

		// 2. 웹 페이지 접속
		String baseUrl = "https://map.naver.com/p/search/%EA%B0%95%EC%95%84%EC%A7%80?c=15.00,0,0,0,dh"; // 크롤링할 url

		// 3. 데이터 추출
		ArrayList<PlaceVO> datas = new ArrayList<>();

		try {
			driver.get(baseUrl); // url 이동
			driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000)); // ajax를 위해서 0.5초 대기
			do {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				driver.switchTo().frame(driver.findElement(By.cssSelector("iframe#searchIframe"))); // iframe 변경 ->
																									// html안에
																									// html을 삽입하는 경우
																									// iframe을
																									// 씀.
				List<WebElement> elements = driver.findElements(By.cssSelector("span.YwYLL"));
				driver.switchTo().defaultContent();
				Iterator<WebElement> itr = elements.iterator();

				while (itr.hasNext()) {
					driver.switchTo().defaultContent();
					driver.switchTo().frame(driver.findElement(By.cssSelector("iframe#searchIframe")));
					WebElement next = itr.next();
					next.click();
					driver.switchTo().defaultContent();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					datas.add(PlaceVO.crawlPlaceInfo(driver));
				}
			} while (PlaceVO.nextPage(driver));

		} finally {
			// 4. WebDriver 종료
			System.out.println(datas);
			driver.quit();
		}
	}

}
