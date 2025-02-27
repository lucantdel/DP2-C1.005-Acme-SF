
package acme.entities.trainingModule;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractEntity;
import acme.entities.projects.Project;
import acme.roles.Developer;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "developer_id"), @Index(columnList = "project_id")
})
public class TrainingModule extends AbstractEntity {
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@Pattern(regexp = "[A-Z]{1,3}-[0-9]{3}")
	@NotBlank
	@Column(unique = true)
	protected String			code;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@NotNull
	protected Date				creationMoment;

	@NotBlank
	@Length(max = 100)
	protected String			details;

	@NotNull
	protected Difficulty		difficultyLevel;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	protected Date				updateMoment;

	@URL
	protected String			link;

	@NotNull
	@Min(1)
	protected Integer			totalEstimatedTime;

	@NotNull
	protected boolean			draftMode;

	// Derived attributes ----------------------------------------------------

	// Relationships ----------------------------------------------------------

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Project			project;

	@NotNull
	@Valid
	@ManyToOne()
	protected Developer			developer;

}
