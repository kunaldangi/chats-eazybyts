import { X } from "lucide-react";
import "./style.css";
export function Dialog({children, onClose}: {children: React.ReactNode, onClose?: () => void}) {

    return (<>
        <div className="dialog">
            <div className="dialog__close"><X size={32} onClick={onClose}/></div>
            {children}
        </div>
    </>)
}