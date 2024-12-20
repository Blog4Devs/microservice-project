import React, { useState, useEffect } from "react";
import AddVehiculeForm from "./AddVehiculeForm";
import VehiculeTable from "./VehiculeTable";
import { getVehicules } from "../../services/invoiceService";

/* const initialVehicles = [
  {
    id: 1,
    vin: "1HGCM82633A123456",
    numMatriculation: "123-XYZ",
    marque: "Toyota",
    annee: 2015,
    color: "Red",
    kilometrage: 120000,
    carburant: "Diesel",
    dateAchat: "2020-05-15",
    vehiculeState: "ACTIVE",
  },
  {
    id: 2,
    vin: "2HGCM82633A654321",
    numMatriculation: "456-ABC",
    marque: "Ford",
    annee: 2018,
    color: "Blue",
    kilometrage: 80000,
    carburant: "Petrol",
    dateAchat: "2021-07-10",
    vehiculeState: "INACTIVE",
  },
]; */

// Parent Component
const VehiculeManager = () => {
  const [vehicles, setVehicles] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const vehiclesPerPage = 5;

  useEffect(() => {
    fetchMaintenances()
  }, [])

  const fetchMaintenances = async () => {
    try {
      const response = await getVehicules();
      console.log(response);
      setVehicles(response.content);
    } catch (error) {
      console.error("Error fetching vehicules:", error);
    }
  };

  // Delete vehicle handler
  const deleteVehicle = (id) => {
    console.log("Vehicle deleted: " + id);
    setVehicles(vehicles.filter((vehicle) => vehicle.id !== id));
  };

  const updateVehicle = (id) => {
    console.log("Vehicle updated: " + id);
  };

  const indexOfLastVehicle = currentPage * vehiclesPerPage;
  const indexOfFirstVehicle = indexOfLastVehicle - vehiclesPerPage;
  const currentVehicles = vehicles.slice(
    indexOfFirstVehicle,
    indexOfLastVehicle
  );

  const paginate = (pageNumber) => setCurrentPage(pageNumber);

  return (
    <div className="p-6">
      <h1 className="text-2xl font-bold mb-4">Vehicule Manager</h1>
      <VehiculeTable
        vehicles={currentVehicles}
        onDelete={deleteVehicle}
        onUpdate={updateVehicle}
      />
      <Pagination
        itemsPerPage={vehiclesPerPage}
        totalItems={vehicles.length}
        paginate={paginate}
      />
    </div>
  );
};

// Pagination Component
const Pagination = ({ itemsPerPage, totalItems, paginate }) => {
  const pageNumbers = [];

  for (let i = 1; i <= Math.ceil(totalItems / itemsPerPage); i++) {
    pageNumbers.push(i);
  }

  return (
    <div className="mt-4">
      <ul className="flex justify-center">
        {pageNumbers.map((number) => (
          <li key={number} className="mx-1">
            <button
              onClick={() => paginate(number)}
              className="border-collapse border border-gray-300 px-3 py-1 rounded hover:bg-gray-400"
            >
              {number}
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default VehiculeManager;
