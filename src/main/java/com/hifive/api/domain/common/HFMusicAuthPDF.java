package com.hifive.api.domain.common;

import com.hifive.api.HFObject;
import com.hifive.api.internal.mapping.ApiListField;
import lombok.Data;
import java.util.List;

@Data
public class HFMusicAuthPDF extends HFObject {

    @ApiListField("fileUrl")
    private List<String> fileUrl;

    public List<String> getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(List<String> fileUrl) {
        this.fileUrl = fileUrl;
    }
}

