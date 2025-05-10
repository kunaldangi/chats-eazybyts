import { useEffect, useState } from 'react';
import { AlignJustify, CirclePlus, MessageCircleIcon } from 'lucide-react';

import './App.css';
import { useWebSocketStore } from './Store/WebSocket';
import { useUserStore } from './Store/Users';
import Users from './Home/Users';
import StatusToast from './components/StatusToast';
import MessageInput from './components/MessageInput';
import { Messages } from './Home/Messages';
import { Dialog } from './components/Dialog';

export default function App() {
	const connect = useWebSocketStore((state) => state.connect);
	const disconnect = useWebSocketStore((state) => state.disconnect);
	const sendMessage = useWebSocketStore((state) => state.sendMessage);

	const setUsers = useUserStore((state) => state.setUsers);

	const toast = useWebSocketStore((state) => state.toast);
	const setToast = useWebSocketStore((state) => state.setToast);

	const chatWith = useUserStore((state) => state.chatWith);

	const [dialogComponent, setDialogComponent] = useState({show: false, component: <></>});

	async function getUsers() {
		let response = await fetch("/api/users/", {
			method: "GET",
			headers: {
				"Content-Type": "application/json",
			}
		});

		let data = await response.json();
		if (data?.status === "success") {
			setUsers(data?.users);
		}
	}

	async function sendMessageToUser(message: string) {
		if (message.trim() === "") return;
		let data = {
			type: "send_message",
			content: JSON.stringify({
				to: chatWith,
				message: message,
			})
		};
		sendMessage(JSON.stringify(data));
	}

	async function handleCreateGroup() {
		setDialogComponent({show: true, component: <>
			<div className="groupCreate__dialog">
				<div className="groupCreate__dialog--title">Create Group</div>
				<div className="groupCreate__dialog--content">
					<div className="groupCreate__dialog--input">
						<input type="text" placeholder="Group Name" />
					</div>
					<div className="groupCreate__dialog--button">
						<button onClick={() => {
							setDialogComponent({show: false, component: <></>});
						}}>Create</button>
					</div>
				</div>
			</div>
		</>});
	}

	useEffect(() => {
		getUsers();
		connect();
		
		document.addEventListener("click", (e) => {
			const sidebar = document.querySelector(".chats__sidebar");
			if (sidebar && !(e.target as HTMLElement).closest(".chats__sidebar") && !(e.target as HTMLElement).closest(".chats__nav--sidebar")) {
				sidebar.classList.toggle("toggle__sidebar", false);
			}
		});

		return () => {
			disconnect();
		}
	}, []);

	return (<>
		<div className="chats__sidebar">
			<div className="chats__title">CHATS</div>
			<div className="groups">
				<div className="groups__create" onClick={handleCreateGroup}>
					<CirclePlus size={20} /> Create Group
				</div>
			</div>
			<Users />
		</div>
		<div className="chats__body">
			<div className="chats__container">
				<div className="chats__nav">
					<div className="chats__nav--sidebar"><AlignJustify onClick={()=>{
						const sidebar = document.querySelector(".chats__sidebar");
						console.log(sidebar);
						if (sidebar) {
							sidebar.classList.toggle("toggle__sidebar", true);
						}
					}} /></div>
					<div className="chats__nav--title">CHATS</div>
				</div>
				{ chatWith?.id ? (<>
					<div className="chats__profile">{chatWith.username}</div>
					<div className="chats__messages">
						<Messages />
					</div>
					<div className="chats__input">
						<MessageInput onSend={sendMessageToUser} />
					</div>
				</>) : (<>
					<div className="chats__empty">
						<span className="chats__empty--content">
							<div className="chats__empty--icon"><MessageCircleIcon size={38} /></div>
							<div className="chats__empty--title">CHATS</div>
						</span>
					</div>
				</>)}
			</div>
		</div>
		<StatusToast message={toast.message} type={toast.type} onClose={() => setToast("", "default")} />
			
		{dialogComponent.show && (
			<Dialog onClose={() => setDialogComponent({show: false, component: <></>})}>
				{dialogComponent.component}
			</Dialog>
		)}
	</>);
}