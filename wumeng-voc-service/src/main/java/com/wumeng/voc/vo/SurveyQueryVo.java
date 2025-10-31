package com.wumeng.voc.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyQueryVo {

    private String id;
    private String title;
    private String description;
    private String js;
    private String css;
    private Byte isPublished;
    private Byte isStar;
    private Byte isDeleted;
    private LocalDateTime createTime;
}
