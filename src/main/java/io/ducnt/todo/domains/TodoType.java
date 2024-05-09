package io.ducnt.todo.domains;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@JsonPropertyOrder(alphabetic = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TodoType {

    @Id
    @NotBlank
    @Size(min = 4, max = 10)
    private String code;

    private String description;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dateCreated;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date lastUpdated;

}
