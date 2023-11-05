package com.fastcampus.pass.service.pass;


import com.fastcampus.pass.repository.pass.BulkPassEntity;
import com.fastcampus.pass.repository.pass.BulkPassStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class BulkPass {

    private Integer bulkPassSeq;
    private String userGroupId;
    private Integer count;
    private BulkPassStatus status;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;

    protected BulkPass() {}

    private BulkPass(
            Integer bulkPassSeq,
            String userGroupId,
            Integer count,
            BulkPassStatus status,
            LocalDateTime startedAt,
            LocalDateTime endedAt
    ) {
        this.bulkPassSeq = bulkPassSeq;
        this.userGroupId = userGroupId;
        this.count = count;
        this.status = status;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
    }

    public static BulkPass of(
            Integer bulkPassSeq,
            String userGroupId,
            Integer count,
            BulkPassStatus status,
            LocalDateTime startedAt,
            LocalDateTime endedAt
    ) {
        return new BulkPass(
                bulkPassSeq,
                userGroupId,
                count,
                status,
                startedAt,
                endedAt
        );
    }

    public static BulkPass from(BulkPassEntity entity) {
        return BulkPass.of(
                entity.getBulkPassSeq(),
                entity.getUserGroupId(),
                entity.getCount(),
                entity.getStatus(),
                entity.getStartedAt(),
                entity.getEndedAt()
        );
    }
}
