package com.examportal.examPortal.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseImageData {
    private String fileName;
    private String downloadUrl;
    private String fileType;
    private Long fileSize;

}
