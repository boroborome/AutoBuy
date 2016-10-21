package driver;

public class Console {

	public static void main(String[] args) {
		ChromeService.getInstance().start();
		System.out.println(ChromeService.getInstance().getUrl());
	}

}
