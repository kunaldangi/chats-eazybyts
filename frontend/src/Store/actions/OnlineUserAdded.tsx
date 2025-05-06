import { User, useUserStore } from "../Users";
import { useWebSocketStore } from "../WebSocket";

export default function OnlineUserAdded(user: User) {
    useWebSocketStore.getState().setToast(`${user.username} is online`, "info");
    useUserStore.getState().updateUser(user.id, { isOnline: true });
}