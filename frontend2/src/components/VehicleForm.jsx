import React, { useState } from "react";
import axios from "axios";
import { api } from "../axios";

const VehicleForm = ({ closeForm, clientId }) => {
  const [vehicle, setVehicle] = useState({
    vin: "",
    numMatriculation: "",
    marque: "",
    annee: "",
    color: "",
    kilometrage: "",
    carburant: "ESSENCE",
    dateAchat: "",
    delivered: false,
    clientId: clientId,
  });

  const handleSubmit = (e) => {
    e.preventDefault();
    api.post("/VEHICULE-SERVICE/api/vehicules", vehicle).then(() => {
      alert("Vehicle added!");
      closeForm(); // Close the form after submission
    });
  };
  const isoToDateOnly = (isoString) => {
    const date = new Date(isoString);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, "0"); // Months are zero-based
    const day = String(date.getDate()).padStart(2, "0");
    return `${year}-${month}-${day}`;
  };

  return (
    <div className="fixed inset-0 flex items-center justify-center z-50 bg-black bg-opacity-50">
      <div className="bg-white rounded-lg shadow-lg p-4 max-w-4xl w-full">
        <h2 className="text-3xl font-bold text-gray-800 mb-6">Add Vehicle</h2>
        <form
          onSubmit={handleSubmit}
          className="grid grid-cols-1 sm:grid-cols-2 gap-6 text-black"
        >
          <div>
            <label
              htmlFor="vin"
              className="block font-medium text-gray-700 text-left "
            >
              VIN
            </label>
            <input
              id="vin"
              type="text"
              className="w-full p-2 border rounded-lg"
              value={vehicle.vin}
              onChange={(e) => setVehicle({ ...vehicle, vin: e.target.value })}
            />
          </div>
          <div>
            <label
              htmlFor="numMatriculation"
              className="block font-medium text-gray-700 text-left "
            >
              Num Matriculation
            </label>
            <input
              id="numMatriculation"
              type="text"
              className="w-full p-2 border rounded-lg"
              value={vehicle.numMatriculation}
              onChange={(e) =>
                setVehicle({ ...vehicle, numMatriculation: e.target.value })
              }
            />
          </div>
          <div>
            <label
              htmlFor="marque"
              className="block font-medium text-gray-700 text-left  "
            >
              Marque
            </label>
            <input
              id="marque"
              type="text"
              className="w-full p-2 border rounded-lg"
              value={vehicle.marque}
              onChange={(e) =>
                setVehicle({ ...vehicle, marque: e.target.value })
              }
            />
          </div>
          <div>
            <label
              htmlFor="annee"
              className="block  font-medium text-gray-700 text-left"
            >
              Year
            </label>
            <input
              id="annee"
              type="number"
              className="w-full p-2 border rounded-lg"
              value={vehicle.annee}
              onChange={(e) =>
                setVehicle({ ...vehicle, annee: Number(e.target.value) })
              }
            />
          </div>
          <div>
            <label
              htmlFor="color"
              className="block font-medium text-gray-700 text-left "
            >
              Color
            </label>
            <input
              id="color"
              type="text"
              className="w-full p-2 border rounded-lg"
              value={vehicle.color}
              onChange={(e) =>
                setVehicle({ ...vehicle, color: e.target.value })
              }
            />
          </div>
          <div>
            <label
              htmlFor="kilometrage"
              className="block font-medium text-gray-700 text-left "
            >
              Kilometrage
            </label>
            <input
              id="kilometrage"
              type="number"
              className="w-full p-2 border rounded-lg"
              value={vehicle.kilometrage}
              onChange={(e) =>
                setVehicle({ ...vehicle, kilometrage: Number(e.target.value) })
              }
            />
          </div>
          <div>
            <label
              htmlFor="carburant"
              className="block font-medium text-gray-700 text-left "
            >
              Carburant
            </label>
            <select
              id="carburant"
              value={vehicle.carburant}
              onChange={(e) =>
                setVehicle({ ...vehicle, carburant: e.target.value })
              }
              className="w-full p-2 border rounded-lg"
            >
              <option value="ESSENCE">ESSENCE</option>
              <option value="DIESEL">DIESEL</option>
            </select>
          </div>
          <div>
            <label
              htmlFor="dateAchat"
              className="block font-medium text-gray-700 text-left "
            >
              Date Achat
            </label>
            <input
              id="dateAchat"
              type="date"
              className="w-full p-2 border rounded-lg"
              value={isoToDateOnly(vehicle.dateAchat)}
              onChange={(e) =>
                setVehicle({
                  ...vehicle,
                  dateAchat: new Date(e.target.value).toISOString(),
                })
              }
            />
          </div>
          <div>
            <label
              htmlFor="clientId"
              className="block font-medium text-gray-700 text-left "
            >
              Client ID
            </label>
            <input
              id="clientId"
              type="number"
              className="w-full p-2 border rounded-lg"
              value={vehicle.clientId}
              onChange={(e) =>
                setVehicle({ ...vehicle, clientId: Number(e.target.value) })
              }
            />
          </div>
          <div className="flex justify-between col-span-2">
            <button
              type="submit"
              className="bg-blue-600 hover:bg-blue-500 text-white px-6 py-3 rounded-lg shadow-lg  transition duration-300"
            >
              Add Vehicle
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

export default VehicleForm;
