package com.learn2earn.todo.controllers;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonView;
import com.learn2earn.todo.centralerrorhandling.ConflictException;
import com.learn2earn.todo.centralerrorhandling.RecordNotFoundException;
import com.learn2earn.todo.models.ToDo;
import com.learn2earn.todo.models.View;
import com.learn2earn.todo.services.ToDoService;

@RestController
public class ToDoController {

	@Autowired
	private ToDoService todoService;

	//GET Mappings

	@GetMapping("/users")
	public ResponseEntity<List<String>> getAllDistinctUsers() {
		List<String> users = todoService.getDistinctUsers();

		if(!users.isEmpty()) {
			return new ResponseEntity<List<String>>(users, HttpStatus.OK);
		}

		throw new RecordNotFoundException("No Users found.");
	}

	// Only shows the fields with view name: View.dwarf.class
	@JsonView(View.dwarf.class) 
	@GetMapping("/users/{username}/short_todos")
	public ResponseEntity<List<ToDo>> getShortTodos(@PathVariable("username") String userName) {
		List<ToDo> todos = todoService.getAllTodosForUser(userName);

		if(todos.isEmpty()) {
			throw new RecordNotFoundException("No todos for the provided user.");
		}

		return new ResponseEntity<List<ToDo>>(todos, HttpStatus.OK);	

	}

	@GetMapping("/users/{username}/todos")
	public ResponseEntity<List<ToDo>> getTodosForUser(@PathVariable("username") String userName) {
		List<ToDo> todos = todoService.getAllTodosForUser(userName);

		if(todos.isEmpty()) {
			throw new RecordNotFoundException("No todos for the provided user.");
		}

		return new ResponseEntity<List<ToDo>>(todos, HttpStatus.OK);		
	}

	@GetMapping("/users/{username}/todos/{id}")
	public ResponseEntity<ToDo> getTodo(@PathVariable String username, @PathVariable int id) {
		Optional<ToDo> todo = todoService.getTodo(id);

		if(todo.isPresent()) {
			return new ResponseEntity<ToDo>(todo.get(), HttpStatus.OK);
		}

		throw new RecordNotFoundException("Invalid Todo Id: " + id);
	}

	//PUT Mappings

	@PutMapping("/users/{username}/todos/{id}")
	public ResponseEntity<ToDo> updateTodo(@PathVariable int id, @RequestBody ToDo todo) {
		Optional<ToDo> todoOpt = todoService.getTodo(id);

		if(todoOpt.isPresent()) {
			ToDo currentTodo = todoOpt.get();
			currentTodo.setUsername(todo.getUsername());
			currentTodo.setDescription(todo.getDescription());
			currentTodo.setCompleted(todo.isCompleted());
			currentTodo.setTargetDate(todo.getTargetDate());
			return new ResponseEntity<ToDo>(todoService.saveTodo(currentTodo), HttpStatus.OK);
		}

		throw new RecordNotFoundException("Invalid Todo Id: " + id);
	}

	// POST Mappings

	@PostMapping("/users/{username}/todos")
	public ResponseEntity<Void> createTodo(@Valid @RequestBody ToDo todo) {
		Optional<ToDo> todoOpt = todoService.getTodo(todo.getId());

		if(todoOpt.isPresent()) {
			throw new ConflictException("Resource exist");
		}

		ToDo createdTodo = todoService.saveTodo(todo);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(UriComponentsBuilder.newInstance().path("/users/{username}/todos/{id}")
				.buildAndExpand("htavva",createdTodo.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	//DELETE Mappings

	@DeleteMapping("/users/{username}/todos")
	public ResponseEntity<String> deleteTodosForUser(@PathVariable String username) {
		List<ToDo> todos = todoService.getAllTodosForUser(username);

		if(!todos.isEmpty()) {
			todoService.deleteTodosForUser(username);
			return new ResponseEntity<String>("All Todos Deleted for the given user.", HttpStatus.OK);
		}

		throw new RecordNotFoundException("No Todos for the given user.");
	}

	@DeleteMapping("/users/{username}/todos/{id}")
	public ResponseEntity<String> deleteTodo(@PathVariable String username, @PathVariable int id) {
		Optional<ToDo> todo = todoService.getTodo(id);
		if(todo.isPresent()) {
			todoService.deleteTodo(id);
			return new ResponseEntity<String>("Todo with Id: "+ id + " deleted.", HttpStatus.OK);
		}

		throw new RecordNotFoundException("Invalid Todo Id: " + id);

	}

}
