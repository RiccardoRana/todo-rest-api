package com.todo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.todo.model.ToDo;
import com.todo.repository.ToDoRepository;
@Service
public class ToDoService {
	
	@Autowired
	private ToDoRepository toDoRepository;
	
	public Optional<ToDo> findById(int toDoId) {
		return toDoRepository.findById(toDoId);
	}

	public ToDo createToDo(ToDo toDo) {
		return toDoRepository.save(toDo);
	}
	
	public List<ToDo> getAllToDos() {
		return toDoRepository.findAll();
	}


	public void deleteToDo(int toDoId) {
		toDoRepository.deleteById(toDoId);
	}




}
