package com.learn2earn.todo.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.learn2earn.todo.models.ToDo;

@Repository // Redundant but ok to have.
public interface ToDoRepository extends JpaRepository<ToDo, Integer> {
	
	public List<ToDo> findByUsername(String username);
	
	@Transactional
	public void removeByUsername(String username); // Delete operation require transaction.
	
	@Query(value = "select distinct username from task", nativeQuery = true)
	public List<String> findDistinctUsers();
}
