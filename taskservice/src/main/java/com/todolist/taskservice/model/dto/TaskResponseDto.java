package com.todolist.taskservice.model.dto;

import java.util.Optional;

import org.jetbrains.annotations.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskResponseDto {
  private String id;
  private String summary;
  private String description;
  private Integer status;
  private String creator;
  private Optional<String> priority = Optional.empty();
  private Optional<String> assignee = Optional.empty();
  private Long creationDate;
  private Optional<Long> deadline = Optional.empty();

  private TaskResponseDto(@NotNull Builder builder) {
    this.id = builder.id;
    this.summary = builder.summary;
    this.description = builder.description;
    this.status = builder.status;
    this.creator = builder.creator;
    this.priority = builder.priority;
    this.assignee = builder.assignee;
    this.creationDate = builder.creationDate;
    this.deadline = builder.deadline;
  }

  public String getId() {
    return id;
  }

  public String getSummary() {
    return summary;
  }

  public String getDescription() {
    return description;
  }

  public Integer getStatus() {
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

  public Long getCreationDate() {
    return creationDate;
  }

  public Optional<Long> getDeadline() {
    return deadline;
  }

  public static class Builder {
    private String id;
    private String summary;
    private String description;
    private Integer status;
    private String creator;
    private Optional<String> priority = Optional.empty();
    private Optional<String> assignee = Optional.empty();
    private Long creationDate;
    private Optional<Long> deadline = Optional.empty();

    public Builder() {
    }

    public Builder(TaskResponseDto taskResponseDto) {
      this.id = taskResponseDto.getId();
      this.summary = taskResponseDto.getSummary();
      this.description = taskResponseDto.getDescription();
      this.status = taskResponseDto.getStatus();
      this.creator = taskResponseDto.getCreator();
      this.priority = taskResponseDto.getPriority();
      this.assignee = taskResponseDto.getAssignee();
      this.creationDate = taskResponseDto.getCreationDate();
      this.deadline = taskResponseDto.getDeadline();
    }

    public Builder id(String val) {
      this.id = val;
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

    public Builder status(Integer val) {
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

    public Builder creationDate(Long val) {
      this.creationDate = val;
      return this;
    }

    public Builder deadline(Long val) {
      this.deadline = Optional.of(val);
      return this;
    }

    public TaskResponseDto build() {
      return new TaskResponseDto(this);
    }
  }
}
