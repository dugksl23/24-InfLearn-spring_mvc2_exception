package springmvc2.exception.exHandler;


import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import springmvc2.exception.api.ApiExceptionControllerV3;
import springmvc2.exception.dto.ApiErrorResponseResult;
import springmvc2.exception.exceptions.UserException;

@Slf4j

@RestControllerAdvice
//        (basePackages = "springmvc2.exception.api")
        (assignableTypes = ApiExceptionControllerV3.class)
public class ExceptionControllerAdviceV2 {

    //ExceptionResolver 가 호출함.
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResults handleException2(HttpServletRequest req, Exception e, Object handler) {
        extracted(e, handler);
        ErrorResults errorResults = new ErrorResults(String.valueOf(HttpStatus.BAD_REQUEST), e.getMessage(), e.getClass().getName());
        return errorResults;
    }

    @ExceptionHandler(UserException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResults handleException2(Exception e, UserException uEx, Object handler) {
        extracted(uEx, handler);
        ErrorResults errorResults = new ErrorResults(String.valueOf(HttpStatus.BAD_REQUEST), uEx.getMessage(), e.getClass().getName());
        return errorResults;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ApiErrorResponseResult handleException2(Exception e, Object handler) {
        extracted(e, handler);
        ErrorResults errorResults = new ErrorResults(String.valueOf(HttpStatus.BAD_REQUEST), e.getMessage(), e.getClass().getName());
        return new ApiErrorResponseResult(errorResults);
    }


    private void extracted(Exception e, Object hanlder) {
        log.info("ControllerAdvice ver : {}", this.getClass().getName());
        log.info("error msg : {}", e.getMessage());
        log.info("error class : {}", e.getClass().getName());
    }

}
