package ru.romanow.restful.model;

import com.google.common.base.MoreObjects;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by romanow on 19.10.16
 */
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

    public String getAddress() {
        return address;
    }

    public String getPurpose() {
        return purpose;
    }

    public Integer getLatency() {
        return latency;
    }

    public Integer getBandwidth() {
        return bandwidth;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .omitNullValues()
                          .add("address", address)
                          .add("purpose", purpose)
                          .add("latency", latency)
                          .add("bandwidth", bandwidth)
                          .toString();
    }
}
