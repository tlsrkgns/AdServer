package com.jaehoon.assignment.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.jaehoon.assignment.service.AdServerService;

@Controller
public class AdServerController {

    @Autowired
    private AdServerService adServerService;

    private String getClientIP() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        return request.getRemoteAddr();
    }

    @GetMapping("/AdServer")
    @ResponseBody
    public String getScripts(@RequestParam("areacode") String areacode) {
        // 요청
        adServerService.collectData(getClientIP(), areacode + " code request");
        return adServerService.getScripts(areacode);
    }

    @GetMapping("/advertisement")
    public String getAdvertisement(@RequestParam("adpage") String adpage) {
        return adpage;
    }

    @GetMapping("/impressions")
    public void impressions(@RequestParam("adpage") String adpage) {
        // 노출
        adServerService.collectData(getClientIP(), adpage + " impressions cost : " + adServerService.getCpm(adpage) / 1000.0);
    }

    @GetMapping("/click")
    public String click(@RequestParam("adpage") String adpage, @RequestParam("adsite") String adsite) {
        // 클릭
        adServerService.collectData(getClientIP(), adsite + " click cost : " + adServerService.getCpm(adpage) / 1000.0);
        return "redirect:" + adsite;
    }
}