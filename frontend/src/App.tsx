import { useEffect, useState } from 'react';
import { AlignJustify } from 'lucide-react';

import './App.css';
import { useWebSocketStore } from './Store/WebSocket';
import { useUserStore } from './Store/Users';
import Users from './Home/Users';
import StatusToast from './components/StatusToast';

function App() {
	const connect = useWebSocketStore((state) => state.connect);
	const disconnect = useWebSocketStore((state) => state.disconnect);

	const setUsers = useUserStore((state) => state.setUsers);

	const toast = useWebSocketStore((state) => state.toast);
	const setToast = useWebSocketStore((state) => state.setToast);

	async function getUsers() {
		let response = await fetch("/api/users/", {
			method: "GET",
			headers: {
				"Content-Type": "application/json",
			}
		});

		let data = await response.json();
		console.log(data);
		if (data?.status === "success") {
			setUsers(data?.users);
		}
	}

	useEffect(() => {
		getUsers();
		connect();
		return () => disconnect()
	}, []);

	return (<>
		<div className="chats__sidebar">
			<div className="chats__title">CHATS</div>
			<Users />
		</div>
		<div className="chats__nav">
			<div className="chats__nav--sidebar"><AlignJustify /></div>
			<div className="chats__nav--title">CHATS</div>
		</div>
		<StatusToast message={toast.message} type={toast.type} onClose={() => setToast("", "default")} />
	</>)
}

export default App
