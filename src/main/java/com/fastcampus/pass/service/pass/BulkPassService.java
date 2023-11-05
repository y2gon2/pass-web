package com.fastcampus.pass.service.pass;

import com.fastcampus.pass.controller.admin.BulkPassRequest;
import com.fastcampus.pass.repository.packaze.PackageEntity;
import com.fastcampus.pass.repository.packaze.PackageRepository;
import com.fastcampus.pass.repository.pass.BulkPassEntity;
import com.fastcampus.pass.repository.pass.BulkPassRepository;
import com.fastcampus.pass.repository.pass.BulkPassStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BulkPassService {
    private final BulkPassRepository bulkPassRepository;
    private final PackageRepository packageRepository;

    public BulkPassService(
            BulkPassRepository bulkPassRepository,
            PackageRepository packageRepository
    ) {
        this.bulkPassRepository = bulkPassRepository;
        this.packageRepository = packageRepository;
    }

    public List<BulkPass> getAllBulkPasses() {
        return bulkPassRepository
                .findAllOrderByStartedAtDesc()
                .stream()
                .map(BulkPass::from)
                .toList();
    }

    public void addBulkPass(BulkPassRequest bulkPassRequest) {
        PackageEntity packageEntity = packageRepository
                .findById(bulkPassRequest.getPackageSeq())
                .orElseThrow();

        LocalDateTime startedAt = bulkPassRequest.getStartedAt();

        BulkPassEntity bulkPassEntity = new BulkPassEntity();

        if (packageEntity.getPeriod() == null) {
            bulkPassEntity = BulkPassEntity.of(
                    packageEntity,
                    bulkPassRequest.getUserGroupId(),
                    BulkPassStatus.READY,
                    packageEntity.getCount(),
                    bulkPassRequest.getStartedAt(),
                    null
            );
        } else {
            bulkPassEntity  = BulkPassEntity.of(
                    packageEntity,
                    bulkPassRequest.getUserGroupId(),
                    BulkPassStatus.READY,
                    packageEntity.getCount(),
                    bulkPassRequest.getStartedAt(),
                    bulkPassRequest.getStartedAt().plusDays(packageEntity.getPeriod())
            );
        }

        bulkPassRepository.save(bulkPassEntity);
    }
}
