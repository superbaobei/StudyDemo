package com.sxy.www.Spring;

import org.junit.Test;
import org.springframework.web.util.HtmlUtils;

public class SpringTest {
    String html = "<ul class=\"nav\"><li><a href=\"http://www.mkfree.com\">首 页</a></li><li class=\"active\"><a href=\"http://blog.mkfree.com\">博客</a></li>" +
            "<li><a href=\"#\">RSS</a></li></ul>";

    @Test
    public void testApplication() {
        String value = HtmlUtils.htmlEscape(html);
        System.out.println(value);
        String enValue = HtmlUtils.htmlUnescape(value);
        System.out.println("enValue = " + enValue);
    }
}
