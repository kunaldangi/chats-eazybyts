import { User, useUserStore } from "../Users";
import { useWebSocketStore } from "../WebSocket";

export default function OnlineUserRemoved(user: User){
    useWebSocketStore.getState().setToast(`${user.username} is offline`, "info");
    useUserStore.getState().updateUser(user.id, { isOnline: false });
}