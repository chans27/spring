package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    //url의 /hello
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!!!");
        return "hello"; //뷰 리졸버가 templates의 hello.html을 찾아 처리
    };

    //MVC와 템플릿엔진
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(name = "name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template"; //뷰 리졸버가 hello-template를 찾아 HTML로 변환후 웹브라우저에 띄움
    }

    @GetMapping("hello-string")
    @ResponseBody //http통신의 body부분에 아래의 데이터를 직접 넣어주는것을 의미
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
