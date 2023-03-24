package com.fourTL.controller.site;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fourTL.dao.AccountsDAO;
import com.fourTL.dao.AuthoritiesDAO;
import com.fourTL.dao.RolesDAO;
import com.fourTL.entities.Accounts;
import com.fourTL.entities.Authorities;
import com.fourTL.entities.Roles;

@CrossOrigin("*")
@RestController
public class RegisterRestController {
	@Autowired
	AccountsDAO accountsDAO;

	@Autowired
	AuthoritiesDAO authoritiesDAO;

	@Autowired
	RolesDAO rolesDAO;

	@Autowired
	BCryptPasswordEncoder pe;

	@PostMapping("/register")
	public ResponseEntity<Accounts> post(@RequestBody Accounts account) {
		if (accountsDAO.existsById(account.getUsername())) {
			return ResponseEntity.badRequest().build();
		} else {
			if (account.getUsername() != null && account.getPassword() != null && account.getFullname() != null
					&& account.getEmail() != null && account.getAddress() != null) {
				account.setPhoto("user.png");
				account.setPassword(pe.encode(account.getPassword()));
				Roles role = rolesDAO.findById("CUST").get();

				Authorities authorities = new Authorities();
				authorities.setAccount(account);
				authorities.setRole(role);

				accountsDAO.save(account);
				authoritiesDAO.save(authorities);
				return ResponseEntity.ok(account);
			} else {
				return ResponseEntity.badRequest().build();
			}
		}
	}
}
