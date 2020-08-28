package com.hifive.api.response;

import com.hifive.api.HifiveResponse;
import com.hifive.api.domain.common.RunnerGroup;
import com.hifive.api.internal.mapping.ApiListField;

import java.util.List;

/**
 * 分页。
 *
 * @author yong.huang
 * @since 1.0, Sep 12, 2009
 */
public class HifiveTrafficGroupResponse extends HifiveResponse {



    @ApiListField("data")
    List<RunnerGroup> runnerGroups;

    public List<RunnerGroup> getRunnerGroups() {
        return runnerGroups;
    }

    public void setRunnerGroups(List<RunnerGroup> runnerGroups) {
        this.runnerGroups = runnerGroups;
    }
}
