import React, { useState, useEffect } from "react";
import axios from "axios";
import MaintenanceTable from "./MaintenanceTable";
import AddMaintenanceForm from "./AddMaintenanceForm";
import AddOperationForm from "./AddOperationForm";
import Modal from "../utils/Modal";
import { getMaintenances } from "../../services/invoiceService";

/* const fakeMaintenances = [
  {
    id: 1,
    startTime: "2024-12-01T08:30:00Z",
    predictedEndTime: "2024-12-01T12:30:00Z",
    endTime: null,
    description: "Oil change and general inspection",
    status: "PLANNED",
    vehicleId: 101,
    operations: [
      { id: 1, description: "Replace engine oil", price: 50 },
      { id: 2, description: "Check tire pressure", price: 20 },
    ],
  },
  {
    id: 2,
    startTime: "2024-12-02T10:00:00Z",
    predictedEndTime: "2024-12-02T14:00:00Z",
    endTime: "2024-12-02T13:30:00Z",
    description: "Brake pad replacement",
    status: "COMPLETED",
    vehicleId: 102,
    operations: [
      { id: 3, description: "Replace front brake pads", price: 120 },
      { id: 4, description: "Replace rear brake pads", price: 100 },
    ],
  },
  {
    id: 3,
    startTime: "2024-12-03T09:00:00Z",
    predictedEndTime: "2024-12-03T11:00:00Z",
    endTime: null,
    description: "Battery replacement",
    status: "IN_PROGRESS",
    vehicleId: 103,
    operations: [
      { id: 5, description: "Remove old battery", price: 30 },
      { id: 6, description: "Install new battery", price: 100 },
    ],
  },
  {
    id: 4,
    startTime: "2024-12-04T08:00:00Z",
    predictedEndTime: "2024-12-04T12:00:00Z",
    endTime: null,
    description: "Engine diagnostics and repair",
    status: "PLANNED",
    vehicleId: 104,
    operations: [
      { id: 7, description: "Run diagnostics", price: 80 },
      { id: 8, description: "Replace spark plugs", price: 40 },
      { id: 9, description: "Clean fuel injectors", price: 60 },
    ],
  },
  {
    id: 5,
    startTime: "2024-12-05T07:30:00Z",
    predictedEndTime: "2024-12-05T11:30:00Z",
    endTime: null,
    description: "Transmission fluid replacement",
    status: "PLANNED",
    vehicleId: 105,
    operations: [
      { id: 10, description: "Drain old transmission fluid", price: 70 },
      { id: 11, description: "Refill with new transmission fluid", price: 50 },
    ],
  },
]; */

const MaintenanceManager = () => {
  const [maintenances, setMaintenances] = useState([]);
  const [selectedMaintenance, setSelectedMaintenance] = useState(null);
  const [isModalOpen, setIsModalOpen] = useState(false);
  useEffect(() => {
    fetchMaintenances();
  }, []);

  const fetchMaintenances = async () => {
    try {
      const response = await getMaintenances();
      console.log(response);
      setMaintenances(response.content);
    } catch (error) {
      console.error("Error fetching maintenances:", error);
    }
  };

  const addOperationToMaintenance = async (maintenanceId, operation) => {
    try {
      const response = await axios.post(
        `/maintenances/${maintenanceId}/operations`,
        operation
      );
      const updatedMaintenances = maintenances.map((maintenance) =>
        maintenance.id === maintenanceId
          ? {
              ...maintenance,
              operations: [...maintenance.operations, response.data],
            }
          : maintenance
      );
      setMaintenances(updatedMaintenances);
    } catch (error) {
      console.error("Error adding operation:", error);
    }
  };

  const openModal = (maintenance) => {
    setSelectedMaintenance(maintenance);
    setIsModalOpen(true);
  };

  const closeModal = () => {
    setIsModalOpen(false);
    setSelectedMaintenance(null);
  };

  return (
    <div className="p-6">
      <h1 className="text-2xl font-bold mb-4">Maintenance Manager</h1>
      <MaintenanceTable maintenances={maintenances} openModal={openModal} />
      {isModalOpen && (
        <Modal onClose={closeModal}>
          <AddOperationForm
            maintenance={selectedMaintenance}
            onAddOperation={addOperationToMaintenance}
            closeModal={closeModal}
          />
        </Modal>
      )}
    </div>
  );
};

export default MaintenanceManager;
