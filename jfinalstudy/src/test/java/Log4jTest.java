import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log4jTest {

    private final Logger logger = LoggerFactory.getLogger(Log4jTest.class);

    @Test
    public void testLog(){
        int a= 0;
        while(a<10000){


            logger.info("INFO log...");
            a++;
        }

    }
}
