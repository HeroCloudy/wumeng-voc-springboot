package com.wumeng.voc.vo;

import com.wumeng.common.vo.PageReq;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SquareQueryVo extends PageReq implements Serializable {

    @Serial
    private static final long serialVersionUID = 7826728244718927025L;

    private String keyword;
    private String category;
    private String label;
}
