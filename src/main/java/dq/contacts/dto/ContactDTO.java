package dq.contacts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record ContactDTO(
        Integer id,
        @NotEmpty String name,
        @NotEmpty String phoneNumber,
        @NotEmpty @Email String email
) {
}
