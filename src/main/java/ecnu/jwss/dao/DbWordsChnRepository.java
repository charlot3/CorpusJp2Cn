package ecnu.jwss.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DbWordsChnRepository extends JpaRepository<DbWordsChn, Integer> {

	public List<DbWordsChn> findByBelongs(int belong);

	public List<DbWordsChn> findByCharacter(String charater);

	public List<DbWordsChn> findByCharacterLike(String charater);
}
