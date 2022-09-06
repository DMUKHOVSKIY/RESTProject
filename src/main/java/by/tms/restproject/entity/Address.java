package by.tms.restproject.entity;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class Address {
    @NotEmpty
    @NotBlank
    private String city;
    @NotEmpty
    @NotBlank
    private String street;
}
