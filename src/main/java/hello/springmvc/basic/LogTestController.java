package hello.springmvc.basic;



import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
//문자가 반환되게 하려고 body에 그냥 데이터를 확 넣어버리려고 rest api 만들 때 핵심적인 컨트롤러
@RestController
public class LogTestController {

    //private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public  String logTeat(){
        String name = "Spring";

        System.out.println("name = " + name);
        log.trace("info log=" + name);// 이렇게 사용하면 안됨 연산이 발생됨

        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info("info log={}", name);
        log.warn("warn log={}", name);
        log.error("error log={}", name);

        return "ok";
    }

}
