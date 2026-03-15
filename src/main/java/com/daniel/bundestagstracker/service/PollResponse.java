package com.daniel.bundestagstracker.service;

import com.daniel.bundestagstracker.dto.PollDTO;

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

