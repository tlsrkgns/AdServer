package com.jaehoon.assignment.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jaehoon.assignment.model.Area;
import com.jaehoon.assignment.model.Compaign;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdServerServiceTests {

    @Autowired
    private AdServerService adServerService;

    private List<Area> areas = new ArrayList<>();
    private List<Compaign> compaigns = new ArrayList<>();

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

        Compaign compaign1 = new Compaign();
        compaign1.setImg("dong1");
        compaign1.setWidth(420);
        compaign1.setHeight(419);
        compaign1.setCost(1000);
        compaign1.setType(Compaign.Type.CPM);
        compaigns.add(compaign1);

        Compaign compaign2 = new Compaign();
        compaign2.setImg("dong2");
        compaign2.setWidth(700);
        compaign2.setHeight(674);
        compaign2.setCost(599);
        compaign2.setType(Compaign.Type.CPC);
        compaigns.add(compaign2);

        Compaign compaign3 = new Compaign();
        compaign3.setImg("dong3");
        compaign3.setWidth(331);
        compaign3.setHeight(355);
        compaign3.setCost(1000);
        compaign3.setType(Compaign.Type.CPC);
        compaigns.add(compaign3);

        Compaign compaign4 = new Compaign();
        compaign4.setImg("ooo");
        compaign4.setWidth(700);
        compaign4.setHeight(674);
        compaign4.setCost(1200);
        compaign4.setType(Compaign.Type.CPM);
        compaigns.add(compaign4);

        Compaign compaign5 = new Compaign();
        compaign5.setImg("mmm");
        compaign5.setWidth(700);
        compaign5.setHeight(674);
        compaign5.setCost(600);
        compaign5.setType(Compaign.Type.CPC);
        compaigns.add(compaign5);
    }

    @Test
    public void adSelectTest() {
        Object[] adCandidate = { compaigns.get(4), compaigns.get(3), compaigns.get(1) };

    }
}
