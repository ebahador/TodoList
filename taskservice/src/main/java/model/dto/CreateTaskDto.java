package model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = CreateTaskDto.Builder.class)
public class CreateTaskDto {}
