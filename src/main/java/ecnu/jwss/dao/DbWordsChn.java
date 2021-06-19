package ecnu.jwss.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "wordsChn")
public class DbWordsChn {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String character;
	private String type;
	private int belongs;
}
