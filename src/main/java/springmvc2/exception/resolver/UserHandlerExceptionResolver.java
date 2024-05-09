package springmvc2.exception.resolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import springmvc2.exception.UserException;

import java.util.HashMap;
import java.util.Map;


@Slf4j
public class UserHandlerExceptionResolver implements HandlerExceptionResolver {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {


        try {
            if (ex instanceof UserException) {
                log.info("resolver ex, {}", ex.getClass().getName());
                log.info("UserExceptionResolver to 400");
                String accept = request.getHeader("accept");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

                // json 과 html 을 분기문
                if("application/json".equals(accept)){
                    Map<String, Object> errorResultModel = new HashMap<>();
                    errorResultModel.put("code", 400);
                    errorResultModel.put("message", ex.getMessage());

                    //Json Object 로 변환
                    String result = mapper.writeValueAsString(errorResultModel);

                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    // Response 객체에 Json Object 가 MessageBody 에 담긴다.
                    response.getWriter().write(result);
                    return new ModelAndView();
                    // return 함으로서 정상 흐름으로 만들기 위함이다. 이미 예외는 response 객체에 담았기에 Servlet container (dispatcher servlet)에 전달됨.
                    // 없을 경우에는 에러를 던진다.

                } else {
                    // text/html
                    // view html 경로 지정
                    return new ModelAndView("error/500");
                }

            }
        } catch (Exception e) {
            log.info("resolver ex, {}", e);
        }

        return null;
    }
}
