package testframework;

import java.io.File;
import java.net.URL;

public class DriverFactory
{
	public String getOS()
	{
		String os = System.getProperty("os.name").toUpperCase();
		
		if (os.contains("WINDOWS")) {
			os = "WINDOWS";
		}
		else if (os.contains("MAC")) {
			os = "MAC";
		}
		else if (os.contains("LINUX")) {
			os = "LINUX";
		}
		
		return os;
	}
	
	public String getChromeDriverExecutable()
	{
		String chromedriverExecutable = "chromedriver";
		
		String os = getOS();
		switch (os)
		{
			case "WINDOWS":
				chromedriverExecutable = "chromedriver.exe";
				break;
			case "MAC": 
				chromedriverExecutable = "chromedriver";
				break;
			case "LINUX": 
				chromedriverExecutable = "chromdriver";
				break;
			default: 
				throw new RuntimeException("unknown OS: " + os);
		}
		
		return chromedriverExecutable;
	}
	
	public String getPathToChromeDriver()
	{
		String chromeDriverExecutable = getChromeDriverExecutable();
		
		ClassLoader classloader = this.getClass().getClassLoader();
		URL url = classloader.getResource(chromeDriverExecutable);
		
		File file = new File(url.getFile());
		System.out.println("file:" + file.getAbsolutePath());
		
		return file.getAbsolutePath();
	}
	
	public void setChromeDriver()
	{
		System.setProperty("webdriver.chrome.driver", getPathToChromeDriver());
	}
	
}
