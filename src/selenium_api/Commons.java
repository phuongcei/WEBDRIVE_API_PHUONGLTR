package selenium_api;

import java.util.Random;

public class Commons {

	public static String randomEmail() {
		String randomMail;

		String prefix = "testautomation_";
		int max = 10000;
		int min = 1000;

		int subfix;

		Random rd = new Random();
		subfix = rd.nextInt(max - min) + min;

		randomMail = prefix + subfix + "@gmail.com";

		System.out.println("Random email: " + randomMail);
		return randomMail;
	}

}
