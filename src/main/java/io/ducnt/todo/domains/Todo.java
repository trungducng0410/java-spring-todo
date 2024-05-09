package io.ducnt.todo.domains;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.ducnt.todo.events.TodoCreationEvent;
import io.ducnt.todo.utils.validators.TitleConstraint;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.Date;

@Data
@Entity
public class Todo extends AbstractAggregateRoot<Todo> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @TitleConstraint
    private String title;

    private String description;

    private boolean done;

    @JsonFormat(pattern = "dd/MM/yyyy hh:mm")
    private Date dateCreated;

    @JsonFormat(pattern = "dd/MM/yyyy hh:mm")
    private Date dueDate;

    @JsonFormat(pattern = "dd/MM/yyyy hh:mm")
    private Date dateDone;

    @JsonFormat(pattern = "dd/MM/yyyy hh:mm")
    private Date lastUpdated;

    @ManyToOne
    @JsonProperty("type")
    private TodoType type;

    public void publishEventAfterSave() {
        registerEvent(new TodoCreationEvent());
    }

}
