package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.happyhouse.model.dto.UserDto;
import com.ssafy.happyhouse.model.service.JwtServiceImpl;
import com.ssafy.happyhouse.model.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private JwtServiceImpl jwtService;

	@Autowired
	private UserService userService;

	@ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) Map<String, String> map) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			UserDto loginUser = userService.login(map);
			if (loginUser != null) { // 로그인 성공! 토큰 만들기
				String token = jwtService.create("userid", loginUser.getId(), "access-token");// key, data, subject
				logger.debug("로그인 토큰정보 : {}", token);
				resultMap.put("access-token", token);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/info/{userid}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userid") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userid,
			HttpServletRequest request) {
//		logger.debug("userid : {} ", userid);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				UserDto userDto = userService.userInfo(userid);
				resultMap.put("userInfo", userDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

//	@GetMapping("/login")
//	public String login() {
//		return "user/login";
//	}

//	@PostMapping("/login")
//	public String login(@RequestParam Map<String, String> map, Model model, HttpSession session,
//			HttpServletResponse response) throws Exception {
//		logger.debug("map : {}", map.get("id"));
//		UserDto userDto = userService.login(map);
//		if (userDto != null) {
//			session.setAttribute("userinfo", userDto);
//
//			Cookie cookie = new Cookie("ssafy_id", map.get("id"));
//			cookie.setPath("/");
//			if ("saveok".equals(map.get("idsave"))) {
//				cookie.setMaxAge(60 * 60 * 24 * 365 * 40);
//			} else {
//				cookie.setMaxAge(0);
//			}
//			response.addCookie(cookie);
//			return "redirect:/";
//		} else {
//			model.addAttribute("msg", "아이디 또는 비밀번호 확인 후 다시 로그인하세요!");
//			return "user/login";
//		}
//	}

//	@GetMapping("/join")
//	public String signUp() {
//		return "user/join";
//	}

	@ApiOperation(value = "회원가입")
	@PostMapping("/regist")
	public ResponseEntity<String> register(@RequestBody UserDto userDto, Model model) throws Exception {
		logger.debug("userDto info : {}", userDto);
		if (userService.signUp(userDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		;
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "회원탈퇴")
	@DeleteMapping("/mypage")
	public ResponseEntity<?> delete(@RequestBody UserDto userDto, Model model, HttpSession session) throws Exception {
		userService.deleteUser(userDto);
		session.setAttribute("userinfo", null);
		return new ResponseEntity<String>("삭제!!!", HttpStatus.OK);
	}
	
	@ApiOperation(value = "아이디 중복검사")
	@GetMapping("/idcheck")
//	@ResponseBody
	public ResponseEntity<String> idCheck(@RequestBody String checkId) throws Exception {
		int idCount = userService.idCheck(checkId);
		if (idCount == 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	@ApiOperation(value = "로그인한 유저의 회원정보 반환")
	@GetMapping("/mypage/")
	public String myPage() {
		return "user/mypage";
	}

	@ApiOperation(value = "비밀번호 체크")
	@PostMapping("/mypage/passCheck")
	public ResponseEntity<?> passCheck(@RequestBody String pass, HttpSession session) throws Exception {
		UserDto user = (UserDto) session.getAttribute("userinfo");
		String id = user.getId();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pass", pass);
		if (userService.login(map) != null) {
			return new ResponseEntity<String>("true", HttpStatus.OK);
		}
		return new ResponseEntity<String>("false", HttpStatus.OK);
	}

	@PutMapping("/mypage")
	public ResponseEntity<?> update(@RequestBody UserDto userDto, Model model, HttpSession session) throws Exception {
		userService.update(userDto);
		session.setAttribute("userinfo", userDto);
		return new ResponseEntity<String>("수정~~~", HttpStatus.OK);
	}


}
