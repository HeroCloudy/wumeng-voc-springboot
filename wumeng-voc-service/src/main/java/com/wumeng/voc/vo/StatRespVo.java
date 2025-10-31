package com.wumeng.voc.vo;

import com.alibaba.fastjson2.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatRespVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 6673979780409485842L;

    private Long total;
    private List<JSONObject> list = new ArrayList<>();
}
