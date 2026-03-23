package com.daniel.bundestagstracker.dto.poll;
import lombok.Data;

import java.util.Date;

@Data
public class PollDTO {

    private Long id;
    private String label;
    private Boolean field_accepted;
    private Date field_poll_date;


}
