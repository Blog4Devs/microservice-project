import React, { useState } from "react";
import { format, parseISO } from "date-fns";
import { getInvoice } from "../../services/invoiceService";

const InvoiceDetails = () => {
  const [invoiceId, setInvoiceId] = useState("");
  const [invoice, setInvoice] = useState(null);

  const handleSearch = async () => {
    if (!invoiceId) return;

    // Mock data
    const mockInvoice = {
      id: 1,
      client: {
        name: "John Doe",
        email: "john.doe@example.com",
        cin: "AB12345",
        address: "Casablanca, Morroco",
        phone: "0645879878",
      },
      vehicules: [
        {
          id: 1,
          vin: "1HGCM82633A123456",
          numMatriculation: "ABC-123",
          marque: "Toyota",
          annee: 2020,
          color: "Red",
          kilometrage: 50000,
          carburant: "Petrol",
          dateAchat: "2022-01-01T00:00:00Z",
          clientId: 1,
          isDelivered: true,
        },
        {
          id: 2,
          vin: "2HGEJ6673WH543210",
          numMatriculation: "DEF-456",
          marque: "Honda",
          annee: 2018,
          color: "Blue",
          kilometrage: 75000,
          carburant: "Diesel",
          dateAchat: "2020-05-15T00:00:00Z",
          clientId: 1,
          isDelivered: false,
        },
      ],
      maintenances: [
        {
          id: 1,
          startTime: "2024-12-01T09:00:00Z",
          predictedEndTime: "2024-12-01T12:00:00Z",
          endTime: "2024-12-01T11:30:00Z",
          description: "Oil change and tire rotation",
          status: "Completed",
          operations: [
            { id: 1, description: "Oil change", price: 50 },
            { id: 2, description: "Tire rotation", price: 30 },
          ],
          vehicleId: 1,
          idProprietaire: 1,
          isPaid: true,
        },
        {
          id: 2,
          startTime: "2024-12-05T10:00:00Z",
          predictedEndTime: "2024-12-05T14:00:00Z",
          endTime: null,
          description: "Brake replacement",
          status: "In Progress",
          operations: [
            { id: 3, description: "Brake pads replacement", price: 100 },
          ],
          vehicleId: 2,
          idProprietaire: 1,
          isPaid: false,
        },
      ],
      timestamp: "2024-12-15T10:00:00Z",
    };
    await getInvoiceById();
  };

  const getInvoiceById = async () => {
    try {
      const res = await getInvoice(invoiceId);
      setInvoice(res);
      console.log(res);
    } catch (err) {
      console.log(err);
    }
  };

  return (
    <div className="p-6 max-w-6xl mx-auto">
      <h1 className="text-3xl font-bold mb-6 text-white">Invoice Details</h1>

      <div className="flex justify-center gap-4 mb-8">
        <input
          type="text"
          placeholder="Enter Invoice ID"
          value={invoiceId}
          onChange={(e) => setInvoiceId(e.target.value)}
          className="border rounded-md p-3 w-full max-w-md text-gray-600 focus:outline-none focus:ring-2 focus:ring-blue-500"
        />
        <button
          onClick={handleSearch}
          className="bg-blue-500 text-white px-6 py-3 rounded-md hover:bg-blue-600"
        >
          Search
        </button>
      </div>

      {invoice && (
        <div className="space-y-6">
          <div className="rounded-lg border border-white shadow-lg p-6 text-start">
            <h2 className="text-2xl font-semibold text-white mb-4">
              Client Information
            </h2>
            <div className="flex flex-wrap gap-5">
              <p className="text-white">
                <strong>Name:</strong> {invoice.client.name}
              </p>
              <p className="text-white">
                <strong>CIN:</strong> {invoice.client.cin}
              </p>
              <p className="text-white">
                <strong>Email:</strong> {invoice.client.email}
              </p>
              <p className="text-white">
                <strong>Phone:</strong> {invoice.client.phone}
              </p>
              <p className="text-white">
                <strong>Address:</strong> {invoice.client.address}
              </p>
            </div>
          </div>

          <div className="p-6 text-start border border-white rounded-lg">
            <h2 className="text-2xl font-semibold mb-4 text-white">
              Vehicles:{" "}
            </h2>
            <div className="flex flex-wrap gap-4 p-4">
              {invoice.vehicules.map((vehicule) => (
                <div
                  key={vehicule.id}
                  className="rounded-md p-4 max-w-md bg-white w-80"
                >
                  <h3 className="text-xl font-semibold text-gray-700 mb-2">
                    {vehicule.marque} ({vehicule.numMatriculation})
                  </h3>
                  <p className="text-gray-600">
                    <strong>VIN:</strong> {vehicule.vin}
                  </p>
                  <p className="text-gray-600">
                    <strong>Year:</strong> {vehicule.annee}
                  </p>
                  <p className="text-gray-600">
                    <strong>Color:</strong> {vehicule.color}
                  </p>
                  <p className="text-gray-600">
                    <strong>Kilometrage:</strong> {vehicule.kilometrage} km
                  </p>
                  <p className="text-gray-600">
                    <strong>Fuel:</strong> {vehicule.carburant}
                  </p>
                  <p className="text-gray-600">
                    <strong>Delivered:</strong>{" "}
                    {vehicule.isDelivered ? "Yes" : "No"}
                  </p>

                  <div className="mt-4">
                    <h4 className="text-lg font-semibold text-gray-800">
                      Maintenances:
                    </h4>
                    {invoice.maintenances
                      .filter(
                        (maintenance) => maintenance.vehicleId === vehicule.id
                      )
                      .map((maintenance) => (
                        <div
                          key={maintenance.id}
                          className="bg-gray-100 p-4 rounded-md shadow-md mt-2"
                        >
                          <p className="text-gray-600">
                            <strong>Description:</strong>{" "}
                            {maintenance.description}
                          </p>
                          <p className="text-gray-600">
                            <strong>Status:</strong> {maintenance.status}
                          </p>
                          <p className="text-gray-600">
                            <strong>Paid:</strong>{" "}
                            {maintenance.isPaid ? "Yes" : "No"}
                          </p>
                          <p className="text-gray-600">
                            <strong>Srtart date: </strong>
                            {format(
                              parseISO(maintenance.startTime),
                              "MMMM do, yyyy HH:mm"
                            )}
                          </p>
                          <div className="mt-2">
                            <h5 className="text-md font-semibold text-gray-700">
                              Operations:
                            </h5>
                            <div className="list-disc ml-5 text-gray-600">
                              {maintenance.operations.map((operation) => (
                                <p key={operation.id}>
                                  {operation.description} - ${operation.price}
                                </p>
                              ))}
                            </div>
                          </div>
                        </div>
                      ))}
                  </div>
                </div>
              ))}
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default InvoiceDetails;
