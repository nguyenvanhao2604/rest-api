package restapi.com.vn.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import restapi.com.vn.domain.Work;
import restapi.com.vn.repository.WorkRepository;

@RestController
@RequestMapping("/api/v1")
public class WorkController {

	@Autowired
	WorkRepository workRepository;

	@PostMapping("/work")
	public ResponseEntity<Work> addWork(@Validated @RequestBody Work work) {
		try {
			Work _work = workRepository.save(work);
			return new ResponseEntity<>(_work, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/work/{id}")
	public ResponseEntity<Work> editWork(@PathVariable(value = "id") Long id,
			@Validated @RequestBody Work workDetails) {
		Optional<Work> work = workRepository.findById(id);
		if (work.isPresent()) {
			Work _work = work.get();
			_work.setWorkName(workDetails.getWorkName());
			_work.setStartingDate(workDetails.getStartingDate());
			_work.setEndingDate(workDetails.getEndingDate());
			_work.setStatus(workDetails.getStatus());
			return new ResponseEntity<>(workRepository.save(_work), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("/work/{id}")
	public ResponseEntity<Work> deleteWork(@PathVariable(value = "id") Long id) {
		try {
			workRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
