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
import mcgillar.backend.TO.user.FriendRequestTO;
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
    public ResponseEntity<FriendRequestTO> createFriendRequest(
        @RequestParam String reciever,
        Authentication authentication) {
        
        String sender = authentication.getName();
        FriendRequestTO friendRequest = friendRequestService.createFriendRequest(sender, reciever);
        return new ResponseEntity<FriendRequestTO>(friendRequest, HttpStatus.OK);
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
    public ResponseEntity<FriendRequestTO> declineFriendRequest(
        @RequestParam String sender,
        Authentication authentication) {
        
        String reciever = authentication.getName();
        FriendRequestTO friendRequest = friendRequestService.declineFriendRequest(sender, reciever);
        return new ResponseEntity<FriendRequestTO>(friendRequest, HttpStatus.OK);
    }

    /**
     * 
     * @param sender
     * @param authentication
     * @return
     * 
     * Decline a friend request from the given user
     */
    @PutMapping("accept")
    public ResponseEntity<FriendRequestTO> acceptFriendRequest(
        @RequestParam String sender,
        Authentication authentication) {
        
        String reciever = authentication.getName();
        FriendRequestTO friendRequest = friendRequestService.acceptFriendRequest(sender, reciever);
        return new ResponseEntity<FriendRequestTO>(friendRequest, HttpStatus.OK);
    }

    /**
     * 
     * @param reciever
     * @param authentication
     * @return
     * 
     * Revoke a friend request to the given user
     */
    @DeleteMapping("revoke")
    public ResponseEntity<String> revokeFriendRequest(
        @RequestParam String reciever,
        Authentication authentication) {
        
        String sender = authentication.getName();
        friendRequestService.revokeFriendRequest(sender, reciever);
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }
}
