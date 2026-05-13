package tandraym.edu.airportdemo;

import org.springframework.boot.SpringApplication;

public class TestAirportDemoApplication {

    public static void main(String[] args) {
        SpringApplication.from(AirportDemoApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
