package springmvc2.exception.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springmvc2.exception.dto.MemberDto;
import springmvc2.exception.exceptions.InternalServerErrorException;
import springmvc2.exception.exceptions.UserException;

import java.io.IOException;

@RestController
@RequestMapping("/api3/member")
@Slf4j
public class ApiExceptionControllerV3 {

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MemberDto getMember(@PathVariable("id") String id, HttpServletResponse response, HttpServletRequest request) throws IOException, ChangeSetPersister.NotFoundException {
        log.info("api memberExceptipnControllerV3 실행");

        if (id.equals("runtime")) {
            throw new RuntimeException("runtime error");
        }

        if (id.equals("500")) {
            //response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "잘못된 접근");
            throw new InternalServerErrorException("잘못된 접근 : 500");
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
