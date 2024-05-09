package springmvc2.exception.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import springmvc2.exception.dto.MemberDto;
import springmvc2.exception.exceptions.BadRequestException;
import springmvc2.exception.exceptions.UserException;

import java.io.IOException;

@RestController
@RequestMapping("/api/member")
@Slf4j
public class ApiController {

    @GetMapping("/{id}")
    public MemberDto getMember(@PathVariable("id") String id, HttpServletResponse response, HttpServletRequest request) throws IOException, ChangeSetPersister.NotFoundException {
        log.info("api member request 실행");
        if (id.equals("500")) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"잘못된 접근");
        }

        if (id.equals("bad")) {
            throw new IllegalArgumentException("잘못된 입력 값 ");
        }

        if (id.equals("user-ex")) {
            log.info("user ex error");
            throw new UserException("user - ex 잘못 실행됨");
        }

        return new MemberDto("dd", "dd");
    }


    @GetMapping("/response-status-ex1")
    public String responseStatusEx1(){
        throw new BadRequestException();
    }

    @GetMapping("/response-status-ex2")
    public String responseStatusEx2(){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "error.bad", new IllegalStateException("error.bad"));
    }

}
