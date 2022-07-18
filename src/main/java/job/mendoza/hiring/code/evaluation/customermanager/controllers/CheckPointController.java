package job.mendoza.hiring.code.evaluation.customermanager.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckPointController {
	
	@RequestMapping(method = RequestMethod.GET, value = "/api/checkpoint")
	public String checkEnvironment() {
		return "The SpringBoot environment is working...regards Mendoza!!!";
	}
}
