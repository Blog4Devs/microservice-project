import React, { useState } from "react";
import axios from "axios";
import { api } from "../axios";

const ClientForm = ({ closeForm }) => {
  const [client, setClient] = useState({
    name: "",
    address: "",
    phone: "",
    email: "",
    cin: "",
  });

  const handleSubmit = (e) => {
    e.preventDefault();
    api
      .post("/CLIENT-SERVICE/api/clients", client)
      .then(() => {
        alert("Client added!");
        closeForm(); // Close the form after submission
      });
  };

  return (
    <div className="fixed inset-0 flex items-center justify-center z-50 bg-black bg-opacity-50">
      <div className="bg-white rounded-lg shadow-lg p-8 max-w-lg w-full">
        <h2 className="text-3xl font-bold text-gray-800 mb-6">Add Client</h2>
        <form onSubmit={handleSubmit} className="space-y-4 text-black">
          <input
            type="text"
            placeholder="Name"
            className="w-full p-4 border rounded-lg"
            value={client.name}
            onChange={(e) => setClient({ ...client, name: e.target.value })}
          />
          <input
            type="text"
            placeholder="Address"
            className="w-full p-4 border rounded-lg"
            value={client.address}
            onChange={(e) => setClient({ ...client, address: e.target.value })}
          />
          <input
            type="text"
            placeholder="Phone"
            className="w-full p-4 border rounded-lg"
            value={client.phone}
            onChange={(e) => setClient({ ...client, phone: e.target.value })}
          />
          <input
            type="email"
            placeholder="Email"
            className="w-full p-4 border rounded-lg"
            value={client.email}
            onChange={(e) => setClient({ ...client, email: e.target.value })}
          />
          <input
            type="text"
            placeholder="CIN"
            className="w-full p-4 border rounded-lg"
            value={client.cin}
            onChange={(e) => setClient({ ...client, cin: e.target.value })}
          />
          <div className="flex justify-between">
            <button
              type="submit"
              className="bg-blue-600 text-white px-6 py-3 rounded-lg shadow-lg hover:bg-blue-500 transition duration-300"
            >
              Add Client
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

export default ClientForm;
