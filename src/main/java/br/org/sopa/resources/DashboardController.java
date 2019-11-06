package br.org.sopa.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.sopa.domain.Dashboard;
import br.org.sopa.service.DashboardService;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
	
	@Autowired
	private DashboardService dashboardService;
	
	@GetMapping
	@CrossOrigin
	public ResponseEntity<Dashboard> carregar() {
		return ResponseEntity.ok().body(dashboardService.carregar());
	}
	
	

}
