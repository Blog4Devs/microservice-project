import React, { useState, useEffect } from "react";
import axios from "axios";
import { AddClientForm } from "./AddClientForm";
import { ClientTable } from "./ClientTable";
import { Button } from "@nextui-org/react";
import { UserIcon } from "../navbar";
import { addNewClient, deleteClient } from "../../services/clientService";
import { getAllInvoices, getClients } from "../../services/invoiceService";
import AddVehiculeForm from "../vehicule/AddVehiculeForm";
import Modal from "../utils/Modal";
import VehiculeModal from "../utils/VehiculeModal";
import { addNewVehicule } from "../../services/vehiculeService";

const users = [
  {
    id: 1,
    name: "Tony Reichert",
    address: "CEO",
    phone: "Management",
    cin: "T315326",

    age: "29",
    avatar: "https://i.pravatar.cc/150?u=a042581f4e29026024d",
    email: "tony.reichert@example.com",
  },
  {
    id: 3,
    name: "Tony Reichert",
    address: "CEO",
    phone: "Management",
    cin: "T315326",

    age: "29",
    avatar: "https://i.pravatar.cc/150?u=a042581f4e29026024d",
    email: "tony.reichert@example.com",
  },

  {
    id: 4,
    name: "Testtt",
    address: "CEO",
    phone: "Management",
    cin: "T315326",

    age: "29",
    avatar: "https://i.pravatar.cc/150?u=a042581f4e29026024d",
    email: "tony.reichert@example.com",
  },
  {
    id: 5,
    name: "Tony Reichert",
    address: "CEO",
    phone: "Management",
    cin: "T315326",
    age: "29",
    avatar: "https://i.pravatar.cc/150?u=a042581f4e29026024d",
    email: "tony.reichert@example.com",
  },
];

const ClientManager = () => {
  const [clients, setClients] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [clientId, setClientId] = useState(null);
  const clientsPerPage = 5;

  useEffect(() => {
    const fetchData = async () => {
      try {
        const data = await getClients();
        setClients(data.content);
      } catch (error) {
        //alert('Failed to fetch invoices!');
      }
    };

    fetchData();
  }, []);

  const handleDeleteClient = async (id) => {
    const bool = confirm("Are you sure?");
    if (bool) await deleteClient(id);
  };

  const updateClient = (id) => {
    console.log("Client updated: " + id);
  };

  const search = (kw) => {
    console.log(kw);
  };

  const openModal = (id) => {
    setIsModalOpen(true);
    setClientId(id);
  };

  const closeModal = () => {
    setIsModalOpen(false);
  };

  const handleAddVehicule = async (newVehicle) => {
    try {
      const res = await addNewVehicule(newVehicle);
      console.log(res);
    } catch (error) {
      console.log("");
    }
  };

  const indexOfLastClient = currentPage * clientsPerPage;
  const indexOfFirstClient = indexOfLastClient - clientsPerPage;
  const currentClients = clients.slice(indexOfFirstClient, indexOfLastClient);

  const paginate = (pageNumber) => setCurrentPage(pageNumber);

  return (
    <div className="p-6">
      <h1 className="text-2xl font-bold mb-4">Client Manager</h1>
      <ClientTable
        clients={currentClients}
        onDelete={handleDeleteClient}
        onUpdate={updateClient}
        onSearch={search}
        openModal={openModal}
      />
      <Pagination
        clientsPerPage={clientsPerPage}
        totalClients={clients.length}
        paginate={paginate}
      />
      {isModalOpen && (
        <VehiculeModal
          onClose={closeModal}
          onAddVehicle={handleAddVehicule}
          clientId={clientId}
        />
      )}
    </div>
  );
};

const Pagination = ({ clientsPerPage, totalClients, paginate }) => {
  const pageNumbers = [];

  for (let i = 1; i <= Math.ceil(totalClients / clientsPerPage); i++) {
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

export default ClientManager;
