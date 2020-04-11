package com.ingenral.app.rest.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ingenral.app.data.entities.BaseEntity;
import com.ingenral.app.data.entities.Contact;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactDTO extends BaseEntity {

    @NotNull(message = "Address is required")
    @JsonProperty("addressLine1")
    private String addressLine1;

    @JsonProperty("addressLine2")
    private String addressLine2;

    @NotNull(message = "Postal code is required")
    @JsonProperty("postalCode")
    private String postalCode;

    @NotNull(message = "Phone number is required")
    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("userId")
    private Long userId;

    public Contact toEntity(){
        Contact contact = new Contact();
        contact.setId(getId());
        contact.setUserId(userId);
        contact.setAddressLine1(addressLine1);
        contact.setAddressLine2(addressLine2);
        contact.setPostalCode(postalCode);
        contact.setPhoneNumber(phoneNumber);
        return contact;
    }

    public static ContactDTO entityToDto(Contact contact) {
        return ContactDTO.builder()
                .id(contact.getId())
                .userId(contact.getUserId())
                .addressLine1(contact.getAddressLine1())
                .addressLine2(contact.getAddressLine2())
                .postalCode(contact.getPostalCode())
                .phoneNumber(contact.getPhoneNumber())
                .build();
    }

}
