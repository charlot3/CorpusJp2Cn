package ecnu.jwss.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DbSentenceRepository extends JpaRepository<DbSentence, Integer> {
	public DbSentence findById(int id);

	public List<DbSentence> findByJpnSentenceLike(String jpnSentence);

	public List<DbSentence> findByChnSentenceLike(String chnSentence);
}
