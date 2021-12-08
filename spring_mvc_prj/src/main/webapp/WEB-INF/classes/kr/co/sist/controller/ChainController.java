package kr.co.sist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("chain/") // Controller에 사용되는 공통경로를 붙여서 작성할때
public class ChainController {

	@RequestMapping(value="chain_a.do",method=GET)
	public String chainA() {
		return "forward:chain_b.do"; //ViewResolver를 사용하지 않고 do로 이동
	}
	@RequestMapping(value="chain_b.do",method=GET)
	public String chainB() {
		return "chain/chain_result";
	}
}
