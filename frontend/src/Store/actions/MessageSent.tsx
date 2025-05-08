import { Message, useMessageStore } from "../Messages";

export default function MessageSent(message: Message) {
    useMessageStore.getState().addMessage(message);
}