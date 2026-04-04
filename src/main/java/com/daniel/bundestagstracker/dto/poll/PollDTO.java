package com.daniel.bundestagstracker.dto.poll;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class PollDTO {

    private Long id;
    private String label;
    
    @JsonProperty("field_accepted")
    private Boolean fieldAccepted;
    
    @JsonProperty("field_poll_date")
    private LocalDate fieldPollDate;
    
    @JsonProperty("field_intro")
    private String fieldIntro;

    // Getters and Setters
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

    public Boolean getFieldAccepted() {
        return fieldAccepted;
    }

    public void setFieldAccepted(Boolean fieldAccepted) {
        this.fieldAccepted = fieldAccepted;
    }

    public LocalDate getFieldPollDate() {
        return fieldPollDate;
    }

    public void setFieldPollDate(LocalDate fieldPollDate) {
        this.fieldPollDate = fieldPollDate;
    }

    public String getFieldIntro() {
        return fieldIntro;
    }

    public void setFieldIntro(String fieldIntro) {
        this.fieldIntro = fieldIntro;
    }
}
