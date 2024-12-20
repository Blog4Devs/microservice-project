import "./App.css";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import Clients from "./pages/Clients";
import Vehicles from "./pages/Vehicles";
import Maintenances from "./pages/Maintenances";
import Invoices from "./pages/Invoices";

const App = () => {
  return (
    <div className="min-h-screen bg-gray-100">
      <nav className="bg-blue-600 text-white p-4">
        <Link to="/clients" className="mr-4">
          Clients
        </Link>
        <Link to="/vehicles" className="mr-4">
          Vehicles
        </Link>
        <Link to="/maintenances " className="mr-4">
          Maintenances
        </Link>
        <Link to="/invoices">Invoices</Link>
      </nav>
      <main className="p-4">
        <Routes>
          <Route path="/" element={<Clients />} />
          <Route path="/clients" element={<Clients />} />

          <Route path="/vehicles" element={<Vehicles />} />
          <Route path="/maintenances" element={<Maintenances />} />
          <Route path="/invoices" element={<Invoices />} />
        </Routes>
      </main>
    </div>
  );
};

export default App;
