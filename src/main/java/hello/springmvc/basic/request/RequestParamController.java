package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username , age);

        response.getWriter().write("ok");
    }


    /**
     * @RequestParam 사용
     * - 파라미터 이름으로 바인딩
     * @ResponseBody 추가
     * - View 조회를 무시하고, HTTP message body에 직접 해당 내용 입력
     */
    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge ){

        log.info("username={}, age={}", memberName , memberAge);
        //Controller 이면서  String이면 뷰리졸버로 ok라는 뷰를 찾게됨
        //그냥 문자 http메세지 하고싶으면 restcontroller 하거나 reponsebody
        //뷰 조회안함
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age ){
    // @RequestParam 생략 할 수 있음 변수명이랑 같으면
        log.info("username={}, age={}", username , age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4( String username,  int age ){
        //다 없앨 수 있음 대신 요청파라미터랑 맞아야함
        log.info("username={}, age={}", username , age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false)  Integer age ){
        // @RequestParam(required = true) 이게 기본값임
        //int에는 null 넣을 수 없어 0이라도 들어가야함 객체형은 null 들어감
        //그래서 int age하면 500 오류 int a= null;(오류) / Integer b = null;
        //null , ""; 다름 / 빈문자는 ok 됨 조심해야함

        log.info("username={}, age={}", username , age);
        return "ok";
    }


    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1")  int age ){
    //빈문자도 디폴트 값으로 들어감, username 삭제해도 기본값 들어가서 required가 필요 없음
        log.info("username={}, age={}", username , age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap){

        log.info("username={}, age={}", paramMap.get("username") , paramMap.get("age"));
        return "ok";
    }
}
