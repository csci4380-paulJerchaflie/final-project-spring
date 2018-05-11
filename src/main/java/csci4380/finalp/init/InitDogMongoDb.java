/**
 * 
 */
package csci4380.finalp.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import csci4380.finalp.dogs.mongodb.model.Dog;
import csci4380.finalp.dogs.mongodb.repo.DogRepository;

@Component
public class InitDogMongoDb implements CommandLineRunner {

	private DogRepository dogRepository;
	
	@Autowired
	public InitDogMongoDb(DogRepository dogRepository) {
		super();
		this.dogRepository = dogRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		Dog dogUno = new Dog(1,"Waggles", "Beagle", "Paul", "1 Microsoft Way", 21, true);
		Dog savedDogUno = dogRepository.save(dogUno);
		System.out.println("Saved dogUno");
	}

}
