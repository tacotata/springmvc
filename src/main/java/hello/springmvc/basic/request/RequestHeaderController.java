package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Slf4j
@RestController
//문자 그대로 박아서 반환 테스트할 때 view 필요 없으니까 @RestController
public class RequestHeaderController {

    @RequestMapping("/headers")
    public String headers(HttpServletRequest request,
                          HttpServletResponse response,
                          HttpMethod httpMethod,
                          Locale locale,
                          //헤더 한번에 다 받는거
                          @RequestHeader MultiValueMap<String, String> headerMap,
                         //이건 하나 받는거 host는 필수 헤더
                          @RequestHeader("host") String host,
                          //value 쿠키 이름 required 기본이 true
                          @CookieValue(value ="myCookie", required = false) String cookie
                          ) {

        log.info("request={}", request);
        log.info("response={}", response);
        log.info("httpMethod={}", httpMethod);
        log.info("locale={}", locale);
        log.info("headerMap={}", headerMap);
        log.info("header host={}", host);
        log.info("myCookie={}", cookie);

        return "ok";


    }

}
