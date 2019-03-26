package com.jsimone.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@ApiModel(description = "Class representing a range of numbers.")
public class Range {

    @ApiModelProperty(notes = "Start of range", example = "1", required = true, position = 1)
    @NotNull(message = "start must be a positive number or 0")
    @PositiveOrZero
    private Integer start;

    @ApiModelProperty(notes = "End of range", example = "100", required = true, position = 2)
    @NotNull(message = "end must be a positive number or 0")
    @PositiveOrZero
    private Integer end;

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }
}
