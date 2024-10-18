package com.bootcamp.exercise.demo_bc_forum.exception;

public class InvalidUserInputException extends RuntimeException{
  public InvalidUserInputException(String message) {
    super(message);
  }
}
