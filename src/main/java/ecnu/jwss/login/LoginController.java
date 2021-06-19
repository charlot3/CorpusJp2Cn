package ecnu.jwss.login;

import java.sql.SQLException;
import java.util.Base64;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ecnu.jwss.dao.DbUser;
import ecnu.jwss.dao.DbUserRepository;

@Controller
@RequestMapping("/main")
public class LoginController {
	@Autowired
	private DbUserRepository userRepository;

	@PostMapping
	public String create(HttpServletResponse response, @RequestParam String username, @RequestParam String password, Model model) throws SQLException {
		//System.out.println(username + "\n" + password);
		DbUser user = userRepository.findByUsername(username);
		if (user == null) {
			model.addAttribute("errorMsg", "该用户不存在");
			return "/index";
		} else {
			String encoded = Base64.getEncoder().encodeToString(password.getBytes());
			//System.out.println(encoded + '\n' + user.getPassword());
			if (!encoded.equals(user.getPassword())) {
				model.addAttribute("errorMsg", "密码错误，请重新登录");
				return "/index";
			} else {
				// 创建 cookie对象
				Cookie cookie = new Cookie("id", Integer.toString(user.getId()));
				Cookie cookie2 = new Cookie("isAdmin", Integer.toString(user.getAdmin()));
				//将cookie对象加入response响应
				response.addCookie(cookie);
				response.addCookie(cookie2);
				return "/main";
			}
		}
	}

}
