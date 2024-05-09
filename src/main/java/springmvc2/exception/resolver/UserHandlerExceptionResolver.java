package springmvc2.exception.resolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import springmvc2.exception.UserException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Slf4j
public class UserHandlerExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {


        try {
            if (ex instanceof UserException) {
                log.info("resolver ex, {}", ex.getClass().getName());
                log.info("UserExceptionResolver to 400");
                String accept = request.getHeader("accept");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

                if("application/json".equals(accept)){
                    Map<String, Object> errorResultModel = new HashMap<>();
                    errorResultModel.put("code", 400);
                    errorResultModel.put("message", ex.getMessage());

                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().print(errorResultModel);
                }

            }
        } catch (Exception e) {
            log.info("resolver ex, {}", e);
        }

        return null;
    }
}
