import React, { useState } from 'react';
import { addNewClient } from '../../services/clientService';

export const AddClientForm = ({ }) => {
  const [formData, setFormData] = useState({
    name: '',
    address: '',
    phone: '',
    email: '',
    cin: '', 
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    addClient(formData);
    setFormData({
      name: '',
      address: '',
      phone: '',
      email: '',
      cin: '',
    });
  };

  const addClient = async (newClient) => {
    
    const res = await addNewClient(newClient);
    console.log(res);
  };

  return (
    <div className="p-6">
      <h1 className="text-2xl font-bold mb-4">Client Manager</h1>
      <form onSubmit={handleSubmit} className="border-collapse border border-gray-300 p-4 rounded shadow-md">
        <h2 className="text-xl font-bold mb-4">Add New Client</h2>
        <div className="grid grid-cols-2 gap-4">
          <input
            type="text"
            name="name"
            placeholder="Name"
            value={formData.name}
            onChange={handleChange}
            className="text-gray-900 border border-gray-300 p-2 rounded w-full"
            required
          />
          <input
            type="text"
            name="address"
            placeholder="Address"
            value={formData.address}
            onChange={handleChange}
            className="text-gray-900 border border-gray-300 p-2 rounded w-full"
            required
          />
          <input
            type="text"
            name="phone"
            placeholder="Phone"
            value={formData.phone}
            onChange={handleChange}
            className="text-gray-900 border border-gray-300 p-2 rounded w-full"
            required
          />
          <input
            type="email"
            name="email"
            placeholder="Email"
            value={formData.email}
            onChange={handleChange}
            className="text-gray-900 border border-gray-300 p-2 rounded w-full"
            required
          />
          <input
            type="text"
            name="cin"
            placeholder="CIN"
            value={formData.cin}
            onChange={handleChange}
            className="text-gray-900 border border-gray-300 p-2 rounded w-full"
            required
          />
        </div>
        <button
          type="submit"
          className="bg-green-500 text-white px-4 py-2 rounded mt-4 hover:bg-green-700"
        >
          Add Client
        </button>
      </form>
    </div>
  );
};
