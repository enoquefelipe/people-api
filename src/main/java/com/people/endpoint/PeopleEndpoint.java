package com.people.endpoint;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.people.model.Person;
import com.people.service.PeopleService;

@RestController
@RequestMapping("/api")
public class PeopleEndpoint {

	@Autowired
	private PeopleService service;

	@GetMapping("/people")
	public @ResponseBody List<Person> getAllPerson() {
		return (List<Person>) service.findAll();
	}

	@PostMapping("/register")
	public ResponseEntity<?> registerPerson(@RequestBody Person person, UriComponentsBuilder uriBuilder) {
		HttpHeaders headers = createURI(uriBuilder, service.save(person));
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	@GetMapping(value = "person/{id}")
	public ResponseEntity<?> findPerson(@PathVariable String id) {
		Optional<Person> person = service.findById(id);
		return Optional.empty().equals(person) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(person, HttpStatus.OK);
	}

	@DeleteMapping(value = "person/{id}")
	public ResponseEntity<?> removePerson(@PathVariable String id) {

		if (!existPerson(id)) {
			service.deleteById(id);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}

	private HttpHeaders createURI(UriComponentsBuilder uriBuilder, Person person) {
		URI location = uriBuilder.path("/api/person/{id}").buildAndExpand(person.getId()).toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(location);
		return headers;
	}

	private boolean existPerson(String id) {
		return Optional.empty().equals(service.findById(id));
	}

}
