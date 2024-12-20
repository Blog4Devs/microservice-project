import axios from 'axios';

const baseURL = import.meta.env.VITE_API_BASE_URL;

export const api = axios.create({
    baseURL: baseURL,
    timeout: 5000,
    headers: {
      "Content-Type": "application/json", 
      Accept: "application/json",
      // "ngrok-skip-browser-warning": "69420", 
    },
  });
