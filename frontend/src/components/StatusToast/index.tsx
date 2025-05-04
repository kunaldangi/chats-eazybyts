import React from "react";
import "./style.css";

type ToastType = "success" | "error" | "warning" | "info" | "default";

interface StatusToastProps {
	message: string;
	type?: ToastType;
	onClose: () => void;
}

const StatusToast: React.FC<StatusToastProps> = ({ message, type = "default", onClose }) => {
	if (!message) return null;
	return (
		<div className={`status-toast ${type}`}>
			<span>{message}</span>
			<button className="close-btn" onClick={onClose}>×</button>
		</div>
	);
};

export default StatusToast;
