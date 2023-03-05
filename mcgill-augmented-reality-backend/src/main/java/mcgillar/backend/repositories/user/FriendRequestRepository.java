package mcgillar.backend.repositories.user;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import mcgillar.backend.model.user.AppUser;
import mcgillar.backend.model.user.FriendRequest;

public interface FriendRequestRepository extends CrudRepository<FriendRequest, Integer> {
    ArrayList<FriendRequest> findFriendRequestBySender(AppUser sender);
    
    ArrayList<FriendRequest> findFriendRequestByReciever(AppUser reciever);

    FriendRequest findFriendRequestBySenderAndReciever(AppUser sender, AppUser reciever);
}
