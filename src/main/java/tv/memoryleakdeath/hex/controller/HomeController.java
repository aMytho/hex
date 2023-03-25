package tv.memoryleakdeath.hex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import tv.memoryleakdeath.hex.backend.dao.TestDao;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	private TestDao testDao;

	@GetMapping("/")
	public String view(HttpServletRequest request, Model model) {
		model.addAttribute("helloMsg", "Hello World!");
		return "index";
	}

	@GetMapping("/test")
	public String testDatabase(HttpServletRequest request, Model model) {
		boolean testDatabaseResult = testDao.testDatabase();
		model.addAttribute("testResults", testDatabaseResult);
		return "test";
	}
}
