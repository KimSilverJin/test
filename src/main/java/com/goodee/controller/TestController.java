package com.goodee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Redirect, Forward
/* - 기존의 Servlet에서 제공하는 Redirect와 Forward는 Servlet에서 직접적으로 제공하는 객체인
 *   HttpServletRequest 혹은 HttpServletResponse 객체를 통해서만 구현이 가능하였다.
 * - 하지만 기존 스펙의 사용법이 어렵고 직관적이지 못해 사용자 입장에서는 상당히 불편하였다.
 * - Spring MVC에서는 이러한 부분을 개선하여 return하는 경로 앞에 [redirect:]혹은 [forward:]를 붙여주면
 *   해당 경로로 redirect 혹은 forward 방식의 전송이 가능하도록 인터페이스를 제공한다.\
 * - 주의점 !! : 기존 Servlet에서 재공하는 것처럼 redirect와 forward를 사용시 화면 페이지를 막바로 호출은
 *   할 수가 없고 반드시 controller의 경로를 기준으로 redirect 혹은 forward를 진행하여야 한다.
 * */


@Controller
public class TestController {
	
	@GetMapping("/test1")
	public String test1() {
		return "redirect:/sub1"; 
		// redirect: 이건 왜 적는겨 >>redirect:로 전송하겠다는거랭
	}
	
	@GetMapping("/sub1")
	public String sub1() {
		return "sub1";
	}
	
	@GetMapping("/test2")
	public String test2() {
		return "forward:/sub2";
	}
	
	@GetMapping("/sub2")
	public String sub2() {
		return "sub2";
	}
	
	@GetMapping("/test3")
	public String test3() {
		String data1 = "김근형";
		return "redirect:/sub3?data1="+data1;
	}
	
	@GetMapping("/sub3")
	public String sub3(@RequestParam String data1) {
		System.out.println("data1 : "+data1);
		return "sub3";
	}
	
	// RedirectAttributes
	/* - url 뒤쪽에 넣는 데이터의 경우 잘못하면 깨질 수가 있다.
	 * - 원래는 base64형태의 인코딩을 하여 데이터를 넣어 전송해야 하지만 자바 내에서 해당 로직을 전부 제공하기에는
	 *   로직이 복잡하고 번거롭다.
	 * - Spring MVC Framework에서는 해당 인코딩 타입을 자동으로 지원해주는 인터페이스 파라미터인 
	 *   RedirectAttribute를 지원해주어 데이터가 깨지지 않고 이동할 수 있도록 편의를 제공하고 있다.
	 * - 또한 일반 파라미터 뿐만이 아닌 PathVariable에도 해당 내용을 사용할 수 있다.
	 * */
	
	
	@GetMapping("/test4")
	public String test4(RedirectAttributes attr) {
		attr.addAttribute("data1", "김근형");
		return "redirect:/sub4";
	}
	
	@GetMapping("/sub4")
	public String sub4(@RequestParam String data1) {
		System.out.println("data1 : "+data1);
		return "sub4";
	}
	
	@GetMapping("/test5")
	public String test5(RedirectAttributes attr) {
//		attr.addAttribute("path", "aaa");
		attr.addAttribute("path", "bbb");
		attr.addAttribute("data1", "김근형");
		return "redirect:/sub5/{path}";
	}
	
	@GetMapping("/sub5/aaa")
	public String sub5aaa(@RequestParam String data1) {
		System.out.println("aaa.data1 : "+data1);
		return "sub5/aaa";
	}
	
	@GetMapping("/sub5/bbb")
	public String sub5bbb(@RequestParam String data1) {
		System.out.println("bbb.data1 : "+data1);
		return "sub5/bbb";
	}
	
}
