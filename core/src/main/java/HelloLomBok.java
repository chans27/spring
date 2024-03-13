import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class HelloLomBok {
    //롬복은 게터와 세터 등을 자동으로 만들어주는 라이브러리. 덕분에 아래와같이 게터 세터 메서드를 따로 만들지 않아도 사용 할 수 있다.
    private String name;
    private int age;

    public static void main(String[] args) {
       HelloLomBok helloLomBok = new HelloLomBok();
       helloLomBok.setName("test");

       String name = helloLomBok.getName();
        System.out.println(name);
    }
}
