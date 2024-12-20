import axios from "axios";

const BASE_URL = "http://localhost:8999/CLIENT-SERVICE/api/clients";

export const addNewClient = async (client) => {
  try {
    const response = await axios.post(BASE_URL, client);
    return response.data;
  } catch (error) {
    console.error("Error adding client:", error);
    throw error;
  }
};

export const updateClient = async (clientId, updatedClientData) => {
  try {
    const response = await axios.put(
      `${BASE_URL}/${clientId}`,
      updatedClientData
    );
    return response.data;
  } catch (error) {
    console.error("Error updating client:", error);
    throw error;
  }
};

export const deleteClient = async (clientId) => {
  try {
    await axios.delete(`${BASE_URL}/${clientId}`);
  } catch (error) {
    console.error("Error deleting client:", error);
    throw error;
  }
};
