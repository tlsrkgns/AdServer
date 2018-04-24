package com.jaehoon.assignment.service;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jaehoon.assignment.model.Area;
import com.jaehoon.assignment.model.Campaign;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdServerServiceTests {
    Logger log = LogManager.getLogger("test");

    @Autowired
    private AdServerService adServerService;

    private List<Area> areas = new ArrayList<>();
    private List<Campaign> compaigns = new ArrayList<>();

    private int[] percent;
    private int dong2, mmm, ooo, testCount;

    @Before
    public void setup() {
        Area area1 = new Area();
        area1.setCode(Area.Code.A);
        area1.setWidth(420);
        area1.setHeight(419);
        areas.add(area1);

        Area area2 = new Area();
        area2.setCode(Area.Code.B);
        area2.setWidth(700);
        area2.setHeight(674);
        areas.add(area2);

        Area area3 = new Area();
        area3.setCode(Area.Code.C);
        area3.setWidth(331);
        area3.setHeight(355);
        areas.add(area3);

        Campaign compaign1 = new Campaign();
        compaign1.setImg("dong1");
        compaign1.setWidth(420);
        compaign1.setHeight(419);
        compaign1.setCost(1000);
        compaign1.setType(Campaign.Type.CPM);
        compaigns.add(compaign1);

        Campaign compaign2 = new Campaign();
        compaign2.setImg("dong2");
        compaign2.setWidth(700);
        compaign2.setHeight(674);
        compaign2.setCost(599);
        compaign2.setType(Campaign.Type.CPC);
        compaigns.add(compaign2);

        Campaign compaign3 = new Campaign();
        compaign3.setImg("dong3");
        compaign3.setWidth(331);
        compaign3.setHeight(355);
        compaign3.setCost(1000);
        compaign3.setType(Campaign.Type.CPC);
        compaigns.add(compaign3);

        Campaign compaign4 = new Campaign();
        compaign4.setImg("ooo");
        compaign4.setWidth(700);
        compaign4.setHeight(674);
        compaign4.setCost(1200);
        compaign4.setType(Campaign.Type.CPM);
        compaigns.add(compaign4);

        Campaign compaign5 = new Campaign();
        compaign5.setImg("mmm");
        compaign5.setWidth(700);
        compaign5.setHeight(674);
        compaign5.setCost(600);
        compaign5.setType(Campaign.Type.CPC);
        compaigns.add(compaign5);

        percent = new int[] { 7, 2, 1 };
        dong2 = 0;
        mmm = 0;
        ooo = 0;
        testCount = 100000;
    }

    @Test
    public void adSelectSameLengthTest()
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Campaign[] candidateAd = { compaigns.get(4), compaigns.get(3), compaigns.get(1) };

        testFor(candidateAd);

        log.info("dong2: " + dong2 + ", mmm: " + mmm + ", ooo: " + ooo);
        assertTrue(69500 < mmm && 70500 > mmm);
        assertTrue(19500 < ooo && 20500 > ooo);
        assertTrue(9500 < dong2 && 10500 > dong2);
    }

    @Test
    public void adSelectCandidateAdLengthBiggerTest()
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Campaign[] candidateAd = { compaigns.get(4), compaigns.get(3), compaigns.get(1), compaigns.get(2), compaigns.get(0) };

        testFor(candidateAd);

        log.info("dong2: " + dong2 + ", mmm: " + mmm + ", ooo: " + ooo);
        assertTrue(69500 < mmm && 70500 > mmm);
        assertTrue(19500 < ooo && 20500 > ooo);
        assertTrue(9500 < dong2 && 10500 > dong2);
    }

    @Test
    public void adSelectPercentLengthBiggerTest()
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Campaign[] candidateAd = { compaigns.get(4), compaigns.get(3) };

        testFor(candidateAd);

        log.info("dong2: " + dong2 + ", mmm: " + mmm + ", ooo: " + ooo);
        assertTrue(69500 < mmm && 70500 > mmm);
        assertTrue(29500 < ooo && 30500 > ooo);
        assertTrue(dong2 == 0);
    }

    @Test
    public void adSelectPercentLengthBigger2Test()
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Campaign[] candidateAd = { compaigns.get(4), };

        testFor(candidateAd);

        log.info("dong2: " + dong2 + ", mmm: " + mmm + ", ooo: " + ooo);
        assertTrue(mmm == 100000);
        assertTrue(ooo == 0);
        assertTrue(dong2 == 0);
    }

    @Test
    public void adSelectPercentLength4Test()
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Campaign[] candidateAd = { compaigns.get(4), compaigns.get(3), compaigns.get(1) };
        percent = new int[] { 6, 2, 1, 1 };

        testFor(candidateAd);

        log.info("dong2: " + dong2 + ", mmm: " + mmm + ", ooo: " + ooo);
        assertTrue(59500 < mmm && 60500 > mmm);
        assertTrue(19500 < ooo && 20500 > ooo);
        assertTrue(19500 < dong2 && 20500 > dong2);
    }

    @Test
    public void adSelectSameCostTest()
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Campaign campaign;

        Method method = AdServerService.class.getDeclaredMethod("adSelect", Campaign[].class, int[].class);
        method.setAccessible(true);
        Method method2 = AdServerService.class.getDeclaredMethod("getCandidateAd", Area.class);
        method2.setAccessible(true);

        for (int i = 0; i < testCount; i++) {
            campaign = (Campaign) method.invoke(adServerService, method2.invoke(adServerService, areas.get(1)), percent);
            if (campaign.getImg().equals("dong2")) {
                dong2++;
            } else if (campaign.getImg().equals("mmm")) {
                mmm++;
            } else if (campaign.getImg().equals("ooo")) {
                ooo++;
            }
        }
        log.info("adSelectSameCostTest - dong2: " + dong2 + ", mmm: " + mmm + ", ooo: " + ooo);
        assertTrue(44500 < mmm && 45500 > mmm);
        assertTrue(44500 < ooo && 45500 > ooo);
        assertTrue(9500 < dong2 && 10500 > dong2);
    }

    private void testFor(Campaign[] candidateAd)
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Campaign campaign;

        Method method = AdServerService.class.getDeclaredMethod("adSelect", Campaign[].class, int[].class);
        method.setAccessible(true);

        for (int i = 0; i < testCount; i++) {
            campaign = (Campaign) method.invoke(adServerService, candidateAd, percent);
            if (campaign.getImg().equals("dong2")) {
                dong2++;
            } else if (campaign.getImg().equals("mmm")) {
                mmm++;
            } else if (campaign.getImg().equals("ooo")) {
                ooo++;
            }
        }
    }
}
