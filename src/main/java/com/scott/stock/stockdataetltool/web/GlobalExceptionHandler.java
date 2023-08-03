package com.scott.stock.stockdataetltool.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scott.stock.stockdataetltool.exception.DuplicatedObjectException;
import com.scott.stock.stockdataetltool.exception.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// ResponseEntityExceptionHandler

@ControllerAdvice
@RequiredArgsConstructor
@Log4j2
public class GlobalExceptionHandler {
  private final ObjectMapper objectMapper;


  @ExceptionHandler(DuplicatedObjectException.class)
  public ResponseEntity<String> handleDuplicatedObjectException(DuplicatedObjectException ex)
      throws JsonProcessingException {
    return ResponseEntity.status(HttpStatus.NOT_FOUND) //
        .contentType(MediaType.APPLICATION_JSON) //
        .body(objectMapper.writeValueAsString(new ErrorBody(ex.getMessage())));
  }

  @ExceptionHandler(ObjectNotFoundException.class)
  public ResponseEntity<String> handleObjectNotFoundException(ObjectNotFoundException ex)
      throws JsonProcessingException {
    return ResponseEntity.status(HttpStatus.NOT_FOUND) //
        .contentType(MediaType.APPLICATION_JSON) //
        .body(objectMapper.writeValueAsString(new ErrorBody(ex.getMessage())));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<String> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException ex) throws JsonProcessingException {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST) //
        .contentType(MediaType.APPLICATION_JSON) //
        .body(objectMapper.writeValueAsString(new ErrorBody(ex.getBindingResult().getFieldError().getDefaultMessage())));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleException(Exception ex) throws JsonProcessingException {

    log.error(ex, ex);

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) //
        .body(objectMapper.writeValueAsString(new ErrorBody(ex.getMessage())));
  }

  @AllArgsConstructor
  @Data
  public static class ErrorBody {
    private final String message;
  }

}

