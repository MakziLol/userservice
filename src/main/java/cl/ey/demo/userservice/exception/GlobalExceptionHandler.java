package cl.ey.demo.userservice.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

     @ExceptionHandler(Exception.class)
    public ResponseEntity<UserServiceException> handleGeneral(Exception ex) {
        return new ResponseEntity<>(new UserServiceException("Error inesperado: " + ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<UserServiceException>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<UserServiceException> errores = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new UserServiceException(error.getDefaultMessage()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
    }
    

}