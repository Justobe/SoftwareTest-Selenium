import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

@RunWith(Parameterized.class)
public class Lab2 {
  private String studentId;
  private String github;
  private String passwd;
  
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  
  public Lab2(String id,String passwd,String git) {
	this.studentId = id;
	this.github = git;
	this.passwd = passwd;
  }
  
  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://121.193.130.195:8080";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
  
  @Parameters 
  public static Collection<Object[]> getData(){
	  	List<Object[]> array = new ArrayList<>();
		File csv = new File("E:\\inputgit.csv");  // CSV文件路径
	    BufferedReader br = null;
	    try
	    {
	        br = new BufferedReader(new FileReader(csv));
	    } catch (FileNotFoundException e)
	    {
	        e.printStackTrace();
	    }
	    String line = "";
	    String everyLine = "";
	    try {
	        
	    		line = br.readLine();
	            while ((line = br.readLine()) != null)  //读取到的内容给line变量
	            {
	                everyLine = line;
	                String [] temp = everyLine.split(",");
	                //System.out.println(temp[0]);
	                Object [] tempobj = new Object[]{
	                		temp[0],temp[0].substring(4,temp[0].length()),temp[2]};
	                array.add(tempobj);
	            }
	    } catch (IOException e)
	    {
	        e.printStackTrace();
	    }
		return array;
	  }
  
  @Test
  public void testLab2() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.id("name")).clear();
    driver.findElement(By.id("name")).sendKeys(this.studentId);
    driver.findElement(By.id("pwd")).clear();
    driver.findElement(By.id("pwd")).sendKeys(this.passwd);
    driver.findElement(By.id("submit")).click();
    Object gitAddress = driver.findElement(By.xpath("//*[@id='table-main']/tr[3]/td[2]")).getText();
    System.out.println(gitAddress.toString());
    assertEquals(this.github, gitAddress);
    
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }

}
