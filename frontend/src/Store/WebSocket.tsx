// store/websocketStore.ts
import { create } from 'zustand';

import OnlineUserAdded from './actions/OnlineUserAdded';
import OnlineUserRemoved from './actions/OnlineUserRemoved';

type Toast = {
	message: string
	type: "success" | "error" | "warning" | "info" | "default"
}

type Message = {
	id: string
	text: string
	sender: string
	timestamp: string
}

type WebSocketState = {
	toast: Toast
	socket: WebSocket | null
	messages: Message[]
	connect: () => void
	disconnect: () => void
	sendMessage: (msg: string) => void
	setToast: (message: string, type: "success" | "error" | "warning" | "info" | "default") => void
}

export const useWebSocketStore = create<WebSocketState>((set, get) => ({
	toast: {
		message: "",
		type: "default",
	},
	socket: null,
	messages: [],

	connect: () => {
		const ws = new WebSocket('ws://localhost:8080/ws/app')

		ws.onopen = () => {
			console.log("✅ Connected to WebSocket");
      		ws.send(JSON.stringify({ type: 'connected', message: 'Connected!'}));
		}
		ws.onmessage = (event) => {
			const data = JSON.parse(event.data);
			
			console.log(data);
			if (data.type === 'online_user_added'){
				OnlineUserAdded(JSON.parse(data.content));
			}
			if (data.type === 'online_user_removed'){
				OnlineUserRemoved(JSON.parse(data.content));
			}
		}
		ws.onclose = () => {
			console.log("❌ WebSocket connection closed");
		}
		ws.onerror = (err) => {
			console.error("🚨 WebSocket error", err);
		}
		set({ socket: ws })
	},

	disconnect: () => {
		get().socket?.close()
		set({ socket: null })
	},

	sendMessage: (text) => {
		const socket = get().socket
		if (socket && socket.readyState === WebSocket.OPEN) {
			socket.send(JSON.stringify({ text }))
		}
	},

	setToast: (message, type) => {
		set({ toast: { message, type } })
	}
}));
