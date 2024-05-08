package springmvc2.exception.servlet;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@Slf4j
public class ServletExceptionController {


    @GetMapping("/error-ex")
    public String exception() {
        throw new RuntimeException();
    }

    @GetMapping("/error-400")
    public void exception(HttpServletResponse response) throws IOException {
        response.sendError(400, "404　오류");
    }


    @GetMapping("/error-404")
    public void exception404(HttpServletResponse response) throws IOException {
        response.sendError(404,"404 오류 ");
        // 이 메서드 자체는 에러가 발생하는 것이 아니라, was 까지 전달하여
        // was의 기본 오류 페이지를 제공한다. 상태코드 입력 가능
    }

    @GetMapping("/error-500")
    public void exception500(HttpServletResponse response) throws IOException {
        response.sendError(500,"500 오류 ");

    }

}
