package com.jaehoon.assignment.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.jaehoon.assignment.model.Area;
import com.jaehoon.assignment.model.Compaign;

@Service
public class AdServerService {
    Logger log = LogManager.getLogger("log");

    private static final int FIRST_RATIO = 7;
    private static final int SECOND_RATIO = 2;

    private List<Area> areas = new ArrayList<>();
    private List<Compaign> compaigns = new ArrayList<>();

    AdServerService() {
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

    public void recordRequestData(String clientIP, String areaCode) {
        writeLog(clientIP, areaCode + " code request");
    }

    public void recordAdPageData(String clientIP, String adPage, String type) {
        writeLog(clientIP, adPage + " " + type + ", cost : " + getAdCost(adPage) + " " + getAdCostType(adPage));
    }

    public String getScripts(String code) {
        Area area = areas.stream().filter(a -> (a.getCode().equals(Area.Code.valueOf(code)))).findFirst().get();
        List<Compaign> shuffleCompaigns = new ArrayList<>(compaigns);
        Collections.shuffle(shuffleCompaigns);

        Object[] candidateAd = shuffleCompaigns.stream().filter(c -> (c.getWidth() == area.getWidth() && c.getHeight() == area.getHeight())).sorted()
                .limit(3).toArray();

        Compaign compaign = adSelect(candidateAd);

        String script = "document.write(\"img : " + compaign.getImg() + ", cost : " + compaign.getCost() + " " + compaign.getType()
                + "<br><iframe src=\\\"http://localhost:8080/advertisement?adpage=" + compaign.getImg() + "\\\"width=\\\"" + area.getWidth()
                + "\\\" height=\\\"" + area.getHeight() + "\\\" marginheight=\\\"0\\\"marginwidth=\\\"0\\\" scrolling=\\\"no\\\"> </iframe>\");";

        return script;
    }

    private void writeLog(String clientIP, String body) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        log.info("clientIP : " + clientIP + " " + "time : " + currentDateTime + " " + "body : " + body);
    }

    private int getAdCost(String adpage) {
        Compaign compaign = compaigns.stream().filter(c -> c.getImg().equals(adpage)).findFirst().get();
        return compaign.getCost();
    }

    private String getAdCostType(String adpage) {
        Compaign compaign = compaigns.stream().filter(c -> c.getImg().equals(adpage)).findFirst().get();
        return compaign.getType().toString();
    }

    private Compaign adSelect(Object[] adCandidate) {
        Object compaign;
        int rand = (int) (Math.random() * 10);

        if (adCandidate.length == 1) {
            compaign = adCandidate[0];
        } else if (adCandidate.length == 2) {
            compaign = (rand < FIRST_RATIO) ? adCandidate[0] : adCandidate[1];
        } else if (adCandidate.length == 3) {
            compaign = (rand < FIRST_RATIO) ? adCandidate[0] : (rand < FIRST_RATIO + SECOND_RATIO) ? adCandidate[1] : adCandidate[2];
        } else {
            compaign = new Compaign();
        }

        return (Compaign) compaign;
    }
}
