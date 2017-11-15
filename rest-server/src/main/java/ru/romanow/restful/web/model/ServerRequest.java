package ru.romanow.restful.web.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class ServerRequest {

    @NotNull
    private String address;

    @NotNull
    private String purpose;

    @Min(0)
    @Max(100)
    private Integer latency;

    @Min(0)
    @Max(10_000_000)
    private Integer bandwidth;

    @NotEmpty
    private String country;

    @NotEmpty
    private String city;

}