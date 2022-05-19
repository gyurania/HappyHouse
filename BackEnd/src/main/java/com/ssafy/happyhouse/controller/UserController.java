package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.happyhouse.model.dto.AddressDto;
import com.ssafy.happyhouse.model.dto.UserDto;
import com.ssafy.happyhouse.model.service.AddressService;
import com.ssafy.happyhouse.model.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	@GetMapping("/join")
	public String signUp() {
		return "user/join";
	}
	
	@PostMapping("/join")
	public String register(UserDto userDto, Model model) throws Exception {
		logger.debug("userDto info : {}", userDto);
		userService.signUp(userDto);
		return "redirect:/user/login";
	}
	
	@GetMapping("/idcheck")
//	@ResponseBody
	public @ResponseBody String idCheck(@RequestParam("ckid") String checkId) throws Exception {
		int idCount = userService.idCheck(checkId);
		JSONObject json = new JSONObject();
		json.put("idcount", idCount);
		return json.toString();
	}

	@GetMapping("/login")
	public String login() {
		return "user/login";
	}

	@PostMapping("/login")
	public String login(@RequestParam Map<String, String> map, Model model, HttpSession session,
			HttpServletResponse response) throws Exception {
		logger.debug("map : {}", map.get("id"));
		UserDto userDto = userService.login(map);
		if (userDto != null) {
			session.setAttribute("userinfo", userDto);

			Cookie cookie = new Cookie("ssafy_id", map.get("id"));
			cookie.setPath("/");
			if ("saveok".equals(map.get("idsave"))) {
				cookie.setMaxAge(60 * 60 * 24 * 365 * 40);
			} else {
				cookie.setMaxAge(0);
			}
			response.addCookie(cookie);
			return "redirect:/";
		} else {
			model.addAttribute("msg", "아이디 또는 비밀번호 확인 후 다시 로그인하세요!");
			return "user/login";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	
	
	@GetMapping("/mypage")
	public String myPage() {
		return "user/mypage";
	}
	
	@PostMapping("/mypage/passCheck")
	public ResponseEntity<?> passCheck(@RequestBody String pass,HttpSession session) throws Exception {
		UserDto user = (UserDto) session.getAttribute("userinfo");
		String id = user.getId();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pass", pass);
		if (userService.login(map) != null) {
			return new ResponseEntity<String>("true",HttpStatus.OK);
		}
		return new ResponseEntity<String>("false",HttpStatus.OK);
	}
	
	@PutMapping("/mypage")
	public ResponseEntity<?> update(@RequestBody UserDto userDto, Model model,HttpSession session) throws Exception {
		userService.update(userDto);
		session.setAttribute("userinfo", userDto);
		return new ResponseEntity<String>("수정~~~",HttpStatus.OK);
	}
	
	@DeleteMapping("/mypage")
	public ResponseEntity<?> delete(@RequestBody UserDto userDto, Model model,HttpSession session) throws Exception {
		userService.deleteUser(userDto);
		session.setAttribute("userinfo", null);
		return new ResponseEntity<String>("삭제!!!",HttpStatus.OK);
	}
}