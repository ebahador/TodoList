package com.todolist.taskservice.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import jakarta.validation.constraints.NotNull;
import java.util.Optional;

@JsonDeserialize(builder = CreateTaskRequestDto.Builder.class)
public class CreateTaskRequestDto {
  private String id;
  private String summary;
  private String description;
  private String status;
  private String creator;
  private Optional<String> priority = Optional.empty();
  private Optional<String> assignee = Optional.empty();
  private Optional<Long> deadline = Optional.empty();

  private CreateTaskRequestDto(@NotNull Builder builder) {
    this.id = builder.id;
    this.summary = builder.summary;
    this.description = builder.description;
    this.status = builder.status;
    this.creator = builder.creator;
    this.priority = builder.priority;
    this.assignee = builder.assignee;
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

  public Optional<Long> getDeadline() {
    return deadline;
  }

  @JsonPOJOBuilder(withPrefix = "")
  public static class Builder {
    private String id;
    private String summary;
    private String description;
    private String status;
    private String creator;
    private Optional<String> priority = Optional.empty();
    private Optional<String> assignee = Optional.empty();
    private Optional<Long> deadline = Optional.empty();

    public Builder() {}

    public Builder(CreateTaskRequestDto createTaskRequestDto) {
      this.id = createTaskRequestDto.id;
      this.summary = createTaskRequestDto.summary;
      this.description = createTaskRequestDto.description;
      this.status = createTaskRequestDto.status;
      this.creator = createTaskRequestDto.creator;
      this.priority = createTaskRequestDto.priority;
      this.assignee = createTaskRequestDto.assignee;
      this.deadline = createTaskRequestDto.deadline;
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

    public Builder deadline(long val) {
      this.deadline = Optional.of(val);
      return this;
    }

    public CreateTaskRequestDto build() {
      return new CreateTaskRequestDto(this);
    }
  }

  @Override
  public String toString() {
    return "CreateTaskRequestDto{"
        + "id='"
        + id
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
        + ", deadline="
        + deadline
        + '}';
  }
}
