package mcgillar.backend.TO.user;

import lombok.Data;
import mcgillar.backend.model.user.AppUser;
import mcgillar.backend.model.user.FriendRequest;
import mcgillar.backend.model.user.FriendRequestStatus;


@Data
public class FriendRequestTO {

    private ValidatedUser sender;

    private ValidatedUser reciever;

    private FriendRequestStatus status;

    public FriendRequestTO(AppUser sender, AppUser reciever, FriendRequestStatus status) {
        this.sender = ValidatedUser.getInstance(sender);
        this.reciever = ValidatedUser.getInstance(reciever);
        this.status = status;
    }

    public FriendRequestTO(FriendRequest friendRequest) {
        this.sender = ValidatedUser.getInstance(friendRequest.getSender());
        this.reciever = ValidatedUser.getInstance(friendRequest.getReciever());
        this.status = friendRequest.getStatus();
    }

}
