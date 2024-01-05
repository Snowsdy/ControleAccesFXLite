package fr.snowsdy.controleaccesfx;

import fr.snowsdy.controleaccesfx.logger.AbstractCustomLogger;
import fr.snowsdy.controleaccesfx.logger.CustomLoggerFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootAppTests {

	@Test
	void singletonLogger() {
		Thread thread1 = new Thread(() -> {
			AbstractCustomLogger logger1 = CustomLoggerFactory.getInfoLogger("Info Log");
			System.out.println(logger1);
		}, "Thread 1");

		Thread thread2 = new Thread(() -> {
			AbstractCustomLogger logger2 = CustomLoggerFactory.getWarningLogger("Warning Log");
			System.out.println(logger2);
		}, "Thread 2");

		Thread thread3 = new Thread(() -> {
			AbstractCustomLogger logger3 = CustomLoggerFactory.getDangerLogger("Danger Log");
			System.out.println(logger3);
		}, "Thread 3");

		Thread thread4 = new Thread(() -> {
			AbstractCustomLogger logger4 = CustomLoggerFactory.getNetworkLogger("Network enabled.");
			System.out.println(logger4);
		}, "Thread 3");

		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
	}

}
