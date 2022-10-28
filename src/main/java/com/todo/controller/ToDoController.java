package com.todo.controller;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.todo.model.ToDo;
import com.todo.service.ToDoService;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ToDoController {

	@Autowired
	private ToDoService toDoService;

	@GetMapping("/todo")
	public List<ToDo> getAllTodos() {
		return (List<ToDo>) toDoService.getAllToDos();
	}

	@GetMapping("todo/{todo_id}")
	public ToDo getToDo(@PathVariable(value = "todo_id") int toDoId) {
		Optional<ToDo> toDo = toDoService.findById(toDoId);
		if (toDo.isPresent())
			return toDo.get();
		else
			return new ToDo();
	}

	@PostMapping("/todo")
	public ResponseEntity<Object> addNewToDo(@Valid @RequestBody ToDo toDo) {
		try {
			return new ResponseEntity<Object>(toDoService.createToDo(toDo), HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("todo/{todo_id}")
	public void deleteById(@PathVariable(value = "todo_id") int toDoId) {
		toDoService.deleteToDo(toDoId);
	}

	
	@PutMapping("/todo")
	public ResponseEntity<Object> updateToDo(@RequestBody ToDo toDo) {
		try {
			Optional<ToDo> t = toDoService.findById(toDo.getId());
			if (t.isPresent()) {
				ToDo ti = t.get();
				ti.setId(toDo.getId());
				ti.setNameToDo(toDo.getNameToDo());
				ti.setDone(toDo.isDone());
				return new ResponseEntity<Object>(toDoService.createToDo(toDo), HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>((ToDo) null, HttpStatus.NOT_FOUND);

			}
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		
		}
	}

	@PutMapping("todo/{todo_id}")
	public ResponseEntity<ToDo> setToDoDone(@PathVariable(value = "todo_id") int toDoId) {
		Optional<ToDo> t = toDoService.findById(toDoId);
		if (t.isPresent()) {
			ToDo ti = t.get();
			ti.setDone(!ti.isDone());
			return new ResponseEntity<ToDo>(toDoService.createToDo(ti), HttpStatus.OK);
		} else {
			return new ResponseEntity<ToDo>((ToDo) null, HttpStatus.NOT_FOUND);
		}
	}

}
