import React, { useState } from "react";
import axios from "axios";

const OperationsModal = ({ operations, closeModal, maintenanceId, reloadData }) => {
  const [updatedOperations, setUpdatedOperations] = useState([...operations]);

  const handleUpdate = (operation) => {
    api
      .put(`/MAITENANCE-SERVICE/operations/${operation.id}`, operation)
      .then(() => {
        alert("Operation updated successfully!");
        reloadData();
      })
      .catch((error) => console.error("Error updating operation:", error));
  };

  const handleDelete = (operationId) => {
    api
      .delete(`/MAITENANCE-SERVICE/operations/${operationId}`)
      .then(() => {
        alert("Operation deleted successfully!");
        reloadData();
      })
      .catch((error) => console.error("Error deleting operation:", error));
  };

  const handleInputChange = (index, key, value) => {
    const updated = [...updatedOperations];
    updated[index][key] = value;
    setUpdatedOperations(updated);
  };

  return (
    <div className="fixed inset-0 bg-gray-800 bg-opacity-50 flex items-center justify-center z-50">
      <div className="bg-white rounded-lg shadow-xl w-11/12 md:w-2/3 lg:w-1/2 p-6">
        <h2 className="text-xl font-bold text-gray-700 mb-4">Operations</h2>
        <ul>
          {updatedOperations.map((operation, index) => (
            <li
              key={operation.id}
              className="flex justify-between items-center mb-4 border-b pb-2"
            >
              <div className="flex-grow space-y-2">
                <input
                  type="text"
                  value={operation.description}
                  onChange={(e) =>
                    handleInputChange(index, "description", e.target.value)
                  }
                  className="border p-2 rounded-lg w-full"
                />
                <input
                  type="number"
                  value={operation.price}
                  onChange={(e) =>
                    handleInputChange(index, "price", parseFloat(e.target.value))
                  }
                  className="border p-2 rounded-lg w-full"
                />
              </div>
              <div className="ml-4 flex space-x-2">
                <button
                  onClick={() => handleUpdate(operation)}
                  className="bg-blue-600 text-white px-4 py-2 rounded-lg"
                >
                  Update
                </button>
                <button
                  onClick={() => handleDelete(operation.id)}
                  className="bg-red-600 text-white px-4 py-2 rounded-lg"
                >
                  Delete
                </button>
              </div>
            </li>
          ))}
        </ul>
        <div className="flex justify-end mt-4">
          <button
            onClick={closeModal}
            className="bg-gray-600 text-white px-6 py-3 rounded-lg"
          >
            Close
          </button>
        </div>
      </div>
    </div>
  );
};

export default OperationsModal;
