package mcgillar.backend.TO.user;

import lombok.Data;
import mcgillar.backend.model.user.AppUser;
import mcgillar.backend.model.user.FriendRequest;
import mcgillar.backend.model.user.FriendRequestStatus;


@Data
public class FriendRequestTO {

    private AppUser sender;

    private AppUser reciever;

    private FriendRequestStatus status;

    public FriendRequestTO(AppUser sender, AppUser reciever, FriendRequestStatus status) {
        this.sender = sender;
        this.reciever = reciever;
        this.status = status;
    }

    public FriendRequestTO(FriendRequest friendRequest) {
        this.sender = friendRequest.getSender();
        this.reciever = friendRequest.getReciever();
        this.status = friendRequest.getStatus();
    }

}
