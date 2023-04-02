package mcgillar.backend.controllers.root;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class RootController {
    

    @GetMapping
    public String home(Authentication auth) {
        return "hello world";
    }

    @GetMapping("onlyadmin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String onlyAdmin() {
        return "only admin";
    }

    @GetMapping("onlymember")
    @PreAuthorize("hasAuthority('MEMBER')")
    public String onlyMember() {
        return "only member";
    }

}
