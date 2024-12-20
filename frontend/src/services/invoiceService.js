import axios from "axios";

const BASE_URL = "http://localhost:3000/invoices";

export const getAllInvoices = async () => {
  try {
    const response = await axios.get(BASE_URL);
    return response.data;
  } catch (error) {
    console.error("Error fetching invoices:", error);
    throw error;
  }
};

export const getInvoice = async (clientId) => {
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
    const response = await axios.post(
      "http://localhost:8999/graphql",
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
    return response.data.data.getInvoiceById;
  } catch (err) {
    console.log(err);
  }
};

export const getClients = async () => {
  try {
    //const response = await axios.get("https://3b37-196-200-133-150.ngrok-free.app/CLIENT-SERVICE/api/clients");
    const response = await axios.get(
      "http://localhost:8999/CLIENT-SERVICE/api/clients?size=20&page=0"
    );
    return response.data;
  } catch (error) {
    console.error("Error fetching clients:", error);
    throw error;
  }
};

export const getMaintenances = async () => {
  try {
    //const response = await axios.get("https://3b37-196-200-133-150.ngrok-free.app/CLIENT-SERVICE/api/clients");
    const response = await axios.get(
      "http://localhost:8999/MAINTENANCE-SERVICE/api/maintenances"
    );
    return response.data;
  } catch (error) {
    console.error("Error fetching clients:", error);
    throw error;
  }
};

export const getVehicules = async () => {
  try {
    //const response = await axios.get("https://3b37-196-200-133-150.ngrok-free.app/CLIENT-SERVICE/api/clients");
    const response = await axios.get(
      "http://localhost:8999/VEHICULE-SERVICE/api/vehicules?size=20&page=0"
    );
    //console.log(response);
    return response.data;
  } catch (error) {
    console.error("Error fetching clients:", error);
    throw error;
  }
};

export const getInvoiceById = async (invoiceId) => {
  try {
    const response = await axios.get(`${BASE_URL}/${invoiceId}`);
    return response.data;
  } catch (error) {
    console.error("Error fetching invoice by ID:", error);
    throw error;
  }
};
