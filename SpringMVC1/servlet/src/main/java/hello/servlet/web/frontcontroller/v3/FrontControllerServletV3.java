package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        System.out.println("프론트컨트롤러V3 생성자 실행");
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        System.out.println("requestURI = " + requestURI);

        ControllerV3 controller = controllerMap.get(requestURI);
        if(controller == null) {
            System.out.println("존재하지 않는 컨트롤러");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //request에 들어있는 key, value빼내서 새 Map 변수에 담기
        Map<String, String> paramMap = createParamMap(request); //파라미터의 key와value정보 들어가있음


        // mv.viewname = "new-form";
        ModelView mv = controller.process(paramMap); //ModelView객체 생성 후 viewName멤버 변수에 "new-form" 저장
        System.out.println("mv : " + mv);
        String viewName = mv.getViewName();//논리이름 'new-form'
        System.out.println("viewName " + viewName);



        MyView view = viewResolver(viewName); //논리이름을 가지고 물리이름을 만들고 MyView반환 (생성자 초기화를 여기서)
        view.render(mv.getModel() ,request, response); //ModelView에서 받은 데이터, path를가지고 jsp에 넘기는 처리
    }


    //컨트롤러가 반환한 논리 뷰 이름을 실제 물리 뷰 경로로 변경
    private static MyView viewResolver(String viewName) {
         return new MyView("/WEB-INF/views/" + viewName + ".jsp");
//        return view;
    }

    private static Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
