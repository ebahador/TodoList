package com.todolist.taskservice.model.task;

public enum TaskStatus {
  TODO("TODO"),
  IN_PROGRESS("IN_PROGRESS"),
  DONE("DONE");

  private final String value;

  TaskStatus(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}