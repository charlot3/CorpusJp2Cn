package ecnu.jwss.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DbGrammarRepository extends JpaRepository<DbGrammar, Integer> {

	public List<DbGrammar> findByGrammar(String grammar);

	public List<DbGrammar> findBySentenceIdIn(List<Integer> ids);

	public List<DbGrammar> findBySentenceId(int id);

	public List<DbGrammar> findByGrammarLike(String grammarLike);

}