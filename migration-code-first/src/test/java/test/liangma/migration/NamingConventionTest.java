package test.liangma.migration;

import com.liangma.migration.config.NamingConvention;
import org.junit.Assert;
import org.junit.Test;

public class NamingConventionTest {

    private String prefix = "_aspNet_";
    private String origin = "client_hello__--_i123";

    @Test
    public void Test_Camel() {
        NamingConvention naming = new NamingConvention();

        naming.camel = true;
        naming.prefix = prefix;

        String expected = "aspNetClientHelloI123";

        System.out.println(naming.caseTransform(origin));

        Assert.assertTrue(expected.equals(naming.caseTransform(origin)));

    }

    /**
     * 帕斯卡命名法
     * example: UserName
     */
    @Test
    public void Test_Pascal(){
        NamingConvention naming = new NamingConvention();

        naming.pascal = true;
        naming.prefix = prefix;

        String expected = "AspNetClientHelloI123";

        System.out.println(naming.caseTransform(origin));

        Assert.assertTrue(expected.equals(naming.caseTransform(origin)));
    }

    /**
     * 小写_下划线法
     * example: user_name
     */
    @Test
    public void Test_LowercaseWithUnderline(){
        NamingConvention naming = new NamingConvention();

        naming.lowercaseWithUnderline = true;
        naming.prefix = prefix;

        String expected = "asp_net_client_hello_i123";

        System.out.println(naming.caseTransform(origin));

        Assert.assertTrue(expected.equals(naming.caseTransform(origin)));
    }

    /**
     * 大写_下划线法
     * example: USER_NAME
     */
    @Test
    public void TestUppercaseWithUnderline(){
        NamingConvention naming = new NamingConvention();

        naming.uppercaseWithUnderline = true;
        naming.prefix = prefix;

        String expected = "ASP_NET_CLIENT_HELLO_I123";

        System.out.println(naming.caseTransform(origin));

        Assert.assertTrue(expected.equals(naming.caseTransform(origin)));
    }
}
