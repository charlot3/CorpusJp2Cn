package ecnu.jwss.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "grammar")
public class DbGrammar {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int sentenceId;
	private String grammar;
}
