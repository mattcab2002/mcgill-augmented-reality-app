package mcgillar.backend.services.user;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import mcgillar.backend.model.user.AppUser;
import mcgillar.backend.model.user.FriendRequest;
import mcgillar.backend.model.user.FriendRequestStatus;
import mcgillar.backend.repositories.user.AppUserRepository;
import mcgillar.backend.repositories.user.FriendRequestRepository;

@Service
@AllArgsConstructor
public class FriendRequestService {
    
    private FriendRequestRepository friendRequestRepository;
    private AppUserRepository appUserRepository;
    
    public void createFriendRequest(String sender, String reciever) {
        FriendRequest friendRequest = new FriendRequest();

        AppUser senderUser = appUserRepository.findAppUserByUsername(sender);
        AppUser recieverUser = appUserRepository.findAppUserByUsername(reciever);

        friendRequest.setSender(senderUser);
        friendRequest.setReciever(recieverUser);

        friendRequestRepository.save(friendRequest);

        friendRequest.setStatus(FriendRequestStatus.PENDING);
    }

    public void declineFriendRequest(String sender, String reciever) {
        AppUser senderUser = appUserRepository.findAppUserByUsername(sender);
        AppUser recieverUser = appUserRepository.findAppUserByUsername(reciever);
        
        FriendRequest friendRequest = friendRequestRepository.findFriendRequestBySenderAndReciever(senderUser, recieverUser);
        friendRequest.setStatus(FriendRequestStatus.DECLINED);
        friendRequestRepository.save(friendRequest);
    }

    public void acceptFriendRequest(String sender, String reciever) {
        AppUser senderUser = appUserRepository.findAppUserByUsername(sender);
        AppUser recieverUser = appUserRepository.findAppUserByUsername(reciever);
        
        FriendRequest friendRequest = friendRequestRepository.findFriendRequestBySenderAndReciever(senderUser, recieverUser);
        friendRequest.setStatus(FriendRequestStatus.ACCEPTED);
        friendRequestRepository.save(friendRequest);
    }
}
