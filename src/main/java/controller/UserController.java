package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import po.User;
import service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@RequestMapping("/index")
	public String index(){
		return "index";
	}
	@RequestMapping("userInfo")
	public ModelAndView userInfo(User user){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("user_info");
		user = userService.getUserById(user.getUserId());
		mv.addObject("user", user);
		return mv;
	}
}
