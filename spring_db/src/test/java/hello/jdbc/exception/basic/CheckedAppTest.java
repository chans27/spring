package hello.jdbc.exception.basic;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.ConnectException;
import java.sql.SQLException;

public class CheckedAppTest {

    @Test
    void checked() throws SQLException, ConnectException {
        Controller controller = new Controller();
        Assertions.assertThatThrownBy(() -> controller.request())
                .isInstanceOf(Exception.class);
    }

    static class Controller {
        Service service = new Service();

        // 체크 예외 이므로 밖으로 예외를 던진다.
        public void request() throws SQLException, ConnectException {
            service.logic();
        }
    }

    static class Service {
        Repository repository = new Repository();
        NetworkCLient networkCLient =new NetworkCLient();

        /**
         * 체크 예외 이므로 밖으로 예외를 던진다.
         * **/
        public void logic() throws SQLException, ConnectException {
            repository.call();
            networkCLient.call();
        }
    }

    static class NetworkCLient  {
        public void call() throws ConnectException {
            throw new ConnectException("연결 실패");
        }
    }

    static class Repository {
        public void call() throws SQLException {
            throw new SQLException();
        }
    }

}
