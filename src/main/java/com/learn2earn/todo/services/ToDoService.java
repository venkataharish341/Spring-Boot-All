package com.learn2earn.todo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn2earn.todo.models.ToDo;
import com.learn2earn.todo.repositories.ToDoRepository;

@Service
public class ToDoService {

	@Autowired
	private ToDoRepository todoRepo;

	public List<ToDo> getAllTodosForUser(String username) {
		return todoRepo.findByUsername(username);
	}

	public Optional<ToDo> getTodo(int id) {
		return todoRepo.findById(id);
	}

	public ToDo saveTodo(ToDo todo) {
		return todoRepo.save(todo);
	}

	public void deleteTodo(int id) {
		todoRepo.deleteById(id);
	}

	public void deleteTodosForUser(String username) {
		todoRepo.removeByUsername(username);
	}
	
	public List<String> getDistinctUsers() {
		return todoRepo.findDistinctUsers();
	}
}
