package com.swiftling.dto.wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ValidationExceptionWrapper {

    private String errorField;
    private Object rejectedValue;
    private String reason;

}
