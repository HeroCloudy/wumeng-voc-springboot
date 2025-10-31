package com.wumeng.voc.vo;

import com.wumeng.voc.entity.voc.VocSurvey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyDetailVo extends VocSurvey {

    @Serial
    private static final long serialVersionUID = 2664624843349616351L;

    private List<ComponentVo> componentList = new ArrayList<>();
}
