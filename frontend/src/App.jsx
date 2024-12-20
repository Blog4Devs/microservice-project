import { useState } from "react";
import reactLogo from "./assets/react.svg";
import viteLogo from "/vite.svg";
import "./App.css";
import Login from "./components/login";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Navbar from "./components/navbar";
import Nav from "./components/navbar";
import ClientManager from "./components/client/ClientManager";
import VehiculeManager from "./components/vehicule/VehiculeManager";
import MaintenanceManager from "./components/maintenance/MaintenanceManager";
import { AddClientForm } from "./components/client/AddClientForm";
import AddVehiculeForm from "./components/vehicule/AddVehiculeForm";
import AddMaintenanceForm from "./components/maintenance/AddMaintenanceForm";
import InvoiceDetails from "./components/invoice/InvoiceDetails";

function App() {
  return (
    <Router>
      <div>
        <Nav />
        <Routes>
          <Route path="/ui/invoice" element={<InvoiceDetails />} />
          <Route path="/ui/clients" element={<ClientManager />} />
          <Route path="/ui/clients/form" element={<AddClientForm />} />
          <Route path="/ui/vehicules" element={<VehiculeManager />} />
          <Route path="/ui/vehicules/form" element={<AddVehiculeForm />} />
          <Route path="/ui/maintenances" element={<MaintenanceManager />} />
          <Route path="/ui/maintenances/form" element={<AddMaintenanceForm />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
