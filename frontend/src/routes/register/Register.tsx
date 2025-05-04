import { useState } from "react";
import { useNavigate } from "react-router-dom";

import "./style.css";
import StatusToast from "../../components/StatusToast";

export default function Register() {
    const navigate = useNavigate();
    const [toast, setToast] = useState<{ message: string; type: "success" | "error" | "warning" | "info" | "default" }>({
        message: "",
        type: "default",
    });

    async function handleRegister() {
        const username: string = (document.getElementById("id_register__info--username") as HTMLInputElement).value;
        const password: string = (document.getElementById("id_register__info--password") as HTMLInputElement).value;
        const email: string = (document.getElementById("id_register__info--email") as HTMLInputElement).value;

        const response = await fetch("/api/auth/register", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                "username": username,
                "email": email,
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
                navigate("/login");
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
        <div className="register__main--outter">
            <div className="register__main">
                <div className="register__title">Register</div>
                <div className="register__info">
                    <div className="register__info--input">
                        <input id="id_register__info--username" type="text" placeholder="Username" />
                        <input id="id_register__info--email" type="text" placeholder="Email" />
                        <input id="id_register__info--password" type="password" placeholder="Password" />
                    </div>
                </div>
                <div className="register__button">
                    <button onClick={handleRegister}>Register</button>
                </div>
            </div>
            <StatusToast message={toast.message} type={toast.type} onClose={() => setToast({ message: "", type: "default" })} />
        </div>
    )
}