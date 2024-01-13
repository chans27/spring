package hello.servlet.web.frontcontroller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

//MyView는 모델과 path를 갖고있고, jsp에 넘기는 처리로직 담당.
public class MyView {

    private String viewPath;

    //생성자
    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }



    //V2에서 사용
    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

    //V3, V4에서 사용
    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        modelToRequestAttribute(model, request);
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath); //해당 jsp파일에 데이터 전달
        dispatcher.forward(request, response);
    }

    //model의 데이터를 받아서 request의 속성으로 등록
    private static void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
        model.forEach((key, value) -> request.setAttribute(key, value));
    }
}
