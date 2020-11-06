package com.alura.forum.exceptions;

import com.alura.forum.exceptions.models.StantardError;
import com.alura.forum.exceptions.models.RequestDataInvalid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionsHandler {

  @Autowired
  private MessageSource messageSource;

  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public List<RequestDataInvalid> requestDataInvalid(MethodArgumentNotValidException ex) {
    List<RequestDataInvalid> requestDataInvalids = new ArrayList<>();
    List<FieldError> fieldsErrors = ex.getBindingResult().getFieldErrors();
    fieldsErrors.forEach(e -> {
      String message = this.messageSource.getMessage(e, LocaleContextHolder.getLocale());
      RequestDataInvalid requestDataInvalid = new RequestDataInvalid(e.getField(), message);
      requestDataInvalids.add(requestDataInvalid);
    });
    return requestDataInvalids;
  }

  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<StantardError> authenticationInvalid(AuthenticationException ex, HttpServletRequest request) {
    StantardError bodyError = new StantardError(
      LocalDateTime.now(),
      HttpStatus.BAD_REQUEST.value(),
      HttpStatus.BAD_REQUEST.getReasonPhrase(),
      "Email or password invalid",
      request.getRequestURI()
    );
    return ResponseEntity.badRequest().body(bodyError);
  }
}
