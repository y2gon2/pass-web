package com.fastcampus.pass.repository.pass;

import com.fastcampus.pass.repository.BaseEntity;
import com.fastcampus.pass.repository.packaze.PackageEntity;
import com.fastcampus.pass.repository.user.UserEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "pass")
public class PassEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer passSeq;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "packageSeq", insertable = false, updatable = false)
    private PackageEntity packageEntity;

    @Column(nullable = false)
    private Integer packageSeq;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private UserEntity userEntity;

    @Column(nullable = false)
    private String userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PassStatus status;

    private Integer remainingCount;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(nullable = false)
    private LocalDateTime startedAt;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime endedAt;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime expiredAt;

    protected PassEntity() {}

    private PassEntity(
            PackageEntity packageEntity,
            UserEntity userEntity,
            PassStatus status,
            Integer remainingCount,
            LocalDateTime startedAt,
            LocalDateTime endedAt,
            LocalDateTime expiredAt
    ) {
        this.packageEntity = packageEntity;
        this.packageSeq = packageEntity.getPackageSeq();
        this.userEntity = userEntity;
        this.userId = userEntity.getUserId();
        this.status = status;
        this.remainingCount = remainingCount;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.expiredAt = expiredAt;
    }

    public static PassEntity of(
            PackageEntity packageEntity,
            UserEntity userEntity,
            PassStatus status,
            LocalDateTime startedAt
    ) {
        return new PassEntity(
                packageEntity,
                userEntity,
                status,
                null,
                startedAt,
                null,
                null
        );
    }
}
