import { create } from 'zustand';

export type Message = {
    id: number;
    senderId: number;
    receiverId: number;
    content: string;
    createdAt: string;
    updatedAt: string;
}

type MessageState = {
    messages: Message[];
    setMessages: (messages: Message[]) => void;
    addMessage: (message: Message) => void;
    removeMessage: (id: number) => void;
    updateMessage: (id: number, updatedMessage: Partial<Message>) => void;
}

export const useMessageStore = create<MessageState>((set, get) => ({
    messages: [],
    setMessages: (messages) => set({ messages }),
    addMessage: (message) => set((state) => ({ messages: [...state.messages, message] })),
    removeMessage: (id) => set((state) => ({ messages: state.messages.filter(message => message.id !== id) })),
    updateMessage: (id, updatedMessage) => set((state) => ({
        messages: state.messages.map(message => message.id === id ? { ...message, ...updatedMessage } : message)
    })),
}));