package bootcamp.hibernate_practical.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateBookRequest {
    @NotBlank(message="Book must have a title")
    private String title;
    @NotBlank(message="Book must have an author")
    private String author;
    @NotBlank(message="Book must have a genre")
    private String genre;
    @NotNull(message="Book must have a publication year")
    @Min(value=1040, message="Publication year must be greater than 1040")
    private int publicationYear;
}
