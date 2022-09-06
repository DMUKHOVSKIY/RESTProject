package by.tms.restproject.entity;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class Telephone {
    @NotEmpty
    @NotBlank
    private String code;
    @NotEmpty
    @NotBlank
    private String number;
}
