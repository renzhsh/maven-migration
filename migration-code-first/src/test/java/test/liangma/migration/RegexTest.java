package test.liangma.migration;

import com.liangma.migration.annotation.MaxLength;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {

    @Test
    public void Test_DbType() throws ClassNotFoundException {
        Date date=new Date();


        System.out.println(date.getClass());
    }
}

enum Hello {
    word,
    hex
}
