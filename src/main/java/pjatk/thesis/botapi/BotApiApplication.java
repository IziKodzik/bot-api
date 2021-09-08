package pjatk.thesis.botapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pjatk.thesis.botapi.security.domain.CBotApiRole;
import pjatk.thesis.botapi.security.domain.BotApiUser;
import pjatk.thesis.botapi.security.service.BotApiSecurityService;

@SpringBootApplication
public class BotApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BotApiApplication.class, args);
	}

	@Bean
	CommandLineRunner run(BotApiSecurityService securityService){
		return args -> {
			securityService.saveRole(new CBotApiRole( "ROLE_USER"));
			securityService.saveRole(new CBotApiRole( "ROLE_ADMIN"));
			securityService.saveRole(new CBotApiRole( "ROLE_MANAGER"));

			securityService.saveUser(new BotApiUser( "Piotr","1234"));
			securityService.saveUser(new BotApiUser( "Mati","1234"));
			securityService.saveUser(new BotApiUser( "Szimi","1234"));
			securityService.saveUser(new BotApiUser( "Tomi","1234"));

			securityService.addRoleToUser("Piotr","ROLE_ADMIN");
			securityService.addRoleToUser("Mati","ROLE_MANAGER");
			securityService.addRoleToUser("Mati","ROLE_USER");
			securityService.addRoleToUser("Tomi","ROLE_USER");


		};
	}

}
