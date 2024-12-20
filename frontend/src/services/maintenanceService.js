import axios from "axios";

const BASE_URL = "http://localhost:8999/MAINTENANCE-SERVICE/api/maintenances";

export const addNewMaintenance = async (maintenance) => {
  try {
    console.log(maintenance)
    const response = await axios.post(BASE_URL + "/schedule", maintenance);
    return response.data;
  } catch (error) {
    console.error("Error adding maintenance:", error);
    throw error;
  }
};
