package com.todolist.userservice.router.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

public class CommonUtils {
  public static String serialize(Object objectToBeSerialized, Logger logger) {
    String jsonString = null;
    ObjectMapper mapper = new ObjectMapper();
    try {
      jsonString = mapper.writeValueAsString(objectToBeSerialized);
    } catch (JsonProcessingException e) {
      logger.error("Failed to serialize object to JSON", e);
    } catch (Exception e) {
      logger.error("Unknown exception occurred during serialization.", e);
    }
    return jsonString;
  }

  public static <T> @Nullable T deserialize(
      String jsonString, Class<T> targetClass, Logger logger) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      return mapper.readValue(jsonString, targetClass);
    } catch (JsonProcessingException e) {
      logger.error("Failed to deserialize JSON", e);
    } catch (Exception e) {
      logger.error("Unknown exception occurred during deserialization.", e);
    }
    return null;
  }
}
