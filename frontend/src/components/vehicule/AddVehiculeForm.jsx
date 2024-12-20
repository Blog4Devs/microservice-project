import { useState } from "react";
import { addNewVehicule } from "../../services/vehiculeService";

const AddVehiculeForm = ({}) => {
  const [formData, setFormData] = useState({
    vin: "",
    numMatriculation: "",
    marque: "",
    annee: "",
    color: "",
    kilometrage: "",
    carburant: "",
    dateAchat: "",
    clientId: "",
  });
  const handleDateChange = (e) => {
    const inputDate = e.target.value; // assuming the input is of type="date"
    const isoDate = new Date(inputDate).toISOString(); // Convert to ISO format
    setFormData((prevData) => ({
      ...prevData,
      dateAchat: isoDate,
    }));
  };
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log(formData);
    addVehicle({ dateAchat: new Date(formData.dateAchat), ...formData });
  };

  const addVehicle = async (newVehicle) => {
    console.log("Vehicle added: ", newVehicle);
    try {
      await addNewVehicule(newVehicle);
    } catch (error) {
      console.log(error);
    }
  };
  const formatDateForInput = (dateString) => {
    if (!dateString) return ""; // Return an empty string if no date is set
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, "0");
    const day = String(date.getDate()).padStart(2, "0");
    return `${year}-${month}-${day}`;
  };

  return (
    <div className="p-6">
      <h1 className="text-2xl font-bold mb-4">Vehicule Manager</h1>
      <form
        onSubmit={handleSubmit}
        className="border-collapse border border-gray-300 p-4 rounded shadow-md"
      >
        <h2 className="text-xl font-bold mb-4">Add New Vehicle</h2>
        <div className="grid grid-cols-2 gap-4">
          {/* VIN */}
          <div className="text-start">
            <label htmlFor="vin">VIN</label>
            <input
              type="text"
              name="vin"
              id="vin"
              value={formData.vin}
              onChange={handleChange}
              className="border border-gray-300 p-2 rounded w-full text-gray-600"
              required
            />
          </div>

          {/* Matriculation */}
          <div className="text-start">
            <label htmlFor="numMatriculation">Matriculation</label>
            <input
              type="text"
              name="numMatriculation"
              id="numMatriculation"
              value={formData.numMatriculation}
              onChange={handleChange}
              className="border border-gray-300 p-2 rounded w-full text-gray-600"
              required
            />
          </div>

          {/* Marque */}
          <div className="text-start">
            <label htmlFor="marque">Marque</label>
            <input
              type="text"
              name="marque"
              id="marque"
              value={formData.marque}
              onChange={handleChange}
              className="border border-gray-300 p-2 rounded w-full text-gray-600"
              required
            />
          </div>

          {/* Year */}
          <div className="text-start">
            <label htmlFor="annee">Year</label>
            <input
              type="number"
              name="annee"
              id="annee"
              value={formData.annee}
              onChange={handleChange}
              className="border border-gray-300 p-2 rounded w-full text-gray-600"
              required
            />
          </div>

          {/* Color */}
          <div className="text-start">
            <label htmlFor="color">Color</label>
            <input
              type="text"
              name="color"
              id="color"
              value={formData.color}
              onChange={handleChange}
              className="border border-gray-300 p-2 rounded w-full text-gray-600"
              required
            />
          </div>

          {/* Kilometrage */}
          <div className="text-start">
            <label htmlFor="kilometrage">Kilometrage</label>
            <input
              type="number"
              name="kilometrage"
              id="kilometrage"
              value={formData.kilometrage}
              onChange={handleChange}
              className="border border-gray-300 p-2 rounded w-full text-gray-600"
              required
            />
          </div>

          {/* Fuel Type (Carburant) */}
          <div className="text-start">
            <label htmlFor="carburant">Fuel Type</label>
            <select
              name="carburant"
              id="carburant"
              value={formData.carburant}
              onChange={handleChange}
              className="border border-gray-300 p-2 rounded w-full text-gray-600"
              required
            >
              <option value="">Select Fuel Type</option>
              <option value="PETROL">Petrol</option>
              <option value="DIESEL">Diesel</option>
              <option value="ELECTRIC">Electric</option>
              <option value="HYBRID">Hybrid</option>
            </select>
          </div>

          {/* Purchase Date */}
          <div className="text-start">
            <label htmlFor="dateAchat">Purchase Date</label>
            <input
              type="date"
              name="dateAchat"
              id="dateAchat"
              value={formatDateForInput(formData.dateAchat)}
              onChange={handleDateChange}
              className="border border-gray-300 p-2 rounded w-full text-gray-600"
              required
            />
          </div>

          {/* Client ID */}
          <div className="text-start">
            <label htmlFor="clientId">Client ID</label>
            <input
              type="number"
              name="clientId"
              id="clientId"
              value={formData.clientId}
              onChange={handleChange}
              className="border border-gray-300 p-2 rounded w-full text-gray-600"
            />
          </div>

          {/* Delivered status */}
          {/* <div className="flex items-center">
                <input
                    type="checkbox"
                    name="isDelivered"
                    checked={formData.isDelivered}
                    onChange={handleChange}
                    className="mr-2"
                />
                <label htmlFor="isDelivered">Delivered</label>
            </div> */}
        </div>

        <button
          type="submit"
          className="bg-green-500 text-white px-4 py-2 rounded mt-4 hover:bg-green-700"
        >
          Add Vehicle
        </button>
      </form>
    </div>
  );
};

export default AddVehiculeForm;
