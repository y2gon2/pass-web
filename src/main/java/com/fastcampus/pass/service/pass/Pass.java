package com.fastcampus.pass.service.pass;

import com.fastcampus.pass.repository.pass.PassEntity;
import com.fastcampus.pass.repository.pass.PassStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Pass{
    private Integer passSeq;
    private Integer packageSeq;
    private String packageName;
    private String userId;
    private PassStatus status;
    private Integer remainingCount;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private LocalDateTime expiredAt;

    protected Pass() {}

    private Pass(
            Integer passSeq,
            Integer packageSeq,
            String packageName,
            String userId,
            PassStatus status,
            Integer remainingCount,
            LocalDateTime startedAt,
            LocalDateTime endedAt,
            LocalDateTime expiredAt
    ) {
        this.passSeq = passSeq;
        this.packageSeq = packageSeq;
        this.packageName = packageName;
        this.userId = userId;
        this.status = status;
        this.remainingCount = remainingCount;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.expiredAt = expiredAt;
    }

    public static Pass of(
            Integer passSeq,
            Integer packageSeq,
            String packageName,
            String userId,
            PassStatus status,
            LocalDateTime startedAt,
            LocalDateTime endedAt
    ) {
        return new Pass(
                passSeq,
                packageSeq,
                packageName,
                userId,
                status,
                null,
                startedAt,
                endedAt,
                null
        );
    }

    public static Pass from(PassEntity entity) {
        return new Pass(
                entity.getPassSeq(),
                entity.getPackageSeq(),
                entity.getPackageEntity().getPackageName(),
                entity.getUserId(),
                entity.getStatus(),
                entity.getRemainingCount(),
                entity.getStartedAt(),
                entity.getEndedAt(),
                entity.getExpiredAt()
        );
    }

}
