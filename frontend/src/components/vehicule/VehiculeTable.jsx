import { useState } from "react";
import { DeleteIcon, EditIcon, SearchIcon } from "../icons/icons";
import { Link } from "react-router-dom";

const VehiculeTable = ({ vehicles, onDelete, onUpdate }) => {
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
          <Link to="/ui/vehicules/form">Add Vehicule</Link>
        </button>
      </div>
      <div className="overflow-x-auto">
        <table className="table-auto w-full border-collapse border border-gray-300">
          <thead>
            <tr>
              <th className="border border-gray-300 px-4 py-2">ID</th>
              <th className="border border-gray-300 px-4 py-2">VIN</th>
              <th className="border border-gray-300 px-4 py-2">
                Matriculation
              </th>
              <th className="border border-gray-300 px-4 py-2">Marque</th>
              <th className="border border-gray-300 px-4 py-2">Year</th>
              <th className="border border-gray-300 px-4 py-2">Color</th>
              <th className="border border-gray-300 px-4 py-2">Actions</th>
            </tr>
          </thead>
          <tbody>
            {vehicles.map((vehicle) => (
              <tr key={vehicle.id}>
                <td className="border border-gray-300 px-4 py-2">
                  {vehicle.id}
                </td>
                <td className="border border-gray-300 px-4 py-2">
                  {vehicle.vin}
                </td>
                <td className="border border-gray-300 px-4 py-2">
                  {vehicle.numMatriculation}
                </td>
                <td className="border border-gray-300 px-4 py-2">
                  {vehicle.marque}
                </td>
                <td className="border border-gray-300 px-4 py-2">
                  {vehicle.annee}
                </td>
                <td className="border border-gray-300 px-4 py-2">
                  {vehicle.color}
                </td>
                <td className="border border-gray-300 px-4 py-2">
                  <button
                    onClick={() => onDelete(vehicle.id)}
                    className="bg-red-500 text-white px-4 py-1 rounded hover:bg-red-700 mr-2"
                  >
                    <DeleteIcon />
                  </button>
                  <button
                    onClick={() => onUpdate(vehicle.id)}
                    className="bg-blue-500 text-white px-4 py-1 rounded hover:bg-blue-700"
                  >
                    <EditIcon />
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

export default VehiculeTable;
