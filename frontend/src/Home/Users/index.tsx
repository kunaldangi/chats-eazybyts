import { useMessageStore } from "../../Store/Messages";
import { User, useUserStore } from "../../Store/Users";
import "./style.css";

export default function Users() {
    const users = useUserStore((state) => state.users);
    const setChatWith = useUserStore((state) => state.setChatWith);
    const setMessages = useMessageStore((state) => state.setMessages);

    function handleUserClick(user: User) {
        setChatWith(user);
        setMessages([]); // clear!
    }

    return (<>
        <div className="chats__users">
            {
                users.map((user) => {
                    return (
                        <div className={ user.isOnline ? "chats__user user__online" : "chats__user user__offline"} key={user.id} onClick={() => handleUserClick(user)}>
                            <span>{user.username}</span>
                            {/* <span className="chats__user--status-msg">{user.email}</span>  */} {/* FOR LATER USE?? */}
                        </div>
                    )
                })
            }
        </div>
    </>);
}