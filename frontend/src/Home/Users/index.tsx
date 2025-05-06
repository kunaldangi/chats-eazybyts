import { useUserStore } from "../../Store/Users";
import "./style.css";

export default function Users() {
    const users = useUserStore((state) => state.users);

    return (<>
        <div className="chats__users">
            {
                users.map((user) => {
                    return (
                        <div className={ user.isOnline ? "chats__user user__online" : "chats__user user__offline"} key={user.id}>
                            <span>{user.username}</span>
                            {/* <span className="chats__user--status-msg">{user.email}</span>  */} {/* FOR LATER USE?? */}
                        </div>
                    )
                })
            }
        </div>
    </>);
}