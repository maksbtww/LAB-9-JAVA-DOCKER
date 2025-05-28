package com.example.website.controllers;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/websites")
public class WebsiteController {

    private List<String> websites = new ArrayList<>(List.of("example.com", "openai.com"));

    @GetMapping
    public List<String> getAllWebsites() {
        return websites;
    }

    @PostMapping
    public void addWebsite(@RequestBody String website) {
        websites.add(website);
    }

    @DeleteMapping("/{index}")
    public void deleteWebsite(@PathVariable int index) {
        if (index >= 0 && index < websites.size()) {
            websites.remove(index);
        }
    }
}
