package com.project.foody.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MapController {

    @GetMapping("/map")
    public String showMap() {
        return "map"; // templates/map.html 렌더링
    }

//    @GetMapping("/kakao-map")
//    public String showMap() { return "map"; }


//    @GetMapping("/map")
//    public String showMap() { return "redirect:/map.html"; }



}