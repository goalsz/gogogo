package iocContainer.test;

import iocContainer.IocContainer;
import iocContainer.test.entity.MianTiao;
import iocContainer.test.entity.People;
import iocContainer.test.entity.PingGuo;

public class IcoContainerTest {

    public static IocContainer iocContainer = new IocContainer();

    public static void before(){
        iocContainer.setBean("pingguo", PingGuo.class);
        iocContainer.setBean("miantiao", MianTiao.class);
        iocContainer.setBean("xiaoming", People.class, "miantiao");
    }

    public static void main(String[] args){
        before();
        People xiaoming = (People) iocContainer.getBean("xiaoming");
        xiaoming.foodColor();
    }

}
