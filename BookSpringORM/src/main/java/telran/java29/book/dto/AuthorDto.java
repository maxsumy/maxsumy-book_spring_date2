package telran.java29.book.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthorDto {
	String name;
	String birthDate;
	@JsonIgnore
	Set<BookDto> books;

	public AuthorDto(String name, String birthDate) {
		this.name = name;
		this.birthDate = birthDate;
	}

}
