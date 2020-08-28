package com.hifive.api.domain.common;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hifive.api.HifiveObject;
import com.hifive.api.internal.mapping.ApiField;
import com.hifive.api.internal.mapping.ApiListField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/** <p>
 *  订单dto
 * </p>
 * @author yong.huang
 *@Date:
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order extends HifiveObject {
    @ApiField("subject")
    private String subject;
    @ApiField("totalFee")
    private Integer totalFee;
    @ApiField("orderId")
    private String orderId;
    @ApiField("HForderId")
    @JsonProperty("HForderId")
    private String HForderId;
    @ApiField("deadline")
    private String deadline;
    @ApiField("createTime")
    private String createTime;
    @ApiListField("music")
    private List<HifiveMusicFile> music;
    
}
