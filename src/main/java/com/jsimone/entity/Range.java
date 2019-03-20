package com.jsimone.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class Range {

    @NotNull(message = "start must be a positive number or 0")
    //@Pattern(regexp = "^\\d+$")
    @PositiveOrZero
    private Integer start;
    @NotNull(message = "end must be a positive number or 0")
    //@Pattern(regexp = "^\\d+$")
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
