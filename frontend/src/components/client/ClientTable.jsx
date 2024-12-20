import React, { useState } from "react";
import { DeleteIcon, EditIcon, SearchIcon } from "../icons/icons";
import { Link } from "react-router-dom";

export const ClientTable = ({
  clients,
  onDelete,
  onUpdate,
  onSearch,
  openModal,
}) => {
  const [searchTerm, setSearchTerm] = useState("");

  return (
    <div className="mt-6">
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
          <Link to="/ui/clients/form">Add Client</Link>
        </button>
      </div>

      {/* Table */}
      <div className="overflow-x-auto">
        <table className="table-auto w-full border-collapse border border-gray-300">
          <thead>
            <tr>
              <th className="border border-gray-300 px-4 py-2">ID Client</th>
              <th className="border border-gray-300 px-4 py-2">Name</th>
              <th className="border border-gray-300 px-4 py-2">CIN</th>
              <th className="border border-gray-300 px-4 py-2">Address</th>
              <th className="border border-gray-300 px-4 py-2">Phone</th>
              <th className="border border-gray-300 px-4 py-2">Email</th>
              <th className="border border-gray-300 px-4 py-2">Vehicules</th>
              <th className="border border-gray-300 px-4 py-2">Actions</th>
            </tr>
          </thead>
          <tbody>
            {clients.map((client) => (
              <tr key={client.id}>
                <td className="border border-gray-300 px-4 py-2">
                  {client.id}
                </td>
                <td className="border border-gray-300 px-4 py-2">
                  {client.name}
                </td>
                <td className="border border-gray-300 px-4 py-2">
                  {client.cin}
                </td>
                <td className="border border-gray-300 px-4 py-2">
                  {client.address}
                </td>
                <td className="border border-gray-300 px-4 py-2">
                  {client.phone}
                </td>
                <td className="border border-gray-300 px-4 py-2">
                  {client.email}
                </td>
                <td className="border border-gray-300 px-4 py-2">
                  <button
                    onClick={() => openModal(client.id)}
                    className="bg-blue-500 text-white px-4 py-1 rounded hover:bg-blue-700 mr-2"
                  >
                    +
                  </button>
                  <button
                    onClick={() => {}}
                    className="bg-blue-500 text-white px-4 py-1 rounded hover:bg-blue-700"
                  >
                    See Vehicules {">>"}
                  </button>
                </td>
                <td className="border border-gray-300 px-4 py-2">
                  <button
                    onClick={() => onDelete(client.id)}
                    className="bg-red-500 text-white px-4 py-1 rounded hover:bg-red-700 mr-2"
                  >
                    <DeleteIcon />
                  </button>
                  <button
                    onClick={() => onUpdate(client.id)}
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
