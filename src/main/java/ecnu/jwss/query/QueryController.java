package ecnu.jwss.query;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ecnu.jwss.dao.DbGrammar;
import ecnu.jwss.dao.DbGrammarRepository;
import ecnu.jwss.dao.DbLogRepository;
import ecnu.jwss.dao.DbSentence;
import ecnu.jwss.dao.DbSentenceRepository;
import ecnu.jwss.dao.DbUserRepository;
import ecnu.jwss.dao.DbWords;
import ecnu.jwss.dao.DbWordsChn;
import ecnu.jwss.dao.DbWordsChnRepository;
import ecnu.jwss.dao.DbWordsRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/query")
public class QueryController {
	@Autowired
	private DbUserRepository userRepository;
	@Autowired
	private DbWordsRepository wordsRepository;
	@Autowired
	private DbWordsChnRepository wordsChnRepository;
	@Autowired
	private DbSentenceRepository sentenceRepository;
	@Autowired
	private DbLogRepository logRepository;
	@Autowired
	private DbGrammarRepository grammarRepository;

	@Data
	@RequiredArgsConstructor
	private static class Result {
		private final DbSentence sentence;
		private final List<DbWords> wordsJpList;
		private final List<DbWordsChn> wordsChnList;
		private final List<DbGrammar> grammarList;
	}

	@GetMapping("/wordchn")
	public String queryWord( @RequestParam String word, @RequestParam boolean useLike, Model model) throws SQLException {
		List<DbWordsChn> wordsChnList;
		if (useLike) {
			wordsChnList = wordsChnRepository.findByCharacterLike("%" + word + "%");
		} else {
			wordsChnList = wordsChnRepository.findByCharacter(word);
		}

		if(wordsChnList.isEmpty()) {
			model.addAttribute("errorMsg", "无此结果");
		} else {
			List<Integer> sentenceIdList = wordsChnList.stream().map(e -> e.getBelongs()).collect(Collectors.toList());
			List<DbSentence> sentence = sentenceRepository.findAllById(sentenceIdList);

			Map<Integer, Result> result = new HashMap<>();

			sentence.stream().forEach(s->{
				List<DbWords> wjl = wordsRepository.findByBelongs(s.getId());
				List<DbWordsChn> wcl = wordsChnRepository.findByBelongs(s.getId());
				List<DbGrammar> gl = grammarRepository.findBySentenceId(s.getId());
				result.put(s.getId(), new Result(s, wjl, wcl, gl));
			});

			model.addAttribute("results", result);
		}

		return "/main";
	}

	@GetMapping("/wordjpn")
	public String queryJpnWord( @RequestParam String word, @RequestParam boolean useLike, Model model) throws SQLException {
		List<DbWords> wordsList;
		if (useLike) {
			wordsList = wordsRepository.findByCharacterLike("%" + word + "%");
		} else {
			wordsList = wordsRepository.findByCharacter(word);
		}

		if(wordsList.isEmpty()) {
			model.addAttribute("errorMsg", "无此结果");
		} else {
			List<Integer> sentenceIdList = wordsList.stream().map(e -> e.getBelongs()).collect(Collectors.toList());
			List<DbSentence> sentence = sentenceRepository.findAllById(sentenceIdList);

			Map<Integer, Result> result = new HashMap<>();

			sentence.stream().forEach(s->{
				List<DbWords> wjl = wordsRepository.findByBelongs(s.getId());
				List<DbWordsChn> wcl = wordsChnRepository.findByBelongs(s.getId());
				List<DbGrammar> gl = grammarRepository.findBySentenceId(s.getId());
				result.put(s.getId(), new Result(s, wjl, wcl, gl));
			});

			model.addAttribute("results", result);
		}

		return "/main";
	}
}
