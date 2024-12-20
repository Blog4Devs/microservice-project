import { useState } from "react";

const AddOperationForm = ({ maintenance, onAddOperation, closeModal }) => {
    const [operation, setOperation] = useState({ description: "", price: "" });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setOperation({ ...operation, [name]: value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        onAddOperation(maintenance.id, operation);
        setOperation({ description: "", price: "" });
        closeModal();
    };

    return (
        <form onSubmit={handleSubmit} className="p-4">
        <h2 className="text-xl font-bold mb-4 text-gray-600">
            Add Operation for: {maintenance.description}
        </h2>
        <div className="mb-4">
            <input
                type="text"
                name="description"
                placeholder="Operation Description"
                value={operation.description}
                onChange={handleChange}
                className="border p-2 w-full text-gray-600"
                required
            />
        </div>
        <div className="mb-4">
            <input
                type="number"
                name="price"
                placeholder="Price"
                value={operation.price}
                onChange={handleChange}
                className="border p-2 w-full text-gray-600"
                required
            />
        </div>
        <button
            type="submit"
            className="bg-blue-500 text-white px-4 py-2 rounded"
        >
            Add Operation
        </button>
        </form>
    );
};

export default AddOperationForm;