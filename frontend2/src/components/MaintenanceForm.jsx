import React, { useState } from "react";
import axios from "axios";
import { api } from "../axios";

const MaintenanceForm = ({ closeForm, vehicleId }) => {
  const [maintenance, setMaintenance] = useState({
    startTime: "",
    predictedEndTime: "",
    description: "",
    status: "PROCESSING",
    operations: [],
    vehicleId: vehicleId || "",
    paid: false,
    idProprietaire: "",
  });

  const [operation, setOperation] = useState({ description: "", price: "" });

  const addOperation = () => {
    setMaintenance({
      ...maintenance,
      operations: [...maintenance.operations, operation],
    });
    setOperation({ description: "", price: "" });
  };

  const removeOperation = (index) => {
    const updatedOperations = maintenance.operations.filter(
      (_, i) => i !== index
    );
    setMaintenance({ ...maintenance, operations: updatedOperations });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    api
      .post("/MAINTENANCE-SERVICE/api/maintenances/schedule", maintenance)
      .then(() => {
        alert("Maintenance added!");
        closeForm(); // Close the form after submission
      });
  };
  const isoToDateOnly = (isoString) => {
    const date = new Date(isoString);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, "0"); // Months are zero-based
    const day = String(date.getDate()).padStart(2, "0");
    return `${year}-${month}-${day}`;
  };
  const handleClickOperation = (operation) => {
    // Populate the form fields with the clicked operation
    setOperation(operation);
  };
  console.log("mainte", maintenance);
  return (
    <div className="fixed inset-0 flex items-center justify-center z-50 bg-black bg-opacity-50">
      <div className="bg-white rounded-lg shadow-lg p-4 max-w-4xl w-full overflow-hidden">
        <h2 className="text-3xl font-bold text-gray-800 mb-6">
          Add Maintenance
        </h2>
        <form
          onSubmit={handleSubmit}
          className="grid grid-cols-1 sm:grid-cols-2 gap-6 text-black overflow-auto max-h-[80vh]"
        >
          <div>
            <label
              htmlFor="start-time"
              className="block text-left font-medium text-gray-700"
            >
              Start Time
            </label>
            <input
              id="start-time"
              type="date"
              className="w-full p-4 border rounded-lg"
              value={isoToDateOnly(maintenance.startTime)}
              onChange={(e) =>
                setMaintenance({
                  ...maintenance,
                  startTime: new Date(e.target.value).toISOString(),
                })
              }
            />
          </div>

          <div>
            <label
              htmlFor="predicted-end-time"
              className="block text-left font-medium text-gray-700"
            >
              Predicted End Time
            </label>
            <input
              id="predicted-end-time"
              type="date"
              className="w-full p-4 border rounded-lg"
              value={isoToDateOnly(maintenance.predictedEndTime)}
              onChange={(e) =>
                setMaintenance({
                  ...maintenance,
                  predictedEndTime: new Date(e.target.value).toISOString(),
                })
              }
            />
          </div>

          <div>
            <label
              htmlFor="description"
              className="block text-left font-medium text-gray-700"
            >
              Description
            </label>
            <input
              id="description"
              type="text"
              className="w-full p-4 border rounded-lg"
              value={maintenance.description}
              onChange={(e) =>
                setMaintenance({ ...maintenance, description: e.target.value })
              }
            />
          </div>
          <div>
            <label
              htmlFor="clientId"
              className="block text-left font-medium text-gray-700"
            >
              Client ID
            </label>
            <input
              id="clientId"
              type="number"
              className="w-full p-4 border rounded-lg"
              value={maintenance.idProprietaire}
              onChange={(e) =>
                setMaintenance({
                  ...maintenance,
                  idProprietaire: Number(e.target.value),
                })
              }
            />
          </div>
          <div>
            <label
              htmlFor="vehiculeId"
              className="block text-left font-medium text-gray-700"
            >
              Vehicule ID
            </label>
            <input
              id="vehiculeId"
              type="number"
              className="w-full p-4 border rounded-lg"
              value={maintenance.vehicleId}
              onChange={(e) =>
                setMaintenance({
                  ...maintenance,
                  vehicleId: Number(e.target.value),
                })
              }
            />
          </div>
          <div className="space-y-2 col-span-2">
            <h4>Add or Edit Operation</h4>
            <label
              htmlFor="operation-description"
              className="block text-left font-medium text-gray-700"
            >
              Operation Description
            </label>
            <input
              id="operation-description"
              type="text"
              className="w-full p-4 border rounded-lg"
              value={operation.description}
              onChange={(e) =>
                setOperation({ ...operation, description: e.target.value })
              }
            />

            <label
              htmlFor="price"
              className="block text-left font-medium text-gray-700"
            >
              Price
            </label>
            <input
              id="price"
              type="number"
              className="w-full p-4 border rounded-lg"
              value={operation.price}
              onChange={(e) =>
                setOperation({ ...operation, price: parseInt(e.target.value) })
              }
            />

            <button
              type="button"
              onClick={addOperation}
              className="bg-blue-600 text-white p-4 rounded-lg mt-2"
            >
              {operation.description && operation.price
                ? "Update Operation"
                : "Add Operation"}
            </button>
          </div>

          <div className="col-span-2">
            <h4>Added Operations</h4>
            <ul className="space-y-2">
              {maintenance.operations.map((op, index) => (
                <li key={index} className="flex justify-between items-center">
                  <span
                    onClick={() => handleClickOperation(op)}
                    className="cursor-pointer text-blue-600"
                  >
                    {op.description} - ${op.price}
                  </span>
                  <button
                    type="button"
                    onClick={() => removeOperation(index)}
                    className="bg-red-600 text-white px-2 py-1 rounded-lg hover:bg-red-500"
                  >
                    Remove
                  </button>
                </li>
              ))}
            </ul>
          </div>

          <div className="flex justify-between col-span-2">
            <button
              type="submit"
              className="bg-green-600 text-white px-6 py-3 rounded-lg shadow-lg hover:bg-green-500 transition duration-300"
            >
              Add Maintenance
            </button>
            <button
              type="button"
              onClick={closeForm}
              className="bg-red-600 text-white px-6 py-3 rounded-lg shadow-lg hover:bg-red-500 transition duration-300"
            >
              Cancel
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default MaintenanceForm;
