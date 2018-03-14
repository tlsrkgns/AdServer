package com.jaehoon.assignment.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.jaehoon.assignment.model.Area;
import com.jaehoon.assignment.model.Compaign;

@Service
public class AdServerService {
    Logger log = LogManager.getLogger(this.getClass());
    private List<Area> areas = new ArrayList<>();
    private List<Compaign> compaigns = new ArrayList<>();

    AdServerService() {
        Area area1 = new Area();
        area1.setCode("A");
        area1.setWidth(420);
        area1.setHeigh(419);
        areas.add(area1);

        Area area2 = new Area();
        area2.setCode("B");
        area2.setWidth(700);
        area2.setHeigh(674);
        areas.add(area2);

        Area area3 = new Area();
        area3.setCode("C");
        area3.setWidth(331);
        area3.setHeigh(355);
        areas.add(area3);

        Compaign compaign1 = new Compaign();
        compaign1.setImg("dong1");
        compaign1.setWidth(420);
        compaign1.setHeigh(419);
        compaign1.setCpm(1000);
        compaigns.add(compaign1);

        Compaign compaign2 = new Compaign();
        compaign2.setImg("dong2");
        compaign2.setWidth(700);
        compaign2.setHeigh(674);
        compaign2.setCpm(1000);
        compaigns.add(compaign2);

        Compaign compaign3 = new Compaign();
        compaign3.setImg("dong3");
        compaign3.setWidth(331);
        compaign3.setHeigh(355);
        compaign3.setCpm(1000);
        compaigns.add(compaign3);

        Compaign compaign4 = new Compaign();
        compaign4.setImg("ooo");
        compaign4.setWidth(700);
        compaign4.setHeigh(674);
        compaign4.setCpm(800);
        compaigns.add(compaign4);

        Compaign compaign5 = new Compaign();
        compaign5.setImg("mmm");
        compaign5.setWidth(700);
        compaign5.setHeigh(674);
        compaign5.setCpm(1100);
        compaigns.add(compaign5);
    }

    public void collectData(String clientIP, String body) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        log.info("clientIP : " + clientIP + " " + "time : " + currentDateTime + " " + "body : " + body);
    }

    public String getScripts(String code) {
        Area area = areas.stream().filter(a -> (a.getCode().equals(code))).findFirst().get();
        Compaign compaign = compaigns.stream().filter(c -> (c.getWidth() == area.getWidth() && c.getHeigh() == area.getHeigh()))
                .sorted((s1, s2) -> s2.getCpm() - s1.getCpm()).findFirst().get();

        String script = "document.write(\"<iframe src=\\\"http://localhost:8080/advertisement?adpage=" + compaign.getImg() + "\\\"width=\\\""
                + compaign.getWidth() + "\\\" height=\\\"" + compaign.getWidth()
                + "\\\" marginheight=\\\"0\\\"marginwidth=\\\"0\\\" scrolling=\\\"no\\\"> </iframe>\");";

        return script;
    }

    public int getCpm(String adpage) {
        Compaign compaign = compaigns.stream().filter(c -> c.getImg().equals(adpage)).findFirst().get();
        return compaign.getCpm();
    }
}
