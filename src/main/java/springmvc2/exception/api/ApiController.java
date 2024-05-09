package springmvc2.exception.api;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springmvc2.dto.MemberDto;

import java.io.IOException;

@RestController
@RequestMapping("/api/member")
@Slf4j
public class ApiController {

    @GetMapping("/{id}")
    public MemberDto getMember(@PathVariable("id") String id, HttpServletResponse response) throws IOException, ChangeSetPersister.NotFoundException {
        if (id.equals("404")) {
//            response.sendError(404, "404　오류");
            throw new RuntimeException();
        } else if(id.equals("bad")){
            throw new IllegalArgumentException("잘못된 입력 값 ");
        }

        return new MemberDto("dd", "dd");
    }
}
