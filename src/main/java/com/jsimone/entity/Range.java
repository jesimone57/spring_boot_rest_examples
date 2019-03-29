package com.jsimone.entity;

import com.jsimone.error.ErrorResponse;
import com.jsimone.exception.ErrorResponseException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.HttpStatus;

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

    public void validate() {
        if (this.getStart() != null && this.getEnd() != null && this.getEnd() < this.getStart()) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "/palindromes", "GET",
                    String.format("Invalid range.  start value=%d must be before end value=%d.", this.getStart(), this.getEnd()));
            throw new ErrorResponseException(errorResponse);
        }
    }
}
