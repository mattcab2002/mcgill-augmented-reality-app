package mcgillar.backend.services.user;

import java.util.Set;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import mcgillar.backend.TO.user.FriendRequestTO;
import mcgillar.backend.model.user.AppUser;
import mcgillar.backend.model.user.AppUserInfo;
import mcgillar.backend.model.user.FriendRequest;
import mcgillar.backend.model.user.FriendRequestStatus;
import mcgillar.backend.repositories.user.AppUserInfoRepository;
import mcgillar.backend.repositories.user.AppUserRepository;
import mcgillar.backend.repositories.user.FriendRequestRepository;

@Service
@AllArgsConstructor
public class FriendRequestService {
    
    private FriendRequestRepository friendRequestRepository;
    private AppUserRepository appUserRepository;
    private AppUserInfoRepository appUserInfoRepository;
    
    /**
     * 
     * @param sender
     * @param reciever
     * @return new FriendRequestTO
     * 
     * Creates a new friend request and saves it to the database
     */
    public FriendRequestTO createFriendRequest(String sender, String reciever) {
        FriendRequest friendRequest = new FriendRequest();

        // Get the sender and reciever users
        AppUser senderUser = appUserRepository.findAppUserByUsername(sender);
        AppUser recieverUser = appUserRepository.findAppUserByUsername(reciever);

        // Set the sender and reciever users
        friendRequest.setSender(senderUser);
        friendRequest.setReciever(recieverUser);
        friendRequest.setStatus(FriendRequestStatus.PENDING);

        friendRequestRepository.save(friendRequest);

        return new FriendRequestTO(friendRequest);
    }

    /**
     * 
     * @param sender
     * @param reciever
     * @return FriendRequestTO with status DECLINED
     * 
     * Declines a friend request and removes it from the database
     */
    public FriendRequestTO declineFriendRequest(String sender, String reciever) {

        // Get the sender and reciever users
        AppUser senderUser = appUserRepository.findAppUserByUsername(sender);
        AppUser recieverUser = appUserRepository.findAppUserByUsername(reciever);
        
        // Get the friend request
        FriendRequest friendRequest = friendRequestRepository.findFriendRequestBySenderAndReciever(senderUser, recieverUser);

        // Decline and remove friend request from database
        friendRequest.setStatus(FriendRequestStatus.DECLINED);
        friendRequestRepository.delete(friendRequest);

        return new FriendRequestTO(friendRequest);
    }
    
    /**
     * 
     * @param sender
     * @param reciever
     * @return FriendRequestTO with status ACCEPTED
     * 
     * Accepts a friend request and removes it from the database
     */
    public FriendRequestTO acceptFriendRequest(String sender, String reciever) {
        // Get the sender and reciever users
        AppUser senderUser = appUserRepository.findAppUserByUsername(sender);
        AppUser recieverUser = appUserRepository.findAppUserByUsername(reciever);
        
        // Update the friend request
        FriendRequest friendRequest = friendRequestRepository.findFriendRequestBySenderAndReciever(senderUser, recieverUser);
        friendRequest.setStatus(FriendRequestStatus.ACCEPTED);

        // Get the sender and reciever user info
        AppUserInfo senderUserInfo = appUserInfoRepository.findAppUserInfoByUser(senderUser);
        AppUserInfo recieverUserInfo = appUserInfoRepository.findAppUserInfoByUser(recieverUser);

        // Add the sender and reciever to each other's friend lists
        Set<AppUser> senderFriends = senderUserInfo.getFriends();
        senderFriends.add(recieverUser);

        Set<AppUser> recieverFriends = recieverUserInfo.getFriends();
        recieverFriends.add(senderUser);

        // Save new info
        appUserInfoRepository.save(senderUserInfo);
        appUserInfoRepository.save(recieverUserInfo);

        // Remove friend request from database
        friendRequestRepository.delete(friendRequest);  

        return new FriendRequestTO(friendRequest);
    }

    /**
     * 
     * @param sender
     * @param reciever
     * 
     * Revokes a friend request and removes it from the database
     */
    public void revokeFriendRequest(String sender, String reciever) {
        // Get the sender and reciever users
        AppUser senderUser = appUserRepository.findAppUserByUsername(sender);
        AppUser recieverUser = appUserRepository.findAppUserByUsername(reciever);
        
        // Get the friend request and remove from database
        FriendRequest friendRequest = friendRequestRepository.findFriendRequestBySenderAndReciever(senderUser, recieverUser);
        friendRequestRepository.delete(friendRequest);
    }
}
