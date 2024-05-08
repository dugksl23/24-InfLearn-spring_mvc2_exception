package springmvc2.exception.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springmvc2.dto.MemberDto;

@RestController
@RequestMapping("/api/member")
public class ApiController {

    @GetMapping("{id}")
    public MemberDto getMember(@PathVariable("id") String id) {
        if (id.equals("501")) {
            throw new RuntimeException("잘못된 사용자");
        }

        return new MemberDto("dd", "dd");
    }
}
