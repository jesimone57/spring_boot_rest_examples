package com.jsimone.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponseBody {

	@JsonProperty("status_code")   private int code;
	@JsonProperty("uri_path")      private String path;
	@JsonProperty("error_message") private String message;
	@JsonProperty("errors")        private List<FieldError> errors = new ArrayList<>();

	public ErrorResponseBody() {
	}

	public ErrorResponseBody(int code, String path, String message) {
		this.code = code;
		this.path = path;
		this.message = message;
	}

	public ErrorResponseBody(int code, String path, String message, List<FieldError> errors) {
		this.code = code;
		this.path = path;
		this.message = message;
		this.errors = errors;
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


	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ErrorResponseBody)) {
			return false;
		}
		if (this == obj) {
			return true;
		}

		ErrorResponseBody rhs = (ErrorResponseBody) obj;
		return new EqualsBuilder()
			.append(this.code, rhs.code)
			.append(this.path, rhs.path)
			.append(this.message, rhs.message)
			.append(this.errors, rhs.errors)
			.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
			.append(code)
			.append(path)
			.append(message)
			.append(errors)
			.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
			.append("code", code)
			.append("path", path)
			.append("message", message)
			.append("errors", errors)
			.toString();
	}
}

