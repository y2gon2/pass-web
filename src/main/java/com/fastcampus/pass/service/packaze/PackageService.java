package com.fastcampus.pass.service.packaze;

import com.fastcampus.pass.repository.packaze.PackageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageService {
    private final PackageRepository packageRepository;

    public PackageService(PackageRepository packageRepository) {
        this.packageRepository = packageRepository;
    }

    public List<Package> getAllPackages() {
        return packageRepository
                .findAllByOrderByPackageName()
                .stream()
                .map(Package::from)
                .toList();
    }
}

