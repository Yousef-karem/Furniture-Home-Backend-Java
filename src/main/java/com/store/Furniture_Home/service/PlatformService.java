package com.store.Furniture_Home.Service;

import com.store.Furniture_Home.Entity.Platform;
import com.store.Furniture_Home.Repository.PlatformRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlatformService {
    private final PlatformRepository platformRepository;

    public PlatformService(PlatformRepository platformRepository) {
        this.platformRepository = platformRepository;
    }

    public List<Platform> getAllPlatforms() {
        return platformRepository.findAll();
    }

    public Platform savePlatform(Platform platform) {
        return platformRepository.save(platform);
    }
}
