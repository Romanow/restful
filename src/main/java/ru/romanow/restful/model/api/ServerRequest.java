package ru.romanow.restful.model.api;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class ServerRequest {

    @NotEmpty(message = "{field.not.empty}")
    private String address;

    @NotEmpty(message = "{field.not.empty}")
    private String purpose;

    @Min(value = 0, message = "{field.min}")
    @Max(value = 100, message = "{field.max}")
    private Integer latency;

    @Min(value = 0, message = "{field.min}")
    @Max(value = 10_000_000, message = "{field.max}")
    private Integer bandwidth;

    @NotNull(message = "{field.not.null}")
    private Integer stateId;
}
