package com.hifive.api.domain.common;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.hifive.api.HFObject;
import com.hifive.api.internal.mapping.ApiField;
import com.hifive.api.internal.mapping.ApiListField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class Order extends HFObject {
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

    @ApiField("status")
    private Integer status;
    @ApiListField("music")
    private List<HFMusicFile> music;
    
}
