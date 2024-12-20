import React, { useState, useEffect } from "react";
import axios from "axios";
import { api } from "../axios";

const Invoices = () => {
  const [clientId, setClientId] = useState("");
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const fetchData = async () => {
    if (!clientId) return;

    const query = `
      query GetInvoiceById($id: ID!) {
        getInvoiceById(id: $id) {
          id
          client {
            id
            name
            address
            phone
            email
            cin
          }
          vehicules {
            id
            vin
            numMatriculation
            marque
            annee
            color
            kilometrage
            carburant
            dateAchat
            clientId
            isDelivered
          }
          maintenances {
            id
            startTime
            predictedEndTime
            endTime
            description
            status
            isPaid
            vehicleId
            idProprietaire
            operations {
              id
              description
              price
            }
          }
          timestamp
        }
      }
    `;

    const variables = { id: clientId };

    try {
      setLoading(true);
      const response = await api.post(
        "/graphql",
        {
          query,
          variables,
        },
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );

      setData(response.data.data.getInvoiceById);
      setError(null);
    } catch (err) {
      setError(err);
      setData(null);
    } finally {
      setLoading(false);
    }
  };

  console.log("data ", data);

  const handleInputChange = (e) => {
    setClientId(e.target.value);
  };

  const handleSearch = () => {
    fetchData();
  };

  return (
    <div className="p-8 bg-gray-50 min-h-screen text-black">
      <div className="bg-white shadow-lg rounded-lg p-6 max-w-4xl mx-auto">
        <h1 className="text-3xl font-bold text-gray-800 mb-6">
          Invoice Information
        </h1>

        <div className="flex items-center space-x-4 mb-6 justify-center">
          <input
            type="text"
            value={clientId}
            onChange={handleInputChange}
            placeholder="Enter Client ID"
            className="border border-gray-300 px-4 py-3 rounded-lg w-64 text-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
          />
          <button
            onClick={handleSearch}
            className="px-6 py-3 bg-blue-600 text-white rounded-lg text-lg font-medium hover:bg-blue-500 focus:outline-none focus:ring-2 focus:ring-blue-300"
          >
            Search
          </button>
        </div>

        {loading && <p className="text-blue-600 text-lg">Loading...</p>}
        {error && (
          <p className="text-red-600 text-lg">Error: {error.message}</p>
        )}

        {!loading && !error && data && (
          <div className="overflow-x-auto mt-6">
            <table className="table-auto w-full text-left border-collapse">
              <thead className="bg-gray-200">
                <tr>
                  <th className="border px-6 py-4 text-gray-700 text-lg font-semibold">
                    Client Details
                  </th>
                  <th className="border px-6 py-4 text-gray-700 text-lg font-semibold">
                    Vehicles
                  </th>
                  <th className="border px-6 py-4 text-gray-700 text-lg font-semibold">
                    Maintenances
                  </th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td className="border px-6 py-4">
                    <p>
                      <strong>Name:</strong> {data.client.name}
                    </p>
                    <p>
                      <strong>Address:</strong> {data.client.address}
                    </p>
                    <p>
                      <strong>Phone:</strong> {data.client.phone}
                    </p>
                    <p>
                      <strong>Email:</strong> {data.client.email}
                    </p>
                    <p>
                      <strong>CIN:</strong> {data.client.cin}
                    </p>
                  </td>
                  <td className="border px-6 py-4">
                    {data.vehicules.map((vehicule) => (
                      <div key={vehicule.id} className="mb-4">
                        <p>
                          <strong>VIN:</strong> {vehicule.vin}
                        </p>
                        <p>
                          <strong>Matriculation:</strong>{" "}
                          {vehicule.numMatriculation}
                        </p>
                        <p>
                          <strong>Brand:</strong> {vehicule.marque}
                        </p>
                        <p>
                          <strong>Year:</strong> {vehicule.annee}
                        </p>
                        <p>
                          <strong>Color:</strong> {vehicule.color}
                        </p>
                        <p>
                          <strong>Mileage:</strong> {vehicule.kilometrage} km
                        </p>
                        <p>
                          <strong>Fuel:</strong> {vehicule.carburant}
                        </p>
                      </div>
                    ))}
                  </td>
                  <td className="border px-6 py-4">
                    {data.maintenances.map((maintenance) => (
                      <div key={maintenance.id} className="mb-4">
                        <p>
                          <strong>Description:</strong>{" "}
                          {maintenance.description}
                        </p>
                        <p>
                          <strong>Status:</strong> {maintenance.status}
                        </p>
                        <p>
                          <strong>Paid:</strong>{" "}
                          {maintenance.isPaid ? "Yes" : "No"}
                        </p>
                        <p>
                          <strong>Operations:</strong>
                        </p>
                        <ul className="list-disc pl-6">
                          {maintenance.operations.map((operation) => (
                            <li key={operation.id}>
                              {operation.description} -
                              <span className="font-semibold">
                                ${operation.price}
                              </span>
                            </li>
                          ))}
                        </ul>
                      </div>
                    ))}
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        )}
      </div>
    </div>
  );
};

export default Invoices;
