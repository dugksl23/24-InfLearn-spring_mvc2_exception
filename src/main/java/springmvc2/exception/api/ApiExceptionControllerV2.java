package springmvc2.exception.api;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import springmvc2.exception.dto.ApiErrorResponseResult;
import springmvc2.exception.dto.ApiResponseResult;
import springmvc2.exception.dto.MemberDto;
import springmvc2.exception.exHandler.ErrorResults;
import springmvc2.exception.exceptions.UserException;

import java.io.IOException;

@RestController
@RequestMapping("/api2/member")
@Slf4j
public class ApiExceptionControllerV2 {


    //ExceptionResolver 가 호출함.
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResults handleException2(HttpServletRequest req, Exception e) {
        extracted(e);
        ErrorResults errorResults = new ErrorResults(String.valueOf(HttpStatus.BAD_REQUEST), e.getMessage(), e.getClass().getName());
        return errorResults;
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity handleException2(Exception e, UserException uEx) {
        extracted(uEx);
        ErrorResults errorResults = new ErrorResults(String.valueOf(HttpStatus.BAD_REQUEST), uEx.getMessage(), e.getClass().getName());
        return new ResponseEntity(errorResults, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ApiErrorResponseResult handleException2(Exception e) {
        extracted(e);
        ErrorResults errorResults = new ErrorResults(String.valueOf(HttpStatus.BAD_REQUEST), e.getMessage(), e.getClass().getName());
        return new ApiErrorResponseResult(errorResults);
    }


    private static void extracted(Exception e) {
        log.info("error msg : {}", e.getMessage());
        log.info("error class : {}", e.getClass().getName());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MemberDto getMember(@PathVariable("id") String id, HttpServletResponse response, HttpServletRequest request) throws IOException, ChangeSetPersister.NotFoundException {
        log.info("api memberExceptipnControllerV2 실행");
        if (id.equals("runtime")) {
            throw new RuntimeException("runtime error");
        }

        if (id.equals("500")) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "잘못된 접근");
        }

        if (id.equals("bad")) {
            throw new IllegalArgumentException("잘못된 입력 값 ");
        }

        if (id.equals("user-ex")) {
            log.info("user ex error");
            throw new UserException("user - ex 잘못 실행됨");
        }

        return new MemberDto("dd", "ㅇㅇ");
    }

}
