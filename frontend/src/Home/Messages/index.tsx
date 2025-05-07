import { useEffect } from "react";
import "./style.css";

import { useUserStore } from "../../Store/Users";
import { useMessageStore } from "../../Store/Messages";

export function Messages(){

    const chatWith = useUserStore((state) => state.chatWith);
    const messages = useMessageStore((state) => state.messages);
    const setMessages = useMessageStore((state) => state.setMessages);

    async function getMessages(){
        const response = await fetch("/api/messages/", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                id: chatWith.id,
                username: chatWith.username,
                email: chatWith.email,
            })
        });

        const data = await response.json();
        if(data.status == "success"){
            setMessages(data.messages);
        }
        else{
            setMessages([]);
        }
    }

    useEffect(() => {
        console.log("chatWith: ", chatWith);
        getMessages();
    }, [chatWith]);

    return (<>
        {
            messages.map((message) => {
                let isOther = message.senderId === chatWith.id;
                return (
                    <div className={isOther ? "message other" : "message me"}  key={message.id}>
                        <div className="message__content">{message.content}</div>
                    </div>
                )
            })
        }
    </>);
}