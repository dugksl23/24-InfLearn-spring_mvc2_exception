package springmvc2.exception.resolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;


@Slf4j
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        /**
         * Instanceof
         * @Interface : A 가 B의 구현체인지
         * @Class     : A 가 B의 Class 가 맞는지
         */
        log.info("Exception type : {}", ex.getClass().getName());
        try {
            if (ex instanceof IllegalArgumentException) {
                String msg = ex.getMessage();
                log.info("IllegalArgumentException 발생 : {}", ex.getMessage());
                log.info("IllegalArgumentException to 400 (Bad Request)");

                response.sendError(HttpServletResponse.SC_BAD_REQUEST, msg);

                return new ModelAndView("error/404");
                // API 응답을 위해서 새로운 mv 생성하여 정상적인 return을 한다면,
                // 자연스럽게 response 응답 객체에 error 를 저장하고, 서블릿 컨테이너(DispatcherServlet)에서
                // 다시 sendError() 를 확인하여 오류 메시지를 반환한다.
            }

        } catch (IOException e) {
            log.info("resolver ex", e);
        }
        return null;
        // return 을 null 로 가면 호출하는 곳까지 error 가 올라간다.
    }
}
