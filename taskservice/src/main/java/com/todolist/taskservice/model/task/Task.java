package com.todolist.taskservice.model.task;

import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public class Task {
  private String taskId;
  private String summary;
  private String description;
  private String status;
  private String creator;
  private Optional<String> priority = Optional.empty();
  private Optional<String> assignee = Optional.empty();
  private long creationDate;
  private Optional<Long> deadline = Optional.empty();

  public Task(@NotNull Builder builder) {

    this.taskId = builder.taskId;
    this.summary = builder.summary;
    this.description = builder.description;
    this.status = builder.status;
    this.creator = builder.creator;
    this.priority = builder.priority;
    this.assignee = builder.assignee;
    this.creationDate = builder.creationDate;
    this.deadline = builder.deadline;
  }

  public String getTaskId() {
    return taskId;
  }

  public String getSummary() {
    return summary;
  }

  public String getDescription() {
    return description;
  }

  public String getStatus() {
    return status;
  }

  public String getCreator() {
    return creator;
  }

  public Optional<String> getPriority() {
    return priority;
  }

  public Optional<String> getAssignee() {
    return assignee;
  }

  public long getCreationDate() {
    return creationDate;
  }

  public Optional<Long> getDeadline() {
    return deadline;
  }

  public static class Builder {
    private String taskId;
    private String summary;
    private String description;
    private String status;
    private String creator;
    private Optional<String> priority = Optional.empty();
    private Optional<String> assignee = Optional.empty();
    private long creationDate;
    private Optional<Long> deadline = Optional.empty();

    public Builder() {}

    public Builder(@NotNull Task task) {
      this.taskId = task.taskId;
      this.summary = task.summary;
      this.description = task.description;
      this.status = task.status;
      this.creator = task.creator;
      this.priority = task.priority;
      this.assignee = task.assignee;
      this.creationDate = task.creationDate;
      this.deadline = task.deadline;
    }

    public Builder taskId(String val) {
      this.taskId = val;
      return this;
    }

    public Builder summary(String val) {
      this.summary = val;
      return this;
    }

    public Builder description(String val) {
      this.description = val;
      return this;
    }

    public Builder status(String val) {
      this.status = val;
      return this;
    }

    public Builder creator(String val) {
      this.creator = val;
      return this;
    }

    public Builder priority(String val) {
      this.priority = Optional.ofNullable(val);
      return this;
    }

    public Builder assignee(String val) {
      this.assignee = Optional.ofNullable(val);
      return this;
    }

    public Builder creationDate(long val) {
      this.creationDate = val;
      return this;
    }

    public Builder deadline(long val) {
      this.deadline = Optional.of(val);
      return this;
    }

    public Task build() {
      return new Task(this);
    }
  }

  @Override
  public String toString() {
    return "Task{"
        + "taskId='"
        + taskId
        + '\''
        + ", summary='"
        + summary
        + '\''
        + ", description='"
        + description
        + '\''
        + ", status='"
        + status
        + '\''
        + ", creator='"
        + creator
        + '\''
        + ", priority="
        + priority
        + ", assignee="
        + assignee
        + ", creationDate="
        + creationDate
        + ", deadline="
        + deadline
        + '}';
  }
}
