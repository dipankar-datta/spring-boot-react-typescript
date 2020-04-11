package com.ingenral.app.rest.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {

    @JsonProperty("id")
    private Long id;

    @NotNull(message = "First name is required")
    @JsonProperty("firstName")
    private String firstName;

    @NotNull(message = "Last name is required")
    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("contacts")
    private List<ContactDTO> contacts;
}
