package com.restoreBookshow.demo.controller;

import com.restoreBookshow.demo.dtos.CreateShowRequest;
import com.restoreBookshow.demo.models.Shows;
import com.restoreBookshow.demo.services.ShowService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class ShowController {

    private ShowService showService;

    //read show api
    @GetMapping("/show/{id}")
    public Shows readShow(@PathVariable Long id){
        return showService.getShow(id);
    }

    //create show api
    @PostMapping("/show")
    public Shows createShow(@RequestBody CreateShowRequest request){
        return showService.createShow(request);
    }
}
