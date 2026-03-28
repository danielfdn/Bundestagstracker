package com.daniel.bundestagstracker.dto.poll;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PollDTO {

    private Long id;
    private String label;
    private Boolean field_accepted;
    private LocalDate field_poll_date;


}
