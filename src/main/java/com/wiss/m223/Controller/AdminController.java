package com.wiss.m223.Controller;

import com.wiss.m223.Model.Question;
import com.wiss.m223.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/admin")
public class AdminController {


    @GetMapping("")
	public ResponseEntity<String> getAdmin() {
		return new ResponseEntity<>("Admin", HttpStatus.OK);
	}
}
