package com.wumeng.voc.vo;

import com.alibaba.fastjson2.JSONObject;
import com.wumeng.voc.entity.voc.VocComponent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import java.io.Serial;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComponentVo extends VocComponent {
    @Serial
    private static final long serialVersionUID = -1936706751930497446L;

    private JSONObject props;

    public static ComponentVo buildVo(VocComponent component) {
        ComponentVo vo = new ComponentVo();
        BeanUtils.copyProperties(component, vo);
        if (StringUtils.hasText(component.getPropsText())) {
            vo.setProps(JSONObject.parse(component.getPropsText()));
        }
        return vo;
    }

    public static VocComponent buildEntity(ComponentVo componentVo) {
        VocComponent entity = new VocComponent();
        BeanUtils.copyProperties(componentVo, entity);
        if (componentVo.getProps() != null) {
            entity.setPropsText(JSONObject.toJSONString(componentVo.getProps()));
        } else {
            entity.setPropsText(null);
        }
        return entity;
    }
}
