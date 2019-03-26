package com.jsimone.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@ApiModel(description = "Class representing the API error response.")
public class ErrorResponse {

    @JsonProperty("status_code")
    private int code;
    @JsonProperty("uri_path")
    private String path;
    private String method;
    @JsonProperty("error_message")
    private String message;
    @JsonProperty("errors")
    private List<FieldError> errors = new ArrayList<>();

    public ErrorResponse() {
    }

    public ErrorResponse(int code, String path, String method, String message) {
        this.code = code;
        this.path = path;
        this.method = method;
        this.message = message;
    }

    public ErrorResponse(int code, String path, String method, String message, List<FieldError> errors) {
        this.code = code;
        this.path = path;
        this.method = method;
        this.message = message;
        this.errors = errors;
    }

    public ErrorResponse(int code, HttpServletRequest request, Exception exception) {
        this.code = code;
        this.path = request.getRequestURI();
        this.message = exception.getMessage();
        this.method = request.getMethod();
        if (exception instanceof BindException || exception instanceof MethodArgumentNotValidException) {
            addBindingResultErrors(((BindException) exception).getBindingResult());
        }
    }

    private void addBindingResultErrors(BindingResult bindingResult) {
        bindingResult
                .getFieldErrors()
                .forEach(fe -> {
                    FieldError newFe = new FieldError(fe.getField(),
                            fe.getRejectedValue() != null ? fe.getRejectedValue().toString() : null, fe.getDefaultMessage());
                    errors.add(newFe);
                });
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<FieldError> getErrors() {
        return errors;
    }

    public void setErrors(List<FieldError> errors) {
        this.errors = errors;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ErrorResponse)) {
            return false;
        }
        if (this == obj) {
            return true;
        }

        ErrorResponse rhs = (ErrorResponse) obj;
        return new EqualsBuilder()
                .append(this.code, rhs.code)
                .append(this.path, rhs.path)
                .append(this.method, rhs.method)
                .append(this.message, rhs.message)
                .append(this.errors, rhs.errors)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(code)
                .append(path)
                .append(method)
                .append(message)
                .append(errors)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("code", code)
                .append("path", path)
                .append("methodh", method)
                .append("message", message)
                .append("errors", errors)
                .toString();
    }
}

