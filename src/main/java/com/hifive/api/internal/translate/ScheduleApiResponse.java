package com.hifive.api.internal.translate;


import com.hifive.api.HifiveResponse;
import com.hifive.api.internal.mapping.ApiField;

import java.util.Date;

/**
 * TOP API: Hifive.topats.schedule.add response.
 * 
 * @author auto create
 */
public class ScheduleApiResponse extends HifiveResponse {

	private static final long serialVersionUID = 5788818741119374931L;

	/**
	 * 创建时间
	 */
	@ApiField("created")
	private Date created;


	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getCreated() {
		return this.created;
	}

}
