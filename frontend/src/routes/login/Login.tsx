import { useState } from "react";
import { useNavigate } from "react-router-dom";

import "./style.css";
import StatusToast from "../../components/StatusToast";

export default function Login() {
    const navigate = useNavigate();
    const [toast, setToast] = useState<{ message: string; type: "success" | "error" | "warning" | "info" | "default" }>({
        message: "",
        type: "default",
    });

    async function handleLogin() {
        const username: string = (document.getElementById("id_login__info--username") as HTMLInputElement).value;
        const password: string = (document.getElementById("id_login__info--password") as HTMLInputElement).value;

        const response = await fetch("/api/auth/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                "email": username,
                "password": password,
            }),
        });

        const data = await response.json();
        if (data.status == 'error') {
            showToast(data.message, "error");
        }
        if(data.status == 'success') {
            showToast(data.message, "success");
            setTimeout(() => {
                navigate("/");
            }, 3000);
        }
    }

    const showToast = (message: string, type: typeof toast.type) => {
        setToast({ message, type });
        setTimeout(() => {
            setToast({ message: "", type: "default" });
        }, 3000);
    };

    return (
        <div className="login__main--outter">
            <div className="login__main">
                <div className="login__title">Login</div>
                <div className="login__info">
                    <div className="login__info--input">
                        <input id="id_login__info--username" type="text" placeholder="Username or Email" />
                        <input id="id_login__info--password" type="password" placeholder="Password" />
                    </div>
                </div>
                <div className="login__button">
                    <button onClick={handleLogin}>Login</button>
                </div>
            </div>
            <StatusToast message={toast.message} type={toast.type} onClose={() => setToast({ message: "", type: "default" })} />
        </div>
    )
}