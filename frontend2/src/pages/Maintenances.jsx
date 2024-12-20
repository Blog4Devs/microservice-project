import React, { useState, useEffect } from "react";
import axios from "axios";
import Table from "../components/Table";
import MaintenanceForm from "../components/MaintenanceForm";
import OperationForm from "../components/OperationForm";
import OperationsModal from "../components/OperationsModal";
import { api } from "../axios";

const Maintenances = () => {
  const [maintenances, setMaintenances] = useState([]);
  const [filters, setFilters] = useState({
    status: "",
    vehicleId: "",
    idProprietaire: "",
    paid: "",
  });
  const [isFormOpen, setIsFormOpen] = useState(false);

  useEffect(() => {
    api
      .get("/MAINTENANCE-SERVICE/api/maintenances?page=0&size=10")
      .then((res) => setMaintenances(res.data.content));
  }, []);

  const columns = [
    { key: "id", label: "ID" },
    { key: "vehicleId", label: "Vehicle ID" },
    { key: "idProprietaire", label: "clientId" },
    { key: "description", label: "Description" },
    { key: "status", label: "Status" },
    { key: "startTime", label: "Start Time" },
    { key: "predictedEndTime", label: "Predicted End Time" },
    { key: "endTime", label: "End Time" },
    { key: "paid", label: "isPaid" },
  ];

  const filteredMaintenances = maintenances.filter((maintenance) => {
    return (
      (!filters.status || maintenance.status.startsWith(filters.status)) &&
      (!filters.vehicleId ||
        maintenance.vehicleId.toString() === filters.vehicleId) &&
      (!filters.idProprietaire ||
        maintenance.idProprietaire
          .toString()
          .startsWith(filters.idProprietaire)) &&
      (filters.paid === "" || maintenance.paid.toString() === filters.paid)
    );
  });
  const [selectedMaintenance, setSelectedMaintenance] = useState(null);
  const [isOperationFormOpen, setIsOperationFormOpen] = useState(false);
  const handleFilterChange = (key, value) => {
    setFilters({ ...filters, [key]: value });
  };
  const handleAddOperation = (maintenance) => {
    setSelectedMaintenance(maintenance);
    setIsOperationFormOpen(true);
  };

  const renderActions = (maintenance) => (
    <div className="flex space-x-2">
      <button
        onClick={() => handleAddOperation(maintenance)}
        className="bg-green-600 text-white px-4 py-2 rounded-lg hover:bg-green-500 flex-shrink-0"
      >
        Add Operation
      </button>
      <button
        onClick={() => handleViewOperations(maintenance)}
        className="bg-blue-600 text-white px-4 py-2 rounded-lg hover:bg-blue-500 flex-shrink-0"
      >
        View Operations
      </button>
    </div>
  );
  const [operationsModalData, setOperationsModalData] = useState(null);

  // Function to open the operations modal
  const handleViewOperations = (maintenance) => {
    setOperationsModalData({
      maintenanceId: maintenance.id,
      operations: maintenance.operations,
    });
  };

  // Function to close the modal
  const closeOperationsModal = () => {
    setOperationsModalData(null);
  };
  return (
    <div className="min-h-screen bg-gradient-to-r from-green-50 to-green-100 p-8">
      <h1 className="text-4xl font-extrabold text-center text-blue-700 mb-6">
        Maintenance Management
      </h1>
      <div className="flex justify-center mb-6">
        <button
          onClick={() => setIsFormOpen(true)}
          className="bg-blue-600 text-white px-6 py-3 rounded-lg shadow-lg hover:bg-blue-500 transition duration-300"
        >
          Add Maintenance
        </button>
      </div>
      <div className="filters bg-white shadow-xl rounded-lg p-8 mb-6">
        <h2 className="text-2xl font-semibold text-gray-700 mb-4">Filters</h2>
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
          <input
            type="text"
            placeholder="Filter by Vehicle ID"
            value={filters.vehicleId}
            onChange={(e) => handleFilterChange("vehicleId", e.target.value)}
            className="border border-gray-300 rounded-lg p-3 focus:ring-2 focus:ring-blue-500 focus:outline-none"
          />
          <input
            type="text"
            placeholder="Filter by idProprietaire"
            value={filters.idProprietaire}
            onChange={(e) =>
              handleFilterChange("idProprietaire", e.target.value)
            }
            className="border border-gray-300 rounded-lg p-3 focus:ring-2 focus:ring-blue-500 focus:outline-none"
          />
          <select
            value={filters.paid}
            onChange={(e) => handleFilterChange("paid", e.target.value)}
            className="border border-gray-300 rounded-lg p-3 focus:ring-2 focus:ring-blue-500 focus:outline-none"
          >
            <option value="">Filter By pay status</option>
            <option value="true">Paid</option>
            <option value="false">Unpaid</option>
          </select>
          <select
            value={filters.status}
            onChange={(e) => handleFilterChange("status", e.target.value)}
            className="border border-gray-300 rounded-lg p-3 focus:ring-2 focus:ring-blue-500 focus:outline-none"
          >
            <option value="">Filter by Status</option>
            <option value="FINISHED">FINISHED</option>
            <option value="PROCESSING">PROCESSING</option>
          </select>
        </div>
      </div>
      <Table
        data={filteredMaintenances}
        columns={columns}
        actions={renderActions}
        className="bg-white shadow-lg rounded-lg overflow-hidden"
      />
      {isFormOpen && <MaintenanceForm closeForm={() => setIsFormOpen(false)} />}
      {isOperationFormOpen && (
        <OperationForm
          closeForm={() => setIsOperationFormOpen(false)}
          maintenanceId={selectedMaintenance.id}
        />
      )}
      {operationsModalData && (
        <OperationsModal
          maintenanceId={operationsModalData.maintenanceId}
          operations={operationsModalData.operations}
          closeModal={closeOperationsModal}
          reloadData={() =>
            api
              .get("/MAITENANCE-SERVICE/api/maintenances")
              .then((res) => setMaintenances(res.data))
          }
        />
      )}
    </div>
  );
};

export default Maintenances;
