import React, { useState } from "react";

const OperationForm = ({ closeForm, addOperation, operation }) => {
  const [newOperation, setNewOperation] = useState(
    operation || { description: "", price: "" }
  );

  const handleSubmit = (e) => {
    e.preventDefault();
    addOperation(newOperation);
  };

  return (
    <div className="fixed inset-0 flex items-center justify-center z-50 bg-black bg-opacity-50">
      <div className="bg-white rounded-lg shadow-lg p-8 max-w-lg w-full">
        <h2 className="text-3xl font-bold text-gray-800 mb-6">Add Operation</h2>
        <form onSubmit={handleSubmit}>
          <div className="mb-4">
            <label className="block text-gray-700 text-left">Description</label>
            <input
              type="text"
              value={newOperation.description}
              onChange={(e) =>
                setNewOperation({
                  ...newOperation,
                  description: e.target.value,
                })
              }
              className="border border-gray-300 rounded-lg p-3 w-full focus:ring-2 focus:ring-blue-500"
            />
          </div>
          <div className="mb-4">
            <label className="block text-gray-700 text-left">Price</label>
            <input
              type="number"
              value={newOperation.price}
              onChange={(e) =>
                setNewOperation({ ...newOperation, price: e.target.value })
              }
              className="border border-gray-300 rounded-lg p-3 w-full focus:ring-2 focus:ring-blue-500"
            />
          </div>
          <div className="flex justify-end space-x-4">
            <button
              type="button"
              onClick={closeForm}
              className="bg-gray-500 text-white px-4 py-2 rounded-lg"
            >
              Cancel
            </button>
            <button
              type="submit"
              className="bg-blue-600 text-white px-4 py-2 rounded-lg"
            >
              Add Operation
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default OperationForm;
