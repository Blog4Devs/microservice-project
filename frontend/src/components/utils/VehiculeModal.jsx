import { useState } from "react";
import { CloseIcon } from "../icons/icons";

const Modal = ({ onClose, children, onAddVehicle, clientId }) => {
    const [formData, setFormData] = useState({
        vin: '',
        numMatriculation: '',
        marque: '',
        annee: '',
        color: '',
        kilometrage: '',
        carburant: '',
        dateAchat: '',
        clientId
      });
    
      const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
      };
    
      const handleSubmit = (e) => {
        e.preventDefault();
        onAddVehicle(formData);
        setFormData({
          vin: '',
          numMatriculation: '',
          marque: '',
          annee: '',
          color: '',
          kilometrage: '',
          carburant: '',
          dateAchat: '',
        });
    };
    return (
        <div className="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-50">
            <div className="relative bg-white p-12 rounded-lg shadow-lg w-[800px]"> {/* Increased width and padding */}
                <button
                    onClick={onClose}
                    className="absolute top-2 right-2 text-gray-600 hover:text-gray-900"
                >
                    <CloseIcon />
                </button>
                <form onSubmit={handleSubmit} className="p-4">
                    <h2 className="text-xl font-bold mb-4 text-gray-600">Add New Vehicle</h2>
                    <div className="grid grid-cols-2 gap-4">
                        {/* VIN */}
                        <div className="text-start">
                            <label htmlFor="vin" className="text-gray-600">VIN</label>
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
                            <label htmlFor="numMatriculation" className="text-gray-600">Matriculation</label>
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
                            <label htmlFor="marque" className="text-gray-600">Marque</label>
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
                            <label htmlFor="annee" className="text-gray-600">Year</label>
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
                            <label htmlFor="color" className="text-gray-600">Color</label>
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
                            <label htmlFor="kilometrage" className="text-gray-600">Kilometrage</label>
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
                            <label htmlFor="carburant" className="text-gray-600">Fuel Type</label>
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
                            <label htmlFor="dateAchat" className="text-gray-600">Purchase Date</label>
                            <input
                                type="date"
                                name="dateAchat"
                                id="dateAchat"
                                value={formData.dateAchat}
                                onChange={handleChange}
                                className="border border-gray-300 p-2 rounded w-full text-gray-600"
                                required
                            />
                        </div>

                        {/* Client ID */}
                        <div className="text-start">
                            <label htmlFor="clientId" className="text-gray-600">Client ID</label>
                            <input
                                type="number"
                                name="clientId"
                                id="clientId"
                                value={clientId}
                                onChange={handleChange}
                                className="border border-gray-300 p-2 rounded w-full text-gray-600"
                                disabled
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
                            <label htmlFor="isDelivered" className="text-gray-600">Delivered</label>
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
        </div>
    )
};

export default Modal;
