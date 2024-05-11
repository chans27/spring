package hello.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

@Slf4j
public class UncheckedAppTest {

    @Test
    void unchecked() {
        Controller controller = new Controller();
        Assertions.assertThatThrownBy(() -> controller.request())
                .isInstanceOf(Exception.class);
    }

    @Test
    void printEx() {
        Controller controller = new Controller();
        try {
            controller.request();
        } catch (Exception e) {
            //e.printStackTrace();
            log.info("ex", e);
        }
    }

    static class Controller {
        Service service = new Service();

        public void request()  {
            service.logic();
        }
    }

    static class Service {
        Repository repository = new Repository();
        NetworkCLient networkCLient =new NetworkCLient();

        public void logic() {
            repository.call();
            networkCLient.call();
        }
    }

    static class NetworkCLient  {
        public void call() {
            throw new RuntimeConnectException("연결 실패");
        }
    }

    static class Repository {
        public void call() {
            try {
                runSQL();
            } catch (SQLException e) {
                /**
                 * SQLException RuntimeException 변환해서 던진다.
                 * 이떄 기존의 예외는 반드시 넣어주어야 한다. (e)
                 */
                throw new RuntimeException(e); //기존 예외(e) 포함
            }
        }

        public void runSQL() throws SQLException {
            throw new SQLException("ex");
        }
    }

    static class RuntimeConnectException extends RuntimeException {
        public RuntimeConnectException(String message) {
            super(message);
        }
    }

    static class RuntimeSQLException extends RuntimeException {
        public RuntimeSQLException() {

        }
    }

}
