package com.wumeng.voc.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubmitAnswerVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 5629952765972765511L;

    private String submitter;
    private String surveyId;
    private Map<String, Object> values = new HashMap<>();
}
