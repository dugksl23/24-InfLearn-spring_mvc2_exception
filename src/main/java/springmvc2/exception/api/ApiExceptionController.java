package springmvc2.exception.api;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import springmvc2.exception.message.ErrorMsg;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ApiExceptionController {


    @RequestMapping(value = "/error-page/500", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> error404API(HttpServletRequest request, HttpServletResponse response) {

        log.info("API error page 500");
        Exception ex = (Exception) request.getAttribute(ErrorMsg.ERROR_EXCEPTION);
        if (ex == null) {
            log.info("ex null");
        }

        Map result = new HashMap();
        result.put("code", 404);
        result.put("message", "");

        Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        return new ResponseEntity<>(result, HttpStatus.valueOf(statusCode));
    }

    @RequestMapping(value = "/error-page/500")
    public ModelAndView error500(HttpServletRequest request, HttpServletResponse response) {

        return new ModelAndView("/error/500");
    }

}
