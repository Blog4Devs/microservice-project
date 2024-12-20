import React, { useState, useEffect } from "react";
import axios from "axios";
import Table from "../components/Table";
import ClientForm from "../components/ClientForm";
import VehicleForm from "../components/VehicleForm";
import { api } from "../axios";

const Clients = () => {
  const [clients, setClients] = useState([]);
  const [filters, setFilters] = useState({
    name: "",
    address: "",
    phone: "",
    email: "",
    cin: "",
  });
  const [isFormOpen, setIsFormOpen] = useState(false);
  const [isVehicleFormOpen, setIsVehicleFormOpen] = useState(false);
  const [selectedClient, setSelectedClient] = useState(null);

  useEffect(() => {
    api
      .get("/CLIENT-SERVICE/api/clients?page=0&size=10")
      .then((res) => setClients(res.data.content));
  }, []);
  const columns = [
    { key: "id", label: "ID" },
    { key: "name", label: "Name" },
    { key: "address", label: "Address" },
    { key: "phone", label: "Phone" },
    { key: "email", label: "Email" },
    { key: "cin", label: "CIN" },
  ];

  const filteredClients = clients?.filter(
    (client) =>
      (!filters.name ||
        client.name.toLowerCase().startsWith(filters.name.toLowerCase())) &&
      (!filters.address ||
        client.address
          .toLowerCase()
          .startsWith(filters.address.toLowerCase())) &&
      (!filters.phone || client.phone.startsWith(filters.phone)) &&
      (!filters.email ||
        client.email.toLowerCase().startsWith(filters.email.toLowerCase())) &&
      (!filters.cin ||
        client.cin.toLowerCase().startsWith(filters.cin.toLowerCase()))
  );

  const handleFilterChange = (key, value) => {
    setFilters({ ...filters, [key]: value });
  };

  const handleAddVehicle = (client) => {
    setSelectedClient(client);
    setIsVehicleFormOpen(true);
  };

  // Define actions for each row
  const renderActions = (client) => (
    <button
      onClick={() => handleAddVehicle(client)}
      className="bg-green-600 text-white px-4 py-2 rounded-lg hover:bg-green-500"
    >
      Add Vehicle
    </button>
  );

  return (
    <div className="min-h-screen bg-gradient-to-r from-blue-50 to-blue-100 p-8">
      <h1 className="text-4xl font-extrabold text-center text-blue-700 mb-6">
        Client Management
      </h1>
      <div className="flex justify-center mb-6">
        <button
          onClick={() => setIsFormOpen(true)}
          className="bg-blue-600 text-white px-6 py-3 rounded-lg shadow-lg hover:bg-blue-500 transition duration-300"
        >
          Add Client
        </button>
      </div>
      <div className="filters bg-white shadow-xl rounded-lg p-8 mb-6">
        <h2 className="text-2xl font-semibold text-gray-700 mb-4">Filters</h2>
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
          {["name", "address", "phone", "email", "cin"].map((key) => (
            <input
              key={key}
              type="text"
              placeholder={`Filter by ${
                key.charAt(0).toUpperCase() + key.slice(1)
              }`}
              value={filters[key]}
              onChange={(e) => handleFilterChange(key, e.target.value)}
              className="border border-gray-300 rounded-lg p-3 focus:ring-2 focus:ring-blue-500 focus:outline-none"
            />
          ))}
        </div>
      </div>
      <Table data={filteredClients} columns={columns} actions={renderActions} />
      {isFormOpen && <ClientForm closeForm={() => setIsFormOpen(false)} />}
      {isVehicleFormOpen && (
        <VehicleForm
          closeForm={() => setIsVehicleFormOpen(false)}
          clientId={selectedClient.id}
        />
      )}
    </div>
  );
};

export default Clients;
