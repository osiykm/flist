package com.osiykm.flist.services;

import org.junit.Test;

import java.util.Calendar;
import java.util.Random;

/***
 * @author osiykm
 * created 29.09.2017 15:17
 */

public class SamlibUpdaterServiceTest {
    private static SamlibUpdaterService service = new SamlibUpdaterService(new WebDriverComponent());

    @Test
    public void testLogin() {
        String annotation = "TEST " + Calendar.getInstance().getTime();
        service.listUpdate(annotation, "......................" +
                ".......................\n" +
                ".......................\n" +
                ".......................\n" +
                ".......................\n" +
                ".......................\n" +
                "............TEST2......\n" +
                ".......................\n" +
                ".......................\n" +
                ".......................\n" +
                ".......................\n");
    }

}