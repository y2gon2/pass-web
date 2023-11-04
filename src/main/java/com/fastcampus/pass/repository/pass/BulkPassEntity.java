package com.fastcampus.pass.repository.pass;


import com.fastcampus.pass.repository.BaseEntity;
import com.fastcampus.pass.repository.packaze.PackageEntity;
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
@Table(name = "bulk_pass")
public class BulkPassEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bulkPassSeq;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "packageSeq", insertable = false, updatable = false)
    private PackageEntity packageEntity;

    @Column(nullable = false)
    private Integer packageSeq;

    @Column(nullable = false)
    private String userGroupId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BulkPassStatus bulkPassStatus;

    private Integer count;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(nullable = false)
    private LocalDateTime startedAt;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime endedAt;

    public void setEndedAt(Integer period) {
        if (period == null) {
            return;
        }
        this.endedAt = this.startedAt.plusDays(period);
    }

    public void setEndedAt(LocalDateTime endedAt) {
        this.endedAt = endedAt;
    }

    protected BulkPassEntity() {}

    private BulkPassEntity(
            PackageEntity packageEntity,
            String userGroupId,
            BulkPassStatus bulkPassStatus,
            Integer count,
            LocalDateTime startedAt,
            LocalDateTime endedAt
    ) {
        this.packageEntity = packageEntity;
        this.packageSeq = packageEntity.getPackageSeq();
        this.userGroupId = userGroupId;
        this.bulkPassStatus = bulkPassStatus;
        this.count = count;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
    }

    public static BulkPassEntity of(
            PackageEntity packageEntity,
            String userGroupId,
            BulkPassStatus bulkPassStatus,
            LocalDateTime startedAt
    ) {
        return new BulkPassEntity(
          packageEntity,
          userGroupId,
          bulkPassStatus,
          null,
          startedAt,
          null
        );
    }
}
