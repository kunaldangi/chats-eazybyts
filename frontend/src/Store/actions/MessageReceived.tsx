import { Message, useMessageStore } from "../Messages";
import { User, useUserStore } from "../Users";
import { useWebSocketStore } from "../WebSocket";

export default function MessageReceived(message: Message) {
    useMessageStore.getState().addMessage(message);
    
    const users = useUserStore.getState().users;
    const sender = users.find((user: User) => user.id === message.senderId);
    
    useWebSocketStore.getState().setToast(`${sender?.username} sent you a message!`, "info");
};