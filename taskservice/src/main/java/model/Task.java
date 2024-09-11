package model;

import java.util.Optional;

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

  public Task(
      String taskId,
      String summary,
      String description,
      String status,
      String creator,
      Optional<String> priority,
      Optional<String> assignee,
      long creationDate,
      Optional<Long> deadline) {
    this.taskId = taskId;
    this.summary = summary;
    this.description = description;
    this.status = status;
    this.creator = creator;
    this.priority = priority;
    this.assignee = assignee;
    this.creationDate = creationDate;
    this.deadline = deadline;
  }

  public String getTaskId() {
    return taskId;
  }

  public void setTaskId(String taskId) {
    this.taskId = taskId;
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getCreator() {
    return creator;
  }

  public void setCreator(String creator) {
    this.creator = creator;
  }

  public Optional<String> getPriority() {
    return priority;
  }

  public void setPriority(String priority) {
    this.priority = Optional.ofNullable(priority);
  }

  public Optional<String> getAssignee() {
    return assignee;
  }

  public void setAssignee(String assignee) {
    this.assignee = Optional.ofNullable(assignee);
  }

  public long getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(long creationDate) {
    this.creationDate = creationDate;
  }

  public Optional<Long> getDeadline() {
    return deadline;
  }

  public void setDeadline(Long deadline) {
    this.deadline = Optional.ofNullable(deadline);
  }
}
