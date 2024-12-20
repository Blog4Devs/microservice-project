import { CloseIcon } from "../icons/icons";

const Modal = ({ onClose, children }) => (
    <div className="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-50">
        <div className="relative bg-white p-6 rounded-lg shadow-lg w-96">
        <button
            onClick={onClose}
            className="absolute top-2 right-2 text-gray-600 hover:text-gray-900"
        >
            <CloseIcon />
        </button>
    
        <div className="mt-4">{children}</div>
        </div>
    </div>
);

export default Modal;