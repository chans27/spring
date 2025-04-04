package hello.springmvc.basic;

import lombok.Data;
import lombok.Value;
import org.springframework.lang.NonNull;

import java.lang.reflect.Type;

@Data
public class HelloData {

    private String username;

    private int age;
}
