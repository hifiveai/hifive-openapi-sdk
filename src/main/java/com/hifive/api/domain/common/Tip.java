package com.hifive.api.domain.common;

import com.hifive.api.HifiveObject;
import com.hifive.api.internal.mapping.ApiField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tip extends HifiveObject {

    @ApiField("code")
    private Integer code;
    /**
     * 舒适度
     */
    @ApiField("suitable")
    private String suitable;
    /**
     * 描述
     */
    @ApiField("desc")
    private String desc;
}
