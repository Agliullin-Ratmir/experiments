package beans;


import org.example.beans.LifecycleBean;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {LifecycleBean.class})
class LifecycleBeanTest {

    @Autowired
    private LifecycleBean bean;

    @Test
    void testLifecycle() {
        Assertions.assertNotNull(bean);
    }
}
