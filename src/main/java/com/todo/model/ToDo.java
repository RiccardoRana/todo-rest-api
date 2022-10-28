package com.todo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="toDo")
public class ToDo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="todo_id")
	private int id;
	@NotNull(message = "NameToDo must not be null")
	private String nameToDo;
	
	private boolean done = false;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNameToDo() {
		return nameToDo;
	}

	public void setNameToDo(String nameToDo) {
		this.nameToDo = nameToDo;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}
	
	
	
	
	

}
