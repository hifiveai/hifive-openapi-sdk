package com.hifive.api.response;

import com.hifive.api.HFResponse;
import com.hifive.api.domain.common.RunnerGroup;
import com.hifive.api.internal.mapping.ApiListField;

import java.util.List;

/**
 * 分页。
 *
 * @author yong.huang
 * @since 1.0, Sep 12, 2009
 */
public class HFOrderGroupResponse extends HFResponse {



    @ApiListField("data")
    List<RunnerGroup> runnerGroups;

    public List<RunnerGroup> getRunnerGroups() {
        return runnerGroups;
    }

    public void setRunnerGroups(List<RunnerGroup> runnerGroups) {
        this.runnerGroups = runnerGroups;
    }
}
