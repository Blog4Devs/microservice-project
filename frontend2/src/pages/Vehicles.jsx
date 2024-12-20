import React, { useState, useEffect } from "react";
import axios from "axios";
import Table from "../components/Table";
import VehicleForm from "../components/VehicleForm";
import { api } from "../axios";

const Vehicles = () => {
  const [vehicles, setVehicles] = useState([]);
  const [filters, setFilters] = useState({
    marque: "",
    annee: "",
    carburant: "",
    clientId: "",
    delivered: "",
  });
  const [isFormOpen, setIsFormOpen] = useState(false);

  useEffect(() => {
    api
      .get("/VEHICULE-SERVICE/api/vehicules?page=0&size=10")
      .then((res) => setVehicles(res.data.content));
  }, []);

  const columns = [
    { key: "id", label: "ID" },
    { key: "clientId", label: "ClientId" },
    { key: "vin", label: "VIN" },
    { key: "numMatriculation", label: "Num Matriculation" },
    { key: "marque", label: "Marque" },
    { key: "annee", label: "Year" },
    { key: "color", label: "Color" },
    { key: "kilometrage", label: "Kilometrage" },
    { key: "carburant", label: "Carburant" },
    { key: "dateAchat", label: "Date Achat" },
    { key: "delivered", label: "Delivered" },
  ];

  const filteredVehicles = vehicles.filter(
    (vehicle) =>
      (!filters.delivered ||
        vehicle.delivered.toString().startsWith(filters.delivered)) &&
      (!filters.clientId ||
        vehicle.clientId.toString().startsWith(filters.clientId)) &&
      (!filters.marque ||
        vehicle.marque
          .toLowerCase()
          .startsWith(filters.marque.toLowerCase())) &&
      (!filters.annee || vehicle.annee.toString().startsWith(filters.annee)) &&
      (!filters.carburant ||
        vehicle.carburant
          .toLowerCase()
          .startsWith(filters.carburant.toLowerCase()))
  );

  const handleFilterChange = (key, value) => {
    setFilters({ ...filters, [key]: value });
  };

  return (
    <div className="min-h-screen bg-gradient-to-r from-blue-50 to-blue-100 p-8">
      <h1 className="text-4xl font-extrabold text-center text-blue-700 mb-6">
        Vehicle Management
      </h1>
      <div className="flex justify-center mb-6">
        <button
          onClick={() => setIsFormOpen(true)}
          className="bg-blue-600 text-white px-6 py-3 rounded-lg shadow-lg hover:bg-blue-500 transition duration-300"
        >
          Add Vehicle
        </button>
      </div>
      <div className="filters bg-white shadow-xl rounded-lg p-8 mb-6">
        <h2 className="text-2xl font-semibold text-gray-700 mb-4">Filters</h2>
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
          {/* Delivered Filter */}
          <div className="flex flex-col">
            <select
              id="delivered"
              value={filters.delivered}
              onChange={(e) => handleFilterChange("delivered", e.target.value)}
              className="border border-gray-300 rounded-lg p-3 focus:ring-2 focus:ring-blue-500 focus:outline-none"
            >
              <option value="">Filter by deliverance status</option>
              <option value="true">Delivered</option>
              <option value="false">Not Delivered</option>
            </select>
          </div>

          {/* Other Filters */}
          {["clientId", "marque", "annee", "carburant"].map((key) => (
            <div key={key} className="flex flex-col">
              <input
                id={key}
                type="text"
                value={filters[key]}
                onChange={(e) => handleFilterChange(key, e.target.value)}
                className="border border-gray-300 rounded-lg p-3 focus:ring-2 focus:ring-blue-500 focus:outline-none"
                placeholder={`Filter by ${
                  key.charAt(0).toUpperCase() + key.slice(1)
                }`}
              />
            </div>
          ))}
        </div>
      </div>
      <Table
        data={filteredVehicles}
        columns={columns}
        className="bg-white shadow-lg rounded-lg overflow-hidden"
      />
      {isFormOpen && <VehicleForm closeForm={() => setIsFormOpen(false)} />}
    </div>
  );
};

export default Vehicles;
