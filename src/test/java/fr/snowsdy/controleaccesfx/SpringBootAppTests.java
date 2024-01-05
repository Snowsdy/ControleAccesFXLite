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
			AbstractCustomLogger logger1 = CustomLoggerFactory.getInfoLogger("Hello World 1 !");
			System.out.println(logger1);
		}, "Thread 1");

		Thread thread2 = new Thread(() -> {
			AbstractCustomLogger logger2 = CustomLoggerFactory.getWarningLogger("Hello World 2 !");
			System.out.println(logger2);
		}, "Thread 2");

		Thread thread3 = new Thread(() -> {
			AbstractCustomLogger logger3 = CustomLoggerFactory.getDangerLogger("Hello World !");
			System.out.println(logger3);
		}, "Thread 3");

		thread1.start();
		thread2.start();
		thread3.start();
	}

}
