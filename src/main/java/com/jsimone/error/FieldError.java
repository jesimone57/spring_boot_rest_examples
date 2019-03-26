package com.jsimone.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@ApiModel(description = "Class representing individual field errors from an API error response.")
public class FieldError {

    @JsonProperty("error_field")
    private String field;
    @JsonProperty("error_value")
    private String value;
    @JsonProperty("error_message")
    private String message;

    public FieldError() {
    }

    public FieldError(String field, String value, String message) {
        this.field = field;
        this.message = message;
        this.value = value;
    }


    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        FieldError rhs = (FieldError) obj;
        return new EqualsBuilder()
                .append(this.field, rhs.field)
                .append(this.value, rhs.value)
                .append(this.message, rhs.message)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(field)
                .append(value)
                .append(message)
                .toHashCode();
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("field", field)
                .append("value", value)
                .append("message", message)
                .toString();
    }
}
