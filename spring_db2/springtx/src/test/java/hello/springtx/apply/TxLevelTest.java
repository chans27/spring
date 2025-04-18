package hello.springtx.apply;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@SpringBootTest
public class TxLevelTest {

    @Autowired
    LevelService service;

    @Test
    void orderTest() {
        service.write();
        service.read();
    }

    @TestConfiguration
    static class TxLevelTestConfig {

        @Bean
        LevelService levelService() {
            return new LevelService();
        }
    }

    @Slf4j
    @Transactional(readOnly = true)
    static class LevelService {


        @Transactional(readOnly = false) // (prioritized)
        public void write() {
            log.info("call write");
            printTxINfo();
        }

        public void read() {
            log.info("call read");
            printTxINfo();
        }

        private void printTxINfo() {
            boolean txActive = TransactionSynchronizationManager.isSynchronizationActive();
            log.info("tx active={}", txActive);
            boolean readOnly = TransactionSynchronizationManager.isActualTransactionActive();
        }
    }
}
