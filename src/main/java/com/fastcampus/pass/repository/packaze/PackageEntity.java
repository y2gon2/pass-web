package com.fastcampus.pass.repository.packaze;

import com.fastcampus.pass.repository.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Getter
@Setter
@ToString
@Entity
@Table(name = "package")
public class PackageEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer packageSeq;

    @Column(nullable = false)
    private String packageName;

    private Integer count;
    private Integer period;

    protected PackageEntity() {}

    private PackageEntity(
            String packageName,
            Integer count,
            Integer period
    ) {
        this.packageName = packageName;
        this.count = count;
        this.period = period;
    }

    public static PackageEntity of(
            String packageName
    ) {
        return new PackageEntity(
          packageName,
          null,
          null
        );
    }
}
