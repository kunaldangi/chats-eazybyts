import { useState } from "react";

import "./style.css";

interface MessageInputProps {
    onSend: (message: string) => Promise<void>;
}

export default function MessageInput({ onSend }: MessageInputProps) {
    const [message, setMessage] = useState("");

    const handleSend = async () => {
        if (message.trim()) {
            await onSend(message);
            setMessage("");
        }
    };

    return (
        <div className="message-input-container">
            <textarea
                className="message-input"
                value={message}
                onChange={(e) => setMessage(e.target.value)}
                onKeyDown={(e) => e.key === "Enter" && handleSend()}
                placeholder="Type a message..."
            />
            <button onClick={handleSend} className="send-button">
                Send
            </button>
        </div>
    );
}
