import { useState } from "react";
import { DeleteIcon, EditIcon } from "../icons/icons";
import { addNewMaintenance } from "../../services/maintenanceService";

const AddMaintenanceForm = () => {
  const [formData, setFormData] = useState({
    predictedEndTime: "",
    description: "",
    operations: [],
    vehicleId: "",
    status: "",
    idProprietaire: "",
    paid: "",
  });

  const [operation, setOperation] = useState({ description: "", price: "" });
  const [editingIndex, setEditingIndex] = useState(-1);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleOperationChange = (e) => {
    const { name, value } = e.target;
    setOperation({ ...operation, [name]: value });
  };

  const addOrUpdateOperation = (e) => {
    e.preventDefault();
    if (operation.description && operation.price) {
      if (editingIndex === -1) {
        setFormData({
          ...formData,
          operations: [...formData.operations, { ...operation }],
        });
      } else {
        const updatedOperations = [...formData.operations];
        updatedOperations[editingIndex] = { ...operation };
        setFormData({ ...formData, operations: updatedOperations });
        setEditingIndex(-1);
      }
      setOperation({ description: "", price: "" });
    }
  };

  const deleteOperation = (index) => {
    const updatedOperations = formData.operations.filter((_, i) => i !== index);
    setFormData({ ...formData, operations: updatedOperations });
  };

  const editOperation = (index) => {
    const operationToEdit = formData.operations[index];
    setOperation(operationToEdit);
    setEditingIndex(index);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    addMaintenance(formData);
    setFormData({
      predictedEndTime: "",
      description: "",
      operations: [],
      vehicleId: "",
      status: "",
      idProprietaire: "",
      paid: "",
    });
  };

  const addMaintenance = async (newMaintenance) => {
    try {
      const response = await addNewMaintenance(newMaintenance);
    } catch (error) {
      console.error("Error adding maintenance:", error);
    }
  };

  const handleDateChange = (e) => {
    const inputDate = e.target.value; // assuming the input is of type="date"
    const isoDate = new Date(inputDate).toISOString(); // Convert to ISO format
    setFormData((prevData) => ({
      ...prevData,
      predictedEndTime: isoDate,
    }));
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
      <h1 className="text-2xl font-bold mb-4">Maintenance Manager</h1>
      <form onSubmit={handleSubmit} className="p-4 border rounded shadow mb-6">
        <h2 className="text-xl font-bold mb-4">Add Maintenance</h2>

        {/* Description Field */}
        <div className="mb-4 text-start text-white">
          <label
            htmlFor="description"
            className="block text-white font-semibold mb-1"
          >
            Description
          </label>
          <input
            type="text"
            id="description"
            name="description"
            placeholder="Description"
            value={formData.description}
            onChange={handleChange}
            className="border p-2 w-full text-gray-600 rounded"
            required
          />
        </div>

        {/* Predicted End Time Field */}
        <div className="mb-4 text-start text-white">
          <label
            htmlFor="predictedEndTime"
            className="block text-white font-semibold mb-1"
          >
            Predicted End Time
          </label>
          <input
            type="date"
            id="predictedEndTime"
            name="predictedEndTime"
            value={formatDateForInput(formData.predictedEndTime)}
            onChange={handleDateChange}
            className="border p-2 w-full text-gray-600 rounded"
            required
          />
        </div>

        {/* Vehicle ID Field */}
        <div className="mb-4 text-start text-white">
          <label
            htmlFor="vehicleId"
            className="block text-white font-semibold mb-1"
          >
            Vehicle ID
          </label>
          <input
            type="number"
            id="vehicleId"
            name="vehicleId"
            placeholder="Vehicle ID"
            value={formData.vehicleId}
            onChange={handleChange}
            className="border p-2 w-full text-gray-600 rounded"
            required
          />
        </div>

        <div className="mb-4 text-start text-white">
          <label
            htmlFor="idProprietaire"
            className="block text-white font-semibold mb-1"
          >
            Id du propriétaire
          </label>
          <input
            id="idProprietaire"
            name="idProprietaire"
            placeholder="Id du propriétaire"
            value={formData.idProprietaire}
            onChange={handleChange}
            className="border p-2 w-full text-gray-600 rounded"
            required
          />
        </div>
        <div className="mb-4 text-start text-white">
          <label
            htmlFor="status"
            className="block text-white font-semibold mb-1"
          >
            Status
          </label>
          <select
            name="status"
            id="status"
            placeholder="Select a Status"
            value={formData.status}
            onChange={handleChange}
            className="border border-gray-300 p-2 rounded w-full text-gray-600"
            required
          >
            <option value="PROCESSING">Processing</option>
            <option value="FINISHED">Finished</option>
          </select>
        </div>

        <div className="mb-4 text-start text-white">
          <label className="block text-white font-semibold mb-1">Paid</label>
          <div className="flex items-center space-x-4">
            <label className="flex items-center">
              <input
                type="radio"
                name="paid"
                value="true"
                checked={formData.paid === "true"}
                onChange={handleChange}
                className="mr-2"
              />
              Yes
            </label>
            <label className="flex items-center">
              <input
                type="radio"
                name="paid"
                value="false"
                checked={formData.paid === "false"}
                onChange={handleChange}
                className="mr-2"
              />
              No
            </label>
          </div>
        </div>

        {/* Operations Form */}
        <div className="mb-4 text-start text-white">
          <h3 className="text-lg font-semibold mb-2">Operations</h3>

          <div className="flex items-center gap-4 mb-2">
            <div className="flex-1">
              <label
                htmlFor="operationDescription"
                className="block text-white font-semibold mb-1"
              >
                Operation Description
              </label>
              <input
                type="text"
                id="operationDescription"
                name="description"
                placeholder="Operation Description"
                value={operation.description}
                onChange={handleOperationChange}
                className="border p-2 w-full text-gray-600 rounded"
              />
            </div>
            <div className="flex-1 text-start">
              <label
                htmlFor="operationPrice"
                className="block text-white font-semibold mb-1"
              >
                Price
              </label>
              <input
                type="number"
                id="operationPrice"
                name="price"
                placeholder="Price"
                value={operation.price}
                onChange={handleOperationChange}
                className="border p-2 w-full text-gray-600 rounded"
              />
            </div>
            <button
              type="button"
              onClick={addOrUpdateOperation}
              className="mt-5 flex items-center justify-center bg-blue-500 text-white w-10 h-10 rounded-full"
            >
              {editingIndex === -1 ? "+" : "✓"}
            </button>
          </div>

          {/* Display Added Operations */}
          <ul className="mt-4">
            {formData.operations.map((op, index) => (
              <li
                key={index}
                className="flex justify-between items-center mb-2"
              >
                <span>{`${op.description} - $${op.price}`}</span>
                <div className="flex gap-2">
                  <button
                    type="button"
                    onClick={() => editOperation(index)}
                    className="bg-blue-500 text-white px-2 py-1 rounded"
                  >
                    <EditIcon />
                  </button>
                  <button
                    type="button"
                    onClick={() => deleteOperation(index)}
                    className="bg-red-500 text-white px-2 py-1 rounded"
                  >
                    <DeleteIcon />
                  </button>
                </div>
              </li>
            ))}
          </ul>
        </div>

        {/* Submit Button */}
        <button
          type="submit"
          className="bg-green-500 text-white px-4 py-2 rounded"
        >
          Add Maintenance
        </button>
      </form>
    </div>
  );
};

export default AddMaintenanceForm;
