package com.store.Furniture_Home.Controller;

import com.store.Furniture_Home.Entity.Platform;
import com.store.Furniture_Home.Service.PlatformService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/platforms")
public class PlatformController {

    private final PlatformService platformService;

    public PlatformController(PlatformService platformService) {
        this.platformService = platformService;
    }

    @GetMapping
    public List<Platform> getAllPlatforms() {
        return platformService.getAllPlatforms();
    }

    @PostMapping
    public Platform createPlatform(@RequestBody Platform platform) {
        return platformService.savePlatform(platform);
    }
}
