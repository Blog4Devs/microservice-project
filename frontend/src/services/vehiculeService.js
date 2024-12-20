import axios from "axios";

const BASE_URL = "http://localhost:8999/VEHICULE-SERVICE/api/vehicules";

export const addNewVehicule = async (vehicule) => {
  try {
    const response = await axios.post(BASE_URL, vehicule);
    return response.data;
  } catch (error) {
    console.error("Error adding vehicule:", error);
    throw error;
  }
};
