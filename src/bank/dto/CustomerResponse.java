package bank.dto;

public record CustomerResponse(

        String fullName,
        String email,
        String gender
) {
}
