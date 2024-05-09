package springmvc2.exception.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto {

    @NotBlank
    private String userId;
    @NotBlank
    private String userName;

}
