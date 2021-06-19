package ecnu.jwss.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DbWordsRepository extends JpaRepository<DbWords, Integer> {

	public List<DbWords> findByBelongs(int belong);

	public List<DbWords> findByCharacter(String charater);

	public List<DbWords> findByCharacterLike(String charater);
}
