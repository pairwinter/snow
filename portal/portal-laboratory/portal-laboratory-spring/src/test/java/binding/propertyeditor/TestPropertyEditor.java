package binding.propertyeditor;

import com.pairwinter.snow.laboratory.spring.binding.propertyeditor.DependsOnExoticItem;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by damon on 9/18/14.
 */
public class TestPropertyEditor {
    public static ApplicationContext applicationContext;
    static{
        applicationContext = new ClassPathXmlApplicationContext("applicationContext-propertyeditor.xml");
    }

    @Test
    public void testExoticEditor(){
        DependsOnExoticItem dependsOnExoticItem = (DependsOnExoticItem)applicationContext.getBean("dependsOnExoticItem");
        Assert.assertEquals(dependsOnExoticItem.getExoticItem().getName(),"This is the name of exoticItem".toUpperCase());
    }
}
