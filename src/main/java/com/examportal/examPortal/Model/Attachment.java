package com.examportal.examPortal.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Attachment extends BaseEntity {
    private String fileName;

    private String filetype;
    @Lob
    private byte[] data;

    public Attachment(String fileName, String filetype, byte[] data) {
        this.fileName = fileName;
        this.filetype = filetype;
        this.data = data;
    }
}
