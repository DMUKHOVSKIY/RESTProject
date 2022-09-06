package by.tms.restproject.entity;

import by.tms.restproject.entity.Address;
import by.tms.restproject.entity.Telephone;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
public class User {
    private long id;
    @NotEmpty
    @NotBlank
    private String name;
    @NotEmpty
    @NotBlank
    private String username;
    @NotEmpty
    @NotBlank
    private String password;
    @NotNull
    @Valid
    private Telephone telephone;
    @NotNull
    @Valid
    private List<Address> address;
}
