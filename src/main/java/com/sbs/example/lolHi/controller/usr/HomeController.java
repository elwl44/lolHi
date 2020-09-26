package com.sbs.example.lolHi.controller.usr;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	@RequestMapping("/user/home/main")
	@ResponseBody
	public String showMain() {
		return "안녕";
	}

	@RequestMapping("/user/home/main2")
	@ResponseBody
	public String showMain2() {
		return "잘가";
	}

	@RequestMapping("/user/home/main3")
	@ResponseBody
	public String showMain3() {
		return "또봐";
	}

	@RequestMapping("/user/home/plus")
	@ResponseBody
	public int showPlus(int a, int b) {
		return a + b;
	}

	@RequestMapping("/user/home/min")
	@ResponseBody
	public int showmin(int a, int b) {
		return a - b;
	}

	@RequestMapping("/user/home/mul")
	@ResponseBody
	public int showmul(int a, int b) {
		return a * b;
	}

	@RequestMapping("/user/home/div")
	@ResponseBody
	public int showdiv(int a, int b) {
		return a / b;
	}
}
