package com.fastcampus.pass.service.packaze;

import com.fastcampus.pass.repository.packaze.PackageEntity;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Package {
    private Integer packageSeq;
    private String packageName;

    protected Package() {}

    private Package(Integer packageSeq, String packageName) {
        this.packageSeq = packageSeq;
        this.packageName = packageName;
    }

    public static Package of(Integer packageSeq, String packageName) {
        return new Package(packageSeq, packageName);
    }

    public static Package from(PackageEntity entity) {
        return Package.of(
                entity.getPackageSeq(),
                entity.getPackageName()
        );
    }

}


