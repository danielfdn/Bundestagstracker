package com.daniel.bundestagstracker.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

public class PollDTO {
    private Long id;
    private String label;

    private Boolean field_accepted;

    @JsonProperty("field_poll_date")
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }


    public boolean isField_accepted() {
        return field_accepted;
    }

    public void setField_accepted(boolean field_accepted) {
        this.field_accepted = field_accepted;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
