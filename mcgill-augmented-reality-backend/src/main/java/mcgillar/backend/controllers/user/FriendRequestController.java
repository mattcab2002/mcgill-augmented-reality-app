package mcgillar.backend.controllers.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import mcgillar.backend.services.user.FriendRequestService;

@AllArgsConstructor
@RestController
@RequestMapping("/friend-req/")
public class FriendRequestController {

    private FriendRequestService friendRequestService;
    
    /**
     * 
     * @param reciever
     * @param authentication
     * @return
     * 
     * Send a friend request to the given user
     */
    @PostMapping("send") 
    public ResponseEntity<String> createFriendRequest(
        @RequestParam String reciever,
        Authentication authentication) {
        
        String sender = authentication.getName();
        friendRequestService.createFriendRequest(sender, reciever);
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }

    /**
     * 
     * @param sender
     * @param authentication
     * @return
     * 
     * Accept a friend request from the given user
     */
    @PutMapping("decline")
    public ResponseEntity<String> declineFriendRequest(
        @RequestParam String sender,
        Authentication authentication) {
        
        String reciever = authentication.getName();
        friendRequestService.declineFriendRequest(sender, reciever);
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }

    @PutMapping("accept")
    public ResponseEntity<String> acceptFriendRequest(
        @RequestParam String sender,
        Authentication authentication) {
        
        String reciever = authentication.getName();
        friendRequestService.acceptFriendRequest(sender, reciever);
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }
}
