package fr.snowsdy.controleaccesfx.config;

import fr.snowsdy.controleaccesfx.entities.AccessCard;
import fr.snowsdy.controleaccesfx.entities.Admin;
import fr.snowsdy.controleaccesfx.entities.Attribution;
import fr.snowsdy.controleaccesfx.entities.User;
import fr.snowsdy.controleaccesfx.services.AccessCardService;
import fr.snowsdy.controleaccesfx.services.AttributionService;
import fr.snowsdy.controleaccesfx.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class H2Config {

    @Bean
    CommandLineRunner commandLineRunner(
            AccessCardService accessCardService,
            UserService userService,
            AttributionService attrService
    ) {
        return args -> {
            // Access cards
            AccessCard card1 = new AccessCard("09F8G9D209R");
            AccessCard card2 = new AccessCard("VD23F9FH23D");
            AccessCard card3 = new AccessCard("T1F48319G2H");

            accessCardService.saveAll(List.of(card1, card2, card3));

            System.out.println("Access cards added !");

            // Users

            User user1 = new User("User 1 Jr.");
            Admin admin1 = new Admin("Mr. Admin n°1", "toto", "tutu");
            User user2 = new User("User 2 Jr.");
            Admin admin2 = new Admin("Mr. Admin n°2", "s0m3TH?ng", "F01f§d@93f1r3f0X");

            userService.saveAll(List.of(user1, admin1, user2, admin2));

            System.out.println("Users added !");

            // Attributions

            Attribution attr1 = new Attribution(user1, card1);
            Attribution attr2 = new Attribution(admin1, card2);
            Attribution attr3 = new Attribution(admin2, card3);

            attrService.saveAll(List.of(attr1, attr2, attr3));

            System.out.println("Attributions loaded !");
        };
    }
}
