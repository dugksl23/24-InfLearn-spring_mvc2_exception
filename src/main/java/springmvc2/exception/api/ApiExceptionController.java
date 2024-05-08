package springmvc2.exception.api;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springmvc2.exception.servlet.ErrorMsg;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ApiExceptionController {


    @GetMapping(value = "/error/501", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> error404API(HttpServletRequest request, HttpServletResponse response) {

        ErrorMsg errorMsg = new ErrorMsg();
        log.info("msg : {}", errorMsg.ERROR_EXCEPTION);
        log.info("API error page 501");
        Exception ex = (Exception) request.getAttribute(errorMsg.ERROR_EXCEPTION);
        Map result = new HashMap();
        result.put("code", 501);
        result.put("message", ex.getMessage());
        result.put("status", request.getAttribute(errorMsg.ERROR_STATUS_CODE));

        Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        return new ResponseEntity<>(result, HttpStatus.valueOf(statusCode));
    }

}
