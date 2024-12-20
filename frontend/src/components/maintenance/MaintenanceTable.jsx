import { div } from "framer-motion/client";
import { SearchIcon } from "../icons/icons";
import { Link } from "react-router-dom";
import { useState } from "react";

const MaintenanceTable = ({ maintenances, openModal }) => {
  const [searchTerm, setSearchTerm] = useState("");
  return (
    <div className="mt-6 overflow-x-auto">
      <div className="flex justify-between items-center mb-4">
        <div className="flex items-center space-x-2">
          <input
            type="text"
            placeholder="Search by name"
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
            className="border text-gray-600 border-gray-300 px-4 py-2 rounded w-64" // Fixed width here
          />
          <button
            onClick={() => {
              onSearch(searchTerm);
            }}
            className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-700 flex items-center"
          >
            <SearchIcon />
            <span className="ml-2">Search</span>
          </button>
        </div>
        <button className="bg-green-500 text-white px-4 py-2 rounded hover:bg-blue-700 flex items-center">
          <Link to="/ui/maintenances/form">Add Maintenance</Link>
        </button>
      </div>
      <div className="overflow-x-auto">
        <table className="table-auto w-full border-collapse border border-gray-300">
          <thead>
            <tr>
              <th className="border px-4 py-2">Description</th>
              <th className="border px-4 py-2">Predicted End Time</th>
              <th className="border px-4 py-2">Vehicle ID</th>
              <th className="border px-4 py-2">Operations</th>
              <th className="border px-4 py-2">Actions</th>
            </tr>
          </thead>
          <tbody>
            {maintenances.map((maintenance) => (
              <tr key={maintenance.id}>
                <td className="border px-4 py-2">{maintenance.description}</td>
                <td className="border px-4 py-2">
                  {maintenance.predictedEndTime}
                </td>
                <td className="border px-4 py-2">{maintenance.vehicleId}</td>
                <td className="border px-4 py-2">
                  {maintenance.operations.map((op) => (
                    <div key={op.id}>
                      {op.description} -$ {op.price}
                    </div>
                  ))}
                </td>
                <td className="border px-4 py-2">
                  <button
                    onClick={() => openModal(maintenance)}
                    className="bg-blue-500 text-white px-4 py-2 rounded"
                  >
                    Add Operation
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default MaintenanceTable;
