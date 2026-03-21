package com.daniel.bundestagstracker.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class PollDTO {

    private Long id;
    private String label;
    private Boolean field_accepted;
    private Date date;

}
