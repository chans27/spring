package hello.servlet.web.frontcontroller;

import java.util.HashMap;
import java.util.Map;

//MyView에 넘길 "데이터만을" 보관하고있는 객체
// v4, v5는 사용안함
public class ModelView {
    private String viewName;
    private Map<String, Object> model = new HashMap<>();

    //생성자
    public ModelView(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }
}
