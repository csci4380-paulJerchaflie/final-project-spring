/**
 * 
 */
package csci4380.finalp.cats.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import csci4380.finalp.cats.jpa.model.Cat;
import csci4380.finalp.cats.jpa.repo.CatRepository;
import csci4380.finalp.dogs.mongodb.model.Dog;

@RestController
@RequestMapping("rest/v1/cats")
public class CatRestController {
	@Autowired CatRepository catRepository;


	@RequestMapping("/echoMessage") 
	public String echoMessage(@RequestParam(value="msg", defaultValue="Hello") String message) {
		return "echoMessage echoed: " + message;
	}

	
	@GetMapping("")
	public Page<Cat> findAll(@RequestParam(defaultValue="0") int page, @RequestParam(value="rowsPerPage", defaultValue="5") int size) {
		Page<Cat> catsPage = catRepository.findAll(new PageRequest(page, size));
		return catsPage;
	}

	@GetMapping("/all")
	public  List<Cat> findAll() {
		List<Cat> cats = catRepository.findAll();
		return cats;
	}


	@PostMapping("")
	public  Optional<Cat> save(@RequestBody final Cat cat) {
		Cat savedCat = catRepository.save(cat);
		return catRepository.findById(savedCat.getPetId());
	}
	
	@GetMapping("/petId/{petId}")
	public  Optional<List<Cat>> findByPetId(@PathVariable Integer petId) {
		Optional<List<Cat>> cats = catRepository.findByPetId(petId);
		return cats;
	}
	
	@GetMapping("/byNameOrOwnerName/{name}/{ownerName}")
	public  Optional<List<Cat>> findByNameOrOwnerName(@PathVariable String name,@PathVariable String ownerName) {
		Optional<List<Cat>> cats = catRepository.findByNameOrOwnerName(name,ownerName);
		return cats;
	}
	
	@DeleteMapping("petId/{petId}")
	public  void deleteById(@PathVariable("petId") Integer Id) {
		catRepository.deleteById(Id);
	}
	

	@GetMapping("/findByTypeIgnoreCaseQuery/{catType}")
	public  Cat findByTypeIgnoreCaseQuery(@PathVariable String catType) {
		Cat cat = catRepository.findByTypeAllIgnoreCase(catType);
		return cat;
	}
	
	
	@GetMapping("/findByNameIgnoreCaseQuery/{catName}")
	public  Cat findByNameIgnoreCaseQuery(@PathVariable String catName) {
		Cat cat = catRepository.findByNameAllIgnoreCase(catName);
		return cat;
	}
	
}
