package com.examportal.examPortal.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MailDto {
    private String to;
    private String from;
    private String subject;
    private String text;
}
