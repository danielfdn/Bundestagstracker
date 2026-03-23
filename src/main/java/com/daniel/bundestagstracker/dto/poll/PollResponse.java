package com.daniel.bundestagstracker.dto.poll;

import java.util.List;

public class PollResponse {
    private List<PollDTO> data;

    public List<PollDTO> getData() {
        return data;
    }

    public void setData(List<PollDTO> data) {
        this.data = data;
    }
}

