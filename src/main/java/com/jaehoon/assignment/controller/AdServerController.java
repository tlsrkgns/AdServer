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
    public String getScripts(@RequestParam("areacode") String areaCode) {
        // 요청
        adServerService.recordRequestData(getClientIP(), areaCode);
        return adServerService.getScripts(areaCode);
    }

    @GetMapping("/advertisement")
    public String getAdvertisement(@RequestParam("adpage") String adPage) {
        return adPage;
    }

    @GetMapping("/impressions")
    public void impressions(@RequestParam("adpage") String adPage) {
        // 노출
        adServerService.recordAdPageData(getClientIP(), adPage, "impressions");
    }

    @GetMapping("/click")
    public String click(@RequestParam("adpage") String adPage, @RequestParam("adsite") String adSite) {
        // 클릭
        adServerService.recordAdPageData(getClientIP(), adPage, "click");
        return "redirect:" + adSite;
    }
}