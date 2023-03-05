package mcgillar.backend.services.user;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import mcgillar.backend.TO.user.FriendRequestTO;
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
    
    public FriendRequestTO createFriendRequest(String sender, String reciever) {
        FriendRequest friendRequest = new FriendRequest();

        AppUser senderUser = appUserRepository.findAppUserByUsername(sender);
        AppUser recieverUser = appUserRepository.findAppUserByUsername(reciever);

        friendRequest.setSender(senderUser);
        friendRequest.setReciever(recieverUser);
        friendRequest.setStatus(FriendRequestStatus.PENDING);

        friendRequestRepository.save(friendRequest);

        return new FriendRequestTO(friendRequest);
    }

    public FriendRequestTO declineFriendRequest(String sender, String reciever) {
        AppUser senderUser = appUserRepository.findAppUserByUsername(sender);
        AppUser recieverUser = appUserRepository.findAppUserByUsername(reciever);
        
        FriendRequest friendRequest = friendRequestRepository.findFriendRequestBySenderAndReciever(senderUser, recieverUser);
        friendRequest.setStatus(FriendRequestStatus.DECLINED);
        friendRequestRepository.save(friendRequest);

        return new FriendRequestTO(friendRequest);
    }
    
    public FriendRequestTO acceptFriendRequest(String sender, String reciever) {
        AppUser senderUser = appUserRepository.findAppUserByUsername(sender);
        AppUser recieverUser = appUserRepository.findAppUserByUsername(reciever);
        
        FriendRequest friendRequest = friendRequestRepository.findFriendRequestBySenderAndReciever(senderUser, recieverUser);
        friendRequest.setStatus(FriendRequestStatus.ACCEPTED);
        friendRequestRepository.save(friendRequest);

        return new FriendRequestTO(friendRequest);
    }
    
    public void revokeFriendRequest(String sender, String reciever) {
        AppUser senderUser = appUserRepository.findAppUserByUsername(sender);
        AppUser recieverUser = appUserRepository.findAppUserByUsername(reciever);
        
        FriendRequest friendRequest = friendRequestRepository.findFriendRequestBySenderAndReciever(senderUser, recieverUser);
        friendRequestRepository.delete(friendRequest);
    }
}
