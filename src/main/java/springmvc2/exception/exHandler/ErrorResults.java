package springmvc2.exception.exHandler;


import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
public class ErrorResults {

    private String errorCode;
    private String errorMsg;
    private String errorClass;

}
