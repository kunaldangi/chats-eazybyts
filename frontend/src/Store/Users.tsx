import { create } from 'zustand';

export type User = {
    id: number;
    username: string;
    email: string;
    isOnline: boolean;
};

type UserState = {
    users: User[];
    setUsers: (users: User[]) => void;
    addUser: (user: User) => void;
    removeUser: (id: number) => void;
    updateUser: (id: number, updatedUser: Partial<User>) => void;

    chatWith: User;
    setChatWith: (user: User) => void;
}

export const useUserStore = create<UserState>((set, get) => ({
    users: [],
    setUsers: (users) => set({ users }),
    addUser: (user) => set((state) => ({ users: [...state.users, user] })),
    removeUser: (id) => set((state) => ({ users: state.users.filter(user => user.id !== id) })),
    updateUser: (id, updatedUser) => set((state) => ({
        users: state.users.map(user => user.id === id ? { ...user, ...updatedUser } : user)
    })),
    
    chatWith: { id: 0, username: '', email: '', isOnline: false },
    setChatWith: (user) => set({ chatWith: user})
}));